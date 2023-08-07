package com.nexters.keyme.member.infrastructure;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.nexters.keyme.member.domain.internaldto.ImageInfo;
import com.nexters.keyme.member.domain.service.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3ImageUploader implements ImageUploader {
    private final AmazonS3Client s3Client;
    @Value("${image-temp}")
    private String tempImagePath;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public ImageInfo uploadImage(MultipartFile image) {
        String extension = extractExtension(image);
        String originalUrl;
        String thumbnailUrl;

        try {
            originalUrl = uploadToS3AndGetUrl(image.getInputStream(), new ObjectMetadata(), extension);
            thumbnailUrl = uploadToS3AndGetUrl(resizeForThumbnail(image), new ObjectMetadata(), extension);
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException();
        }

        return new ImageInfo(originalUrl, thumbnailUrl);
    }

    private File resizeForThumbnail(MultipartFile image) throws IOException {
        String extension = extractExtension(image);
        File thumbnail = new File(tempImagePath + UUID.randomUUID() + "." + extension);

        Thumbnails.of(image.getInputStream())
            .size(700, 400)
            .toFile(thumbnail);

        return thumbnail;
    }

    private String uploadToS3AndGetUrl(InputStream inputStream, ObjectMetadata metadata, String extension) {
        String key = UUID.randomUUID() + "." + extension;

        s3Client.putObject(bucket, key, inputStream, metadata);
        return s3Client.getUrl(bucket, key).toString();
    }

    private String uploadToS3AndGetUrl(File tempFile, ObjectMetadata metadata, String extension) throws FileNotFoundException {
        String url = uploadToS3AndGetUrl(new FileInputStream(tempFile), metadata, extension);
        tempFile.delete();

        return url;
    }

    private String extractExtension(MultipartFile image) {
        return Objects.requireNonNull(image.getContentType()).split("/")[1];
    }

}
