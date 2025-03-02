package com.saipal.response;

import com.saipal.entity.Person;
import com.saipal.entity.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {

	private long id;
	private String loginName;
	private Person person;
	private UserType userType;
	private int userStatus;

}
