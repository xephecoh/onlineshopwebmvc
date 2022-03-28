package com.khamutov.web.controller;

import com.khamutov.entities.Product;
import com.khamutov.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddProductController {
    private final ProductService service;

    @Autowired
    public AddProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(@RequestParam String name,
                             @RequestParam String price) {
        Product newProduct = Product.builder()
                .price(Integer.parseInt(price))
                .name(name)
                .build();
        service.save(newProduct);
        return "redirect:/products";
    }
}
