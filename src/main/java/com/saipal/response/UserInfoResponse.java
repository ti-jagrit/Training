package com.saipal.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.saipal.entity.Person;
import com.saipal.entity.UserType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {

	@JsonProperty("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private long id;
	private String loginName;
	private Person person;
	private UserType userType;
	private int userStatus;

}
