package com.application.moduDeal.admin.dto;

import java.util.Date;

import lombok.Data;
@Data
public class SellerProductImgDTO {
	private String imgUuid;
    private int productId;
    private String imgOriginalName;
    private Date imgAt;
}
