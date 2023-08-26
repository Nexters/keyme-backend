package com.nexters.keyme.domain.member.infrastructure;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.nexters.keyme.global.common.exceptions.FileUploadFailedException;
import com.nexters.keyme.domain.member.dto.internal.ImageInfo;
import com.nexters.keyme.domain.member.domain.service.processor.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
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
        String originalUrl;
        String thumbnailUrl;
        String extension = extractExtension(image);

        try {
            originalUrl = uploadToS3AndGetUrl(image.getInputStream(), extension);
            thumbnailUrl = uploadToS3AndGetUrl(resizeForThumbnail(image), extension);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(e.getCause().getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
            throw new FileUploadFailedException();
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

    private String uploadToS3AndGetUrl(InputStream inputStream, String extension) {
        String key = UUID.randomUUID() + "." + extension;

        s3Client.putObject(bucket, key, inputStream, new ObjectMetadata());
        return s3Client.getUrl(bucket, key).toString();
    }

    private String uploadToS3AndGetUrl(File tempFile, String extension) throws FileNotFoundException {
        String url = uploadToS3AndGetUrl(new FileInputStream(tempFile), extension);
        tempFile.delete();

        return url;
    }

    private String extractExtension(MultipartFile image) {
        return Objects.requireNonNull(image.getContentType()).split("/")[1];
    }

}
