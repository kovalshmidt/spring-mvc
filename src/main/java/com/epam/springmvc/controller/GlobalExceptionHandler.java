package com.epam.springmvc.controller;

import com.epam.springmvc.exception.FileIsEmptyException;
import com.epam.springmvc.exception.NotAcceptedFileFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public String handleIOException() {
        log.warn("IOException handler executed");

        return "exceptions/notFound";
    }

    @ExceptionHandler(FileIsEmptyException.class)
    public String handleFileIsEmptyException(HttpServletRequest request, Exception ex, Model model) {
        log.warn("FileIsEmptyException handler executed");

        String message = "The file is empty, please upload another file";
        model.addAttribute("message", message);
        model.addAttribute("exceptionMessage", ex.getMessage());
        return "exceptions/error";
    }

    @ExceptionHandler(NotAcceptedFileFormatException.class)
    public String handleNotAcceptedFileFormatException(HttpServletRequest request, Exception ex, Model model) {
        log.warn("NotAcceptedFileFormatException handler executed");

        String message = "The file is of a wrong content type, please upload another file";
        model.addAttribute("message", message);
        model.addAttribute("exceptionMessage", ex.getMessage());
        return "exceptions/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception ex, Model model) {
        log.warn("tException handler executed");

        String message = "The file is of a wrong content type, please upload another file";
        model.addAttribute("message", message);
        model.addAttribute("exceptionMessage", ex.getMessage());
        return "exceptions/error";
    }
}
