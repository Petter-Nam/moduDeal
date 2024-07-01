package com.application.moduDeal.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.moduDeal.cart.dto.CartDTO;

@Mapper
public interface CartDAO {
	public void addToCart(CartDTO cartDTO);

	public List<CartDTO> getCartItemsByUserId(String userId);
	
	public void deleteCartItem(int cartId);
	
	public CartDTO getCartItemById(int cartId);
	
	
}
