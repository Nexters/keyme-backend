package com.nexters.keyme.domain.member.domain.service.processor;

import com.nexters.keyme.global.common.exceptions.FileUploadFailedException;
import com.nexters.keyme.domain.member.dto.internal.ImageInfo;
import com.nexters.keyme.infra.interfaces.FileUploader;
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
public class ProfileImageUploader {
    private final FileUploader fileUploader;

    @Value("${image-temp}")
    private String tempImagePath;

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
        return fileUploader.uploadAndGetUrl(inputStream, extension);
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
