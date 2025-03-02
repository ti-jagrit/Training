package com.saipal.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String jwtToken;
	private String username;
	private List<String> roles;

	public LoginResponse(String jwtToken, String username, List<String> roles) {
		this.jwtToken = jwtToken;
		this.username = username;
		this.roles = roles;
	}

}
