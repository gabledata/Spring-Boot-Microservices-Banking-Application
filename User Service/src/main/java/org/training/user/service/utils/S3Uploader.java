package org.training.user.service.utils;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.charset.StandardCharsets;

public class S3Uploader {

    public static void uploadContentsToFile(String contents, String bucketName, String key) {
        // Upload the contents to the specified file in the S3 bucket
        try {
            S3Client s3 = S3Client.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .build();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType("application/json")
                    .build();
                    
            s3.putObject(
                    putObjectRequest,
                    software.amazon.awssdk.core.sync.RequestBody.fromString(contents, StandardCharsets.UTF_8)
            );
            s3.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
