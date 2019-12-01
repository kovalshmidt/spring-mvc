package com.epam.springmvc.controller;

import com.epam.springmvc.exception.FileIsEmptyException;
import com.epam.springmvc.exception.NotAcceptedFileFormatException;
import com.epam.springmvc.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
public class FileUploadController {

    private UploadService uploadService;

    @Autowired
    public FileUploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws FileIsEmptyException, NotAcceptedFileFormatException {

        if (!file.isEmpty()) {
            String contentTypeJSON = "application/json";
            if (!Objects.equals(file.getContentType(), contentTypeJSON)) {
                throw new NotAcceptedFileFormatException(contentTypeJSON, file.getContentType());
            }
            uploadService.uploadFileFromJson(file);
            return "redirect:/users";
        }

        throw new FileIsEmptyException(file);
    }
}
