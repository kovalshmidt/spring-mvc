package com.epam.springmvc.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    boolean uploadFileFromJson(MultipartFile file);
}
