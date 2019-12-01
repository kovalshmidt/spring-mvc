package com.epam.springmvc.exception;

public class NotAcceptedFileFormatException extends Exception {

    public NotAcceptedFileFormatException(String expected, String actual) {
        super("NotAcceptedFileFormatException, expected:  " + expected + ", found: " + actual);
    }
}
