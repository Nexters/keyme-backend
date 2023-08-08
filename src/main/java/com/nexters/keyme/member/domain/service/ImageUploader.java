package com.nexters.keyme.member.domain.service;

import com.nexters.keyme.member.domain.internaldto.ImageInfo;
import org.springframework.web.multipart.MultipartFile;


public interface ImageUploader {
    ImageInfo uploadImage(MultipartFile image);
}
