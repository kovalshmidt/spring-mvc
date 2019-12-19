package com.epam.springmvc.utility;

import com.epam.springmvc.model.PhoneUserAccount;
import com.epam.springmvc.model.User;
import com.epam.springmvc.service.PhoneCompanyService;
import com.epam.springmvc.service.PhoneUserAccountService;
import com.epam.springmvc.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
        ----------------------------------------
        |FullName | PhoneNumber | PhoneCompany |
        ----------------------------------------
        |fullName | PhoneNumber1| PhoneCompany1|
                  ------------------------------
        |         | PhoneNumber2| PhoneCompany2|
                  ------------------------------
        |         | PhoneNumber3| PhoneCompany3|
        ----------------------------------------
        -/-------/-------/-------/-------/-------/
         */

@Component
public class PdfUtility {

    public static final String FILE_NAME = "itext.pdf";

    private PhoneUserAccountService phoneUserAccountService;
    private PhoneCompanyService phoneCompanyService;

    @Autowired
    public PdfUtility(PhoneUserAccountService phoneUserAccountService, PhoneCompanyService phoneCompanyService) {
        this.phoneUserAccountService = phoneUserAccountService;
        this.phoneCompanyService = phoneCompanyService;
    }

    public Document createUsersPdf(List<User> users) {
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
            p2.add(createFirstTableForUsers(users));

            document.add(p2);
            document.close();

            System.out.println("Pdf created");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    private PdfPTable createFirstTableForUsers(List<User> users) {

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

        for (User user : users) {
            int userId = user.getId();
            int countOfNumbers = phoneUserAccountService.getNumberOfPhonesNumbersByUserId(userId);
            if (countOfNumbers == 0) {
                continue; //if the user has no phone numbers it is not showed in the phone directory
            }
            cell = new PdfPCell(new Phrase(user.getName() + " " + user.getSurname()));
            cell.setRowspan(countOfNumbers);
            table.addCell(cell);

            List<PhoneUserAccount> phoneUserAccounts = phoneUserAccountService.getPhoneNumbersByPhoneUserId(userId);

            for (PhoneUserAccount phoneUserAccount : phoneUserAccounts) {
                String companyName = phoneCompanyService.getById(phoneUserAccount.getPhoneCompanyId()).getCompanyName();

                table.addCell(phoneUserAccount.getPhoneNumber());
                table.addCell(companyName);
            }
        }
        return table;
    }
}
