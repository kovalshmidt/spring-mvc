package com.epam.springmvc.controller;

import com.epam.springmvc.model.PhoneUser;
import com.epam.springmvc.model.User;
import com.epam.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * The url to open a sign_up page
     */
    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new PhoneUser());
        return "/auth/sign_up";
    }

    /**
     * The url to send data with credentials of a new user
     */
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUp(@ModelAttribute User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        userService.save(newUser);
        return "redirect:/users";
    }

    /**
     * The url that opens a login page
     */
    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "auth/login";
    }


}
