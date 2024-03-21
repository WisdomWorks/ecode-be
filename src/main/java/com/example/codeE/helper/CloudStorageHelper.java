package com.example.codeE.helper;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class CloudStorageHelper {
    private final String PUBLIC_URL = "https://storage.googleapis.com/";
    @Autowired
    private Environment environment;
    private Storage storage;
    private String bucketName;

    public CloudStorageHelper() {
        // Empty constructor - initialization moved to @PostConstruct method
    }

    @PostConstruct
    void init() {
        // This method will be executed after dependency injection is done to perform any initialization
        var projectId = environment.getProperty("google.projectId");
        this.bucketName = environment.getProperty("storage.bucketName");
//        System.out.println(projectId);
//        System.out.println(bucketName);
        this.storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    }

    public String uploadFile(MultipartFile file, boolean isPublic, String store) throws Exception {
        String objectName = file.getOriginalFilename();
        if (objectName == null) {
            throw new IOException("File name is null");
        }
        try {
            BlobId blobId = BlobId.of(this.bucketName, store + objectName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            Storage.BlobWriteOption precondition;
            if (storage.get(bucketName, store + objectName) == null) {
                precondition = Storage.BlobWriteOption.doesNotExist();
            } else {
                precondition = Storage.BlobWriteOption.generationMatch(
                        storage.get(bucketName, store + objectName).getGeneration());
            }
            storage.createFrom(blobInfo, file.getInputStream(), precondition);
            if (isPublic) {
                storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
            }
        } catch (Exception e) {
            throw new Exception("Can not upload file to cloud storage");
        }
        return PUBLIC_URL + this.bucketName + "/"+ store + file.getOriginalFilename();
    }


    public String uploadFile(MultipartFile file, String store) throws Exception {
        return this.uploadFile(file, false, store);
    }

    public boolean deleteFile(String fileUrl) throws Exception {
        String objectName = getFileNameFromPath(fileUrl);
        System.out.println(objectName);
        System.out.println(bucketName);
        Blob blob = storage.get(bucketName, objectName);
        if (blob == null) {
            throw new IOException("File not found");
        }
        try {
            Storage.BlobSourceOption precondition = Storage.BlobSourceOption.generationMatch(blob.getGeneration());
            storage.delete(BlobId.of(this.bucketName, objectName), precondition);
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new Exception("Can not delete this material on cloud / "+e.getMessage());
        }
        return true;
    }

    public String getFileNameFromPath(String filePath) {
        String[] pathParts = filePath.split("/");
        return pathParts[pathParts.length - 2] + "/" + pathParts[pathParts.length - 1];
    }
}
