package com.khamutov.web.servlets;


import com.khamutov.services.UserService;
import com.khamutov.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    private final UserService userService;

    public RegisterServlet(@Autowired UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        PageGenerator.getPage("register.html", pageVariables, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        if (!password.equals(repeatPassword)) {
            resp.sendRedirect("/login");
            return;
        }
        userService.saveUser(name, password);
        resp.sendRedirect("/products");
    }
}

