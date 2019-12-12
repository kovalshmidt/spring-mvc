package com.epam.springmvc.controller;


import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneUserInfo;
import com.epam.springmvc.model.User;
import com.epam.springmvc.service.PhoneCompanyService;
import com.epam.springmvc.service.PhoneUserAccountService;
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
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.springmvc.utility.PdfUtility.FILE_NAME;

@Controller
public class MainController {

    private PdfUtility pdfUtility;
    private PhoneUserInfoService phoneUserInfoService;
    private UserService userService;
    private PhoneCompanyService phoneCompanyService;
    private PhoneUserAccountService phoneUserAccountService;


    private static int loggedUserId;
    private static boolean isAdmin;

    @Autowired
    public MainController(PdfUtility pdfUtility, PhoneUserInfoService phoneUserInfoService,
                          UserService userService, PhoneCompanyService phoneCompanyService,
                          PhoneUserAccountService phoneUserAccountService) {
        this.pdfUtility = pdfUtility;
        this.phoneUserInfoService = phoneUserInfoService;
        this.userService = userService;
        this.phoneCompanyService = phoneCompanyService;
        this.phoneUserAccountService = phoneUserAccountService;
    }

    /**
     * Main home page
     */
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        return "redirect:homePage";
    }

    /**
     * Phone directory page with all phone users, accessible[BOOKING_MANAGER]
     */
    @GetMapping(value = "/users")
    public String getAllPhoneUsers(Model model) {

        List<PhoneUserInfo> phoneUserInfos = new ArrayList<>();
        List<Integer> usersIds = userService.findAll().stream().map(User::getId).collect(Collectors.toList());
        for (Integer id : usersIds) {
            phoneUserInfos.add(phoneUserInfoService.createByUserId(id));
        }

        model.addAttribute("phoneUserInfos", phoneUserInfos);

        model.addAttribute("admin", isAdmin);
        model.addAttribute("loggedUserId", loggedUserId);
        return "usersList";
    }

    /**
     * User page with user info, accessible [REGISTERED_USER, BOOKING_MANAGER]
     */
    @GetMapping(value = "/user/{id}")
    public String getById(@PathVariable("id") int id, Model model) {

        PhoneUserInfo phoneUserInfo = phoneUserInfoService.createByUserId(id);
        List<String> operators = phoneCompanyService.findAll().stream()
                .map(PhoneCompany::getCompanyName).collect(Collectors.toList());

        model.addAttribute("phoneUserInfo", phoneUserInfo);
        model.addAttribute("hasPhones", phoneUserInfo.getPhoneUserId() != 0);
        model.addAttribute("operators", operators);

        model.addAttribute("admin", isAdmin);
        model.addAttribute("loggedUserId", loggedUserId);
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
    public String toUploadPage(Model model) {

        model.addAttribute("admin", isAdmin);
        model.addAttribute("loggedUserId", loggedUserId);
        return "uploadPage";
    }


    @GetMapping(value = "/homePage")
    public String homePage(Principal principal, Model model) {

        loggedUserId = userService.findByEmail(principal.getName()).getId();
        PhoneUserInfo phoneUserInfo = phoneUserInfoService.createByUserId(loggedUserId);

        isAdmin = phoneUserInfo.getRoles().contains("BOOKING_MANAGER");

        model.addAttribute("phoneUserInfo", phoneUserInfo);

        model.addAttribute("admin", isAdmin);
        model.addAttribute("loggedUserId", loggedUserId);

        return "homePage";
    }

    @RequestMapping(value = "/change_operator", method = RequestMethod.POST)
    public String changeOperator(@ModelAttribute PhoneUserInfo.PhoneInfo phoneInfo) {
        phoneUserAccountService.changeMobileOperator(phoneInfo.getPhoneNumber(), phoneInfo.getPhoneCompany());
        return "redirect:user/" + loggedUserId;
    }

}
