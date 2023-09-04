package com.nexters.keyme.infra.interfaces;

import java.io.InputStream;

public interface FileUploader {
    String uploadAndGetUrl(InputStream inputStream, String extension);
}
