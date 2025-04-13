package com.saipal.request;

import lombok.Data;

@Data
public class UserInfoDto {
	private String id;
	private String person;
	private String loginName;
	private String userType;
	private int status;

}
