package com.application.moduDeal.product.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProductImgDTO {
	
	private String imgUuid;
    private int productId;
    private String imgOriginalName;
    private Date imgAt;
    
}
