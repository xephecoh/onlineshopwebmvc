package com.khamutov.web.controller;

import com.khamutov.entities.Session;
import com.khamutov.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
class DeleteFromCartController {
    private final CartService cartService;

    @Autowired
    public DeleteFromCartController( CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/deleteFromCart", method = RequestMethod.POST)
    public String deleteFromCart(@RequestParam String productName,
                                 @RequestParam Session session) {
        cartService.deleteProductFromCart(productName, session.getUserName());
        return "redirect:/products";
    }
}
