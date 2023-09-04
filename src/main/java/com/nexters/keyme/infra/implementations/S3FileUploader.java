package com.nexters.keyme.infra.implementations;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.nexters.keyme.infra.interfaces.FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3FileUploader implements FileUploader {
    private final AmazonS3Client s3Client;

    @Value("${image-temp}")
    private String tempImagePath;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadAndGetUrl(InputStream inputStream, String extension) {
        String key = UUID.randomUUID() + "." + extension;

        s3Client.putObject(bucket, key, inputStream, new ObjectMetadata());
        return s3Client.getUrl(bucket, key).toString();
    }
}
