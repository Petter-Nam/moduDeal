package com.application.moduDeal.cart.service;

import java.util.List;

import com.application.moduDeal.cart.dto.CartDTO;

public interface CartService {
    public void addToCart(CartDTO cartDTO);
    public List<CartDTO> getCartItemsByUserId(String userId);
    public void deleteCartItem(int cartId);
    public CartDTO getCartItemById(int cartId); // 새로운 메서드 추가
    
}
