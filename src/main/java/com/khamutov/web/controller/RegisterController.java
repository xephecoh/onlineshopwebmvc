package com.khamutov.web.controller;

import com.khamutov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String login(@RequestParam String name,
                        @RequestParam String repeatPassword,
                        @RequestParam String password) {
        if (!password.equals(repeatPassword)) {
            return "redirect:/login";
        }
        userService.saveUser(name, password);
        return "redirect:/login";
    }


}
