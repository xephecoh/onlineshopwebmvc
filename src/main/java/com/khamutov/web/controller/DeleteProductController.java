package com.khamutov.web.controller;


import com.khamutov.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DeleteProductController {
    private final ProductService service;

    @Autowired
    public DeleteProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "/delete/{product.id}", method = RequestMethod.POST)
    public String deleteFromCart(@PathVariable String productId) {
        service.deleteById(Integer.parseInt(productId));
        return "redirect:/products";
    }
}
