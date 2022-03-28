package com.khamutov.services;

import com.khamutov.entities.CartItem;
import com.khamutov.dao.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartService {
    private final CartDao cartDao;

    @Autowired
    public CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public void addToCart(CartItem cartItem, String userName) {
        cartDao.addToCart(cartItem, userName);
    }

    public List<CartItem> getUserCart(String userName) {
        return cartDao.getUserCart(userName);
    }

    public void deleteProductFromCart(String userName, String productName) {
        cartDao.deleteFromCart(userName, productName);
    }

}
