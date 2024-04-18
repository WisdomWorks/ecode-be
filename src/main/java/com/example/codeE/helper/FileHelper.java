package com.example.codeE.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.mock.web.MockMultipartFile;

public class FileHelper {
    public static void zipFile(Path filePath, Path dir){
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(filePath))) {
            Files.walk(dir)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(dir.relativize(path).toString());
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            Files.copy(path, zipOutputStream);
                            zipOutputStream.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MultipartFile convertFileToMultiPartFile(File file, String newName){
        // Create a new File object with the new name in the same directory
        File newFile = new File(file.getParent(), newName);

        // Rename the file
        boolean isRenamed = file.renameTo(newFile);
        if (!isRenamed) {
            throw new RuntimeException("Failed to rename file");
        }

        // Convert the renamed file to MultipartFile
        try (FileInputStream input = new FileInputStream(newFile)) {
            return new MockMultipartFile("file",
                    newFile.getName(), "text/plain", input);
        } catch (IOException e) {
            throw new RuntimeException("Error converting file to MultipartFile", e);
        }
    }
}
