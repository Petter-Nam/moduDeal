package com.application.moduDeal.review.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeReviewDTO {

	private int reviewId;
	private int noticeId;
	private String userId;
	private String content;
	private Date createdAt;
}
