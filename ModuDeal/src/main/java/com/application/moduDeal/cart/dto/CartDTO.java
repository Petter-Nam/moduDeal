package com.application.moduDeal.cart.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CartDTO {

	private int cartId;
    private String userId;
    private int productId;
    private int qty;
    private String title;
    private String imgUuid; 
    private int price; 
    private Date addedAt;
	
}