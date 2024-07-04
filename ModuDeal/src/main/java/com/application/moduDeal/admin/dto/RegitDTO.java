package com.application.moduDeal.admin.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class RegitDTO {

	private String userId;
	private String password;
	private String name;
	private Date   birthDate;
	private String hp;
	private String email;
	private String addressZipCode;
	private String addressRoad;
	private String addressNamuji;
	private String activeYn;
	private String   inactiveAt;
	private Date   joinAt;
}
