package com.epam.springmvc.converter;

import com.epam.springmvc.model.User;
import com.epam.springmvc.utility.PdfUtility;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class PdfHttpMessageConverter extends AbstractHttpMessageConverter<List<User>> {

    private PdfUtility pdfUtility;

    public PdfHttpMessageConverter(PdfUtility pdfUtility) {
        super(new MediaType("application", "pdf"));
        this.pdfUtility = pdfUtility;
    }

    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_PDF);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected List<User> readInternal(Class<? extends List<User>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(List<User> users, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        pdfUtility.createUsersPdf(users);
    }

}
