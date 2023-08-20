package com.nexters.keyme.domain.member.domain.service;

import com.nexters.keyme.domain.member.domain.internaldto.ImageInfo;
import org.springframework.web.multipart.MultipartFile;


public interface ImageUploader {
    ImageInfo uploadImage(MultipartFile image);
}
