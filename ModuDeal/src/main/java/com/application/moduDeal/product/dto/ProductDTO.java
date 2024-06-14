package com.application.moduDeal.product.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {

	private int productId;
    private String title;
    private String description;
    private int qty;
    private int price;
    private String productCategory;
    private String userId;
    private Date productAt;
    
}
