package com.saipal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.saipal.config.security.JwtUtils;
import com.saipal.request.LoginRequest;
import com.saipal.response.LoginResponse;
@Controller
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	
	@PostMapping("/public/api/signin")
	public ResponseEntity<?> userAuthencation(@RequestBody LoginRequest loginRequest) {
		Authentication authentication;

		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Bad Credentials");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String jwtToken = jwtUtils.generateTokenFromUserName(userDetails);
		List<String> role = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		LoginResponse response = new LoginResponse(jwtToken, userDetails.getUsername(), role);
		return ResponseEntity.ok(response);
	}

}
