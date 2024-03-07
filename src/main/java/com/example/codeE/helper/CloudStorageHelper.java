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

    @Autowired
    private Environment environment;

    private Storage storage;
    private String projectId;
    private String bucketName;

    public CloudStorageHelper() {
        // Empty constructor - initialization moved to @PostConstruct method
    }

    @PostConstruct
    void init() {
        // This method will be executed after dependency injection is done to perform any initialization
        this.projectId = environment.getProperty("google.projectId");
        this.bucketName = environment.getProperty("storage.bucketName");
        System.out.println(this.projectId);
        System.out.println(this.bucketName);
        this.storage = StorageOptions.newBuilder().setProjectId(this.projectId).build().getService();
    }

    public boolean uploadFile(MultipartFile file, boolean isPublic) throws IOException {
        String objectName = file.getOriginalFilename();
        if (objectName == null) {
            throw new IOException("File name is null");
        }
        BlobId blobId = BlobId.of(this.bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Storage.BlobWriteOption precondition;
        if (storage.get(bucketName, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition = Storage.BlobWriteOption.generationMatch(
                    storage.get(bucketName, objectName).getGeneration());
        }
        storage.createFrom(blobInfo, file.getInputStream(), precondition);
        if (isPublic) {
            storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        }
        return true;
    }


    public boolean uploadFile(MultipartFile file) throws IOException {
        return this.uploadFile(file, false);
    }

    public boolean deleteFile(String fileUrl) throws IOException {
        BlobId blobId = BlobId.of(this.bucketName, fileUrl);
        Blob blob = storage.get(blobId);
        if (blob == null) {
            throw new IOException("File  not found");
        }

        Storage.BlobSourceOption precondition = Storage.BlobSourceOption.generationMatch(blob.getGeneration());

        storage.delete(blobId, precondition);
        return true;
    }
}
