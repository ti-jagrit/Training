package com.saipal.request;

import lombok.Data;

@Data
public class PersonDto {
	private String id;
	private String personType;
	private String fullName;
	private int gender;
	private String address;
	private String email;
	private String mobileNo;
	private String institution;

}
