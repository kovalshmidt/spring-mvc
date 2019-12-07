package com.epam.springmvc.utility;

import com.epam.springmvc.model.PhoneNumber;
import com.epam.springmvc.model.PhoneUser;
import com.epam.springmvc.service.PhoneCompanyService;
import com.epam.springmvc.service.PhoneNumberService;
import com.epam.springmvc.service.PhoneUserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PdfUtility {

    public static final String FILE_NAME = "itext.pdf";

    private PhoneUserService phoneUserService;
    private PhoneNumberService phoneNumberService;
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    public PdfUtility(PhoneUserService phoneUserService, PhoneNumberService phoneNumberService, PhoneCompanyService phoneCompanyService) {
        this.phoneUserService = phoneUserService;
        this.phoneNumberService = phoneNumberService;
        this.phoneCompanyService = phoneCompanyService;
    }

    public Document createUsersPdf() {
        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            document.open();

            Paragraph p = new Paragraph();
            p.add("Phone Directory");
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20f);

            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add(createFirstTable());

            document.add(p2);
            document.close();

            System.out.println("Pdf created");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    public PdfPTable createFirstTable() {

        PdfPTable table = new PdfPTable(3);
        PdfPCell cell;

        Font f = new Font();
        f.setStyle(Font.BOLD);
        f.setSize(12);

        cell = new PdfPCell(new Phrase("FullName", f));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("PhoneNumber", f));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("PhoneCompany", f));
        table.addCell(cell);

        List<PhoneUser> phoneUsers = phoneUserService.findAll();
        for(PhoneUser phoneUser : phoneUsers) {
            int userId = phoneUser.getId();
            int countOfNumbers = phoneUserService.getNumberOfPhonesNumbersById(userId);
            cell = new PdfPCell(new Phrase(phoneUser.getFullName()));
            cell.setRowspan(countOfNumbers);
            table.addCell(cell);

            List<PhoneNumber> phoneNumbers = phoneNumberService.getPhoneNumbersByPhoneUserId(userId);

            for(PhoneNumber phoneNumber : phoneNumbers) {
                String companyName = phoneCompanyService.getById(phoneNumber.getPhoneCompanyId()).getCompanyName();

                table.addCell(phoneNumber.getPhoneNumberValue());
                table.addCell(companyName);
            }
        }
        return table;
    }
}
