package com.example.codeE.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.Duration;

public class DockerHelper {
    public DefaultDockerClientConfig dockerClientConfig;
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

    public String buildDockerImage(File dockerFileName) {
        String imageId = null;
        BuildImageCmd buildImageCmd = this.dockerClient.buildImageCmd()
                .withDockerfile(dockerFileName)
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
                //write log, return false
                e.printStackTrace();
            }
            return outputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String convertXmlToJson(String xmlContent) {
//        try {
//            // Create XmlMapper
//            XmlMapper xmlMapper = new XmlMapper();
//            // Read XML string into JsonNode
//            JsonNode jsonNode = xmlMapper.readTree(xmlContent);
//            // Create ObjectMapper
//            ObjectMapper objectMapper = new ObjectMapper();
//            // Convert JsonNode to JSON string
//            return objectMapper.writeValueAsString(jsonNode);
//        } catch (JsonMappingException e) {
//            throw new RuntimeException(e);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Boolean overFileContent(String filePath, String content) {
        try{
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
