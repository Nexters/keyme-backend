package com.nexters.keyme.domain.member.domain.service.processor;

import com.nexters.keyme.domain.member.dto.internal.ImageInfo;
import org.springframework.web.multipart.MultipartFile;


public interface ImageUploader {
    ImageInfo uploadImage(MultipartFile image);
}
