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
import java.util.List;

@Component
public class PdfUtility {

    public static final String FILE_NAME = "itext.pdf";

    private PhoneUserAccountService phoneUserAccountService;
    private PhoneCompanyService phoneCompanyService;
    private UserService userService;

    @Autowired
    public PdfUtility(PhoneUserAccountService phoneUserAccountService, PhoneCompanyService phoneCompanyService,
                      UserService userService) {
        this.phoneUserAccountService = phoneUserAccountService;
        this.phoneCompanyService = phoneCompanyService;
        this.userService = userService;
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

        List<User> users = userService.findAll();
        for (User user : users) {
            int userId = user.getId();
            int countOfNumbers = phoneUserAccountService.getNumberOfPhonesNumbersByUserId(userId);
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
