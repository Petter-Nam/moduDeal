package com.application.moduDeal.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.moduDeal.cart.dao.CartDAO;
import com.application.moduDeal.cart.dto.CartDTO;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Override
    public void addToCart(CartDTO cartDTO) {
        cartDAO.addToCart(cartDTO);
    }

    @Override
    public List<CartDTO> getCartItemsByUserId(String userId) {
        return cartDAO.getCartItemsByUserId(userId);
    }

    @Override
    public void deleteCartItem(int cartId) {
        cartDAO.deleteCartItem(cartId);
    }

    @Override
    public CartDTO getCartItemById(int cartId) {
        return cartDAO.getCartItemById(cartId);
    }
}