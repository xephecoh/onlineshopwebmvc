package com.khamutov.web.servlets;

import com.khamutov.services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteProductServlet extends HttpServlet {
    private final ProductService service;

    public DeleteProductServlet(ProductService service) {
        this.service = service;
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        service.deleteById(Integer.parseInt(id));
        resp.sendRedirect("/products");
    }

}
