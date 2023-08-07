package com.nexters.keyme.member.infrastructure;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.nexters.keyme.member.domain.internaldto.ImageInfo;
import com.nexters.keyme.member.domain.service.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3ImageUploader implements ImageUploader {
    private final AmazonS3Client s3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public ImageInfo uploadImage(MultipartFile image) {
        try {
            String originalUrl = uploadToS3(image.getInputStream(), new ObjectMetadata());
            String thumbnailUrl = uploadToS3(new FileInputStream(resizeForThumbnail(image)), new ObjectMetadata());

            return new ImageInfo(originalUrl, thumbnailUrl);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException();
        }
    }

    private File resizeForThumbnail(MultipartFile image) {
        return new File("dummy path");
    }

    private String uploadToS3(InputStream inputStream, ObjectMetadata metadata) {
        String key = UUID.randomUUID() + ".jpg";

        s3Client.putObject(bucket, key, inputStream, metadata);
        return s3Client.getUrl(bucket, key).toString();
    }
}
