package com.example.codeE.helper;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CopyArchiveFromContainerCmd;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class DockerHelper {
    public DefaultDockerClientConfig dockerClientConfig;
    public DockerHttpClient dockerHttpClient;
    private DockerClient dockerClient;

    public DockerHelper() {
        dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .sslConfig(dockerClientConfig.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        dockerClient = DockerClientImpl.getInstance(dockerClientConfig, httpClient);
    }

    public String buildDockerImage(File dockerFile) {
        String imageId = null;
        BuildImageCmd buildImageCmd = this.dockerClient.buildImageCmd()
                .withDockerfile(dockerFile)
                .withPull(true)
                .withNoCache(true);

        try {
            imageId = buildImageCmd.exec(new BuildImageResultCallback() {
                @Override
                public void onNext(BuildResponseItem item) {
                    System.out.println(item.getStream());
                    super.onNext(item);
                }
            }).awaitImageId();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return imageId;
    }

    public String createContainer(String dockerImageIdString) {
        String containerId = null;
        try {
            containerId = this.dockerClient.createContainerCmd(dockerImageIdString)
                    .exec()
                    .getId();

            this.dockerClient.startContainerCmd(containerId).exec();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return containerId;
    }

    public String runCmd(String containerId, String... args) {
        try {
            String execId = dockerClient.execCreateCmd(containerId)
                    .withCmd(args)
                    .withAttachStdout(true)
                    .withAttachStderr(true)
                    .exec()
                    .getId();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                dockerClient.execStartCmd(execId)
                        .exec(new ExecStartResultCallback(outputStream, System.err))
                        .awaitCompletion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return outputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void replaceFile (String containerId, String contentFile, String pathFile) {
        // Create temp file
        String tempFilePath = createTempFile(contentFile);
        File localFile = new File(tempFilePath);

        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                TarArchiveOutputStream tarArchive = new TarArchiveOutputStream(byteArrayOutputStream)
        ) {
            tarArchive.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);
            int lastSlashIndex = StringUtils.removeEnd(pathFile, "/").lastIndexOf("/");
            String extractArchiveTo = pathFile.substring(0, lastSlashIndex + 1);
            String pathInArchive = pathFile.substring(lastSlashIndex + 1);

            // Transfer local file to TarArchiveOutputStream
            try (FileInputStream fileInputStream =
                         new FileInputStream(localFile)) {
                tarArchive.putArchiveEntry(new TarArchiveEntry(localFile, pathInArchive));
                IOUtils.copy(fileInputStream, tarArchive);
                tarArchive.closeArchiveEntry();
            }

            tarArchive.finish();

            // Copy the tar archive to the container
            dockerClient
                    .copyArchiveToContainerCmd(containerId)
                    .withTarInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))
                    .withRemotePath(extractArchiveTo)
                    .exec();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String copyFileFromContainer(String containerId, String containerFilePath){
        String tempFilePath = createTempFile("");

        CopyArchiveFromContainerCmd copyCmd = dockerClient.copyArchiveFromContainerCmd(containerId, containerFilePath);

        try (var archiveStream = copyCmd.exec();
             var tarInputStream = new TarArchiveInputStream(archiveStream)) {

            TarArchiveEntry entry;
            while ((entry = tarInputStream.getNextTarEntry()) != null) {
//                if (!entry.isDirectory() && entry.getName().endsWith("/" + containerFilePath)) {
                    Files.copy(tarInputStream, Paths.get(tempFilePath), StandardCopyOption.REPLACE_EXISTING);
                    break;
//                }
            }
            return new String(Files.readAllBytes(Paths.get(tempFilePath)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String createTempFile(String content) {
        try {
            String tempFilePath = Files.createTempFile("updatedFile", ".java").toString();
            Files.write(Paths.get(tempFilePath), content.getBytes(StandardCharsets.UTF_8));
            return tempFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating temporary file", e);
        }
    }
}
