package com.epam.springmvc.viewResolvers;


import com.epam.springmvc.model.User;
import com.epam.springmvc.utility.PdfUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Component
public class PdfViewResolver extends AbstractView {

    private PdfUtility pdfUtility;

    @Autowired
    public PdfViewResolver(PdfUtility pdfUtility) {
        setContentType("application/pdf");
        this.pdfUtility = pdfUtility;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = (List<User>) model.get("userList");
        response.addHeader("Content-Type", MediaType.APPLICATION_PDF_VALUE);
        ByteArrayOutputStream os = pdfUtility.createUsersPdf(users);
        writeToResponse(response, os);
    }
}