package com.application.moduDeal.admin.dto;

import java.util.List;



import lombok.Data;

@Data
public class SellerProductDTO {
    private String userId;
    private String userName;
    private String productTitle;
    private String imageUuid;
    private String description;
    private int price;
    private int quantity;
    private int productId;
    private List<SellerProductImgDTO> sellerProductImages;
}