package com.epam.springmvc.controller;


import com.epam.springmvc.model.PhoneUserInfo;
import com.epam.springmvc.model.User;
import com.epam.springmvc.service.PhoneUserInfoService;
import com.epam.springmvc.service.UserService;
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

    private UserService userService;
    private PdfUtility pdfUtility;
    private PhoneUserInfoService phoneUserInfoService;

    @Autowired
    public MainController(UserService userService, PdfUtility pdfUtility, PhoneUserInfoService phoneUserInfoService) {
        this.userService = userService;
        this.pdfUtility = pdfUtility;
        this.phoneUserInfoService = phoneUserInfoService;
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        return "redirect:users";
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {

        List<PhoneUserInfo> phoneUserInfos = new ArrayList<>();
        List<Integer> usersIds = userService.findAll().stream().map(User::getId).collect(Collectors.toList());
        for (Integer id : usersIds) {
            phoneUserInfos.add(phoneUserInfoService.createByUserId(id));
        }

        model.addAttribute("phoneUserInfos", phoneUserInfos);
        return "usersList";
    }

    @GetMapping(value = "/user/{id}")
    public String getById(@PathVariable("id") int id, Model model) {

        model.addAttribute("phoneUserInfo", phoneUserInfoService.createByUserId(id));
        return "showUser";
    }


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

    @GetMapping(value = "/uploadPage")
    public String toUploadPage() {
        return "uploadPage";
    }

}
