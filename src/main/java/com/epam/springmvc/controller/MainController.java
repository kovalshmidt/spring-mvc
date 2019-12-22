package com.epam.springmvc.controller;


import com.epam.springmvc.model.PhoneCompany;
import com.epam.springmvc.model.PhoneUserAccount;
import com.epam.springmvc.model.PhoneUserInfo;
import com.epam.springmvc.model.User;
import com.epam.springmvc.model.viewmodel.UserViewModel;
import com.epam.springmvc.service.PhoneCompanyService;
import com.epam.springmvc.service.PhoneUserAccountService;
import com.epam.springmvc.service.PhoneUserInfoService;
import com.epam.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private PhoneUserInfoService phoneUserInfoService;
    private UserService userService;
    private PhoneCompanyService phoneCompanyService;
    private PhoneUserAccountService phoneUserAccountService;


    private static int loggedUserId;
    private static boolean isAdmin;

    @Autowired
    public MainController(PhoneUserInfoService phoneUserInfoService,
                          UserService userService, PhoneCompanyService phoneCompanyService,
                          PhoneUserAccountService phoneUserAccountService) {
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
    @GetMapping(value = "/users") //   /users?order=asc&limit=5
    public String getAllPhoneUsers(@RequestParam(required = false, value = "order") String order,
                                   @RequestParam(required = false, value = "limit") Integer limit,
                                   Model model) {

        List<PhoneUserInfo> phoneUserInfos = new ArrayList<>();
        order = order == null ? "ASC" : order;
        List<User> all = userService.findAll();
        List<Integer> usersIds = all.stream().map(User::getId).collect(Collectors.toList());
        for (Integer id : usersIds) {
            phoneUserInfos.add(phoneUserInfoService.createByUserId(id));
        }

        // Show only users with phone numbers
        phoneUserInfos = phoneUserInfos.stream().filter(p -> !p.getPhoneInfoSet().isEmpty()).collect(Collectors.toList());

        //Order
        phoneUserInfos.sort(order.equalsIgnoreCase("ASC")
                ? Comparator.comparing(PhoneUserInfo::getName, String.CASE_INSENSITIVE_ORDER)
                : Comparator.comparing(PhoneUserInfo::getName, String.CASE_INSENSITIVE_ORDER).reversed());

        //Limit
        if(limit != null) {
            phoneUserInfos = phoneUserInfos.stream().limit(limit).collect(Collectors.toList());
        }

        //All operators
        List<String> operators = phoneCompanyService.findAll().stream()
                .map(PhoneCompany::getCompanyName).collect(Collectors.toList());

        model.addAttribute("phoneUserInfos", phoneUserInfos);
        model.addAttribute("operators", operators);
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
    public ResponseEntity<List<User>> getUsersPDF() {

        String filename = "users.pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(userService.findAll(), headers, HttpStatus.OK);
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

    @PostMapping(value = "/change_operator")
    public String changeOperator(@ModelAttribute PhoneUserInfo.PhoneInfo phoneInfo) throws Exception {

        phoneUserAccountService.changeMobileOperator(phoneInfo.getPhoneNumber(), phoneInfo.getPhoneCompany());
        int phoneUserId = phoneUserAccountService.getPhoneUserAccountByPhoneNumber(phoneInfo.getPhoneNumber()).getUserId();

        return "redirect:user/" + phoneUserId;
    }

    /**
     * For RestClient
     */
    @ResponseBody
    @GetMapping(value = "/rest/user/{id}", headers = "Accept=application/json, application/pdf")
    public List<User> getUser(@PathVariable("id") int id) {

        return Collections.singletonList(userService.getById(id));
    }

    /**
     * For RestClient
     */
    @ResponseBody
    @GetMapping(value = "/rest/users", headers = "Accept=application/json, application/pdf")
    public List<User> getUsers() {

        return userService.findAll();
    }

    /**
     * The url to send data with credentials of a new user
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String signUp(@ModelAttribute UserViewModel userViewModel) {
        User user = new User();
        user.setName(userViewModel.getName());
        user.setSurname(userViewModel.getSurname());
        user.setEmail(userViewModel.getEmail());

        int userId = userService.save(user);

        PhoneUserAccount phoneUserAccount = new PhoneUserAccount();
        phoneUserAccount.setPhoneNumber(userViewModel.getPhoneNumber());
        phoneUserAccount.setUserId(userId);
        phoneUserAccount.setAmount(userViewModel.getAmount());
        phoneUserAccount.setPhoneCompanyId(phoneCompanyService.getByCompanyName(userViewModel.getPhoneCompany()).getId());

        phoneUserAccountService.save(phoneUserAccount);

        return "redirect:/users";
    }
}
