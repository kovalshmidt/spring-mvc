package com.epam.springmvc.controller;


import com.epam.springmvc.model.PhoneUser;
import com.epam.springmvc.model.PhoneUserInfo;
import com.epam.springmvc.service.PhoneUserInfoService;
import com.epam.springmvc.service.PhoneUserService;
import com.epam.springmvc.utility.PdfUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.springmvc.utility.PdfUtility.FILE_NAME;

@Controller
public class MainController {

    private PhoneUserService phoneUserService;
    private PdfUtility pdfUtility;
    private PhoneUserInfoService phoneUserInfoService;

    @Autowired
    public MainController(PhoneUserService phoneUserService, PdfUtility pdfUtility, PhoneUserInfoService phoneUserInfoService) {
        this.phoneUserService = phoneUserService;
        this.pdfUtility = pdfUtility;
        this.phoneUserInfoService = phoneUserInfoService;
    }

    /**
     * Main home page
     */
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        return "homePage";
    }

    /**
     * Phone directory page with all phone users, accessible[BOOKING_MANAGER]
     */
    @GetMapping(value = "/users")
    public String getAllPhoneUsers(Model model) {

        List<PhoneUserInfo> phoneUserInfos = new ArrayList<>();
        List<Integer> usersIds = phoneUserService.findAll().stream().map(PhoneUser::getId).collect(Collectors.toList());
        for (Integer id : usersIds) {
            phoneUserInfos.add(phoneUserInfoService.createByUserId(id));
        }

        model.addAttribute("phoneUserInfos", phoneUserInfos);
        return "usersList";
    }

    /**
     * User page with user info, accessible [REGISTERED_USER, BOOKING_MANAGER]
     */
    @GetMapping(value = "/user/{id}")
    public String getById(@PathVariable("id") int id, Model model) {

        model.addAttribute("phoneUserInfo", phoneUserInfoService.createByUserId(id));
        return "showPhoneUser";
    }

    /**
     * The url to download all the phone users in pdf, accessible[BOOKING_MANAGER]
     */
    @GetMapping(value = "/getUsersPdf")
    public ResponseEntity<byte[]> getUsersPDF() throws IOException {

        pdfUtility.createUsersPdf();
        byte[] contents = Files.readAllBytes(new File(FILE_NAME).toPath());

        String filename = "users.pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    /**
     * The page on which is possible to upload a file with phone users, accessible[BOOKING_MANAGER]
     */
    @GetMapping(value = "/uploadPage")
    public String toUploadPage() {
        return "uploadPage";
    }

}
