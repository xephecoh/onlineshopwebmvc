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
    private final String LOGIN = "login";
    private final String LOGIN_URI = "login";
    private final String SEND_REDIRECT = "redirect:/products";


    @Autowired
    public LoginController( SecurityService securityService) {
        this.securityService = securityService;
    }

    @RequestMapping(value = LOGIN_URI, method = RequestMethod.GET)
    public String login() {
        return LOGIN;
    }

    @RequestMapping(value = LOGIN_URI, method = RequestMethod.POST)
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        ModelMap modelMap,
                        HttpServletResponse response) {
        boolean login = securityService.login(username, password);
        if (!login) {
            modelMap.addAttribute("rejected","true");
           return  LOGIN;
        }
        Cookie cookieWithToken = securityService.generateCookie(username);
        Cookie userName = new Cookie("userName", username);
        response.addCookie(userName);
        response.addCookie(cookieWithToken);
        return SEND_REDIRECT;
    }
}
