package com.epam.springmvc.exception;

import org.springframework.web.multipart.MultipartFile;

public class FileIsEmptyException extends Exception {

    public FileIsEmptyException(MultipartFile file) {
        super("FileIsEmptyException of file =" + file.getName());
    }
}
