package com.application.moduDeal.admin.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeDTO {

	private int noticeId;
	private String adminId;
	private String title;
	private String content;
	private Date createdAt;
	
}
