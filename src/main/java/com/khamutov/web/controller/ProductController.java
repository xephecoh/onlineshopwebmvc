package com.khamutov.web.controller;

import com.khamutov.entities.CartItem;
import com.khamutov.entities.Product;
import com.khamutov.entities.Session;
import com.khamutov.services.CartService;
import com.khamutov.services.ProductService;
import com.khamutov.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private final ProductService service;
    private final CartService cartService;


    @Autowired
    public ProductController(ProductService service, CartService cartService) {
        this.service = service;
        this.cartService = cartService;
    }

    @RequestMapping(value = "/products1", method = RequestMethod.GET)
    public String getAllProducts1(ModelMap modelMap) {
        modelMap.addAttribute("products", List.of(Product.builder()
                .name("Ball")
                .price(12)
                .build()));
        return "products";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts(ModelMap modelMap) {
        List<Product> productList = service.getProductList();
        modelMap.addAttribute("products", productList);
        return "products";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String getAllProducts(@RequestParam String name,
                                 @RequestParam String price,
                                 HttpServletRequest request,
                                 ModelMap modelMap) {
        List<Product> productList = service.getProductList();
        CartItem cartItem = new CartItem(Product.builder()
                .price(Integer.parseInt(price))
                .name(name)
                .build(), 1);
        Session session =(Session) request.getAttribute("session");
        cartService.addToCart(cartItem, session.getUserName());
        List<CartItem> userCart = cartService.getUserCart(session.getUserName());
        modelMap.addAttribute("userCart", userCart);
        modelMap.addAttribute("products", productList);
        modelMap.addAttribute("userRole", session.getUserRole().getAuthority());
        return "products";

    }
}
