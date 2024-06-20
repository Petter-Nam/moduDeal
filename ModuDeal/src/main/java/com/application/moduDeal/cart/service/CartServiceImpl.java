package com.application.moduDeal.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.moduDeal.cart.dao.CartDAO;

@Service
public class CartServiceImpl implements CartService {

	
	@Autowired
	private CartDAO cartDAO;
}
