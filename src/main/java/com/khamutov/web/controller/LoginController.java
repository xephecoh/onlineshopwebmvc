package com.khamutov.web.controller;

import com.khamutov.web.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private final SecurityService securityService;

    @Autowired
    public LoginController( SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        ModelMap modelMap,
                        HttpServletResponse response) {
        boolean login = securityService.login(username, password);
        if (!login) {
            modelMap.addAttribute("rejected","true");
           return  "login";
        }
        Cookie cookieWithToken = securityService.generateCookie(username);
        Cookie userName = new Cookie("userName", username);
        response.addCookie(userName);
        response.addCookie(cookieWithToken);
        return "redirect:/products";
    }
}
