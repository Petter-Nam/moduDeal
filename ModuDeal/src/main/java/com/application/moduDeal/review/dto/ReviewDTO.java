package com.application.moduDeal.review.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
    private int reviewId;
    private String userId;
    private int productId;
    private String review;
    private Date reviewDate;
}
