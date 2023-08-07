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
            originalUrl = uploadToS3(image.getInputStream(), new ObjectMetadata(), extension);
            thumbnailUrl = uploadToS3(new FileInputStream(resizeForThumbnail(image)), new ObjectMetadata(), extension);
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException();
        }

        return new ImageInfo(originalUrl, thumbnailUrl);
    }

    private File resizeForThumbnail(MultipartFile image) {
        String extension = extractExtension(image);

        File original = new File(tempImagePath + UUID.randomUUID() + "." + extension);
        File thumbnail = new File(tempImagePath + UUID.randomUUID() + "." + extension);

        try (FileOutputStream os = new FileOutputStream(original)) {
            os.write(image.getBytes());
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException();
        }

        try {
            Thumbnails.of(original)
                .size(700, 400)
                .toFile(thumbnail);
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException();
        }

        original.delete();

        return thumbnail;
    }

    private String uploadToS3(InputStream inputStream, ObjectMetadata metadata, String extension) {
        String key = UUID.randomUUID() + "." + extension;

        s3Client.putObject(bucket, key, inputStream, metadata);
        return s3Client.getUrl(bucket, key).toString();
    }

    private String extractExtension(MultipartFile image) {
        return Objects.requireNonNull(image.getContentType()).split("/")[1];
    }

    private void clearFiles(File original, File thumbnail) {
        original.delete();
        thumbnail.delete();
    }
}
