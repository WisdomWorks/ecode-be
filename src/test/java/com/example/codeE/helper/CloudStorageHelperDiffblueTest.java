package com.example.codeE.helper;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {CloudStorageHelper.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CloudStorageHelperDiffblueTest {
    @Autowired
    private CloudStorageHelper cloudStorageHelper;

    @MockBean
    private Environment environment;

    /**
     * Method under test: {@link CloudStorageHelper#init()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInit() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        // TODO: Populate arranged inputs
        this.cloudStorageHelper.init();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test:
     * {@link CloudStorageHelper#uploadFile(MultipartFile, String)}
     */
    @Test
    void testUploadFile() throws Exception {
        // Arrange, Act and Assert
        assertThrows(Exception.class, () -> cloudStorageHelper
                .uploadFile(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), "Store"));
        assertThrows(Exception.class, () -> cloudStorageHelper.uploadFile(
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))), true, "Store"));
    }

    /**
     * Method under test: {@link CloudStorageHelper#deleteFile(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteFile() throws IOException, RuntimeException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.google.common.base.Preconditions.checkNotNull(Preconditions.java:903)
        //       at com.google.cloud.storage.BlobId.of(BlobId.java:120)
        //       at com.google.cloud.storage.StorageImpl.get(StorageImpl.java:329)
        //       at com.example.codeE.helper.CloudStorageHelper.deleteFile(CloudStorageHelper.java:67)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        cloudStorageHelper.deleteFile("https://example.org/example");
    }

    /**
     * Method under test: {@link CloudStorageHelper#getFileNameFromPath(String)}
     */
    @Test
    void testGetFileNameFromPath() {
        // Arrange, Act and Assert
        assertNull(cloudStorageHelper.getFileNameFromPath("/directory/foo.txt"));
    }
}
