package com.khamutov.web.controller;

import com.khamutov.entities.Product;
import com.khamutov.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateController {
    private final ProductService service;
    private final String REDIRECT = "redirect:/login";

    @Autowired
    public UpdateController(ProductService service) {
        this.service = service;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateForm(@RequestParam String id,
                             @RequestParam String name,
                             @RequestParam String price,
                             ModelMap modelMap) {
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("name", name);
        modelMap.addAttribute("price", price);
        return "update";
    }

    @RequestMapping(value = "/update/{product.name}/{product.price}", method = RequestMethod.PUT)
    public String updateAction(@RequestParam String id,
                               @RequestParam String name,
                               @RequestParam String price
    ) {
        Product product = Product.builder()
                .name(name)
                .price(Integer.parseInt(price))
                .id(Integer.parseInt(id))
                .build();
        service.update(product);
        return "redirect:/login";
    }

}
