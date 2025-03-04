package com.saipal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

@Controller
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/public/api/signin")
	public ResponseEntity<Map<String, Object>> userAuthentication(@RequestBody LoginRequest loginRequest) {
	    Map<String, Object> response = new HashMap<>();
	    Authentication authentication;

	    try {
	        authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	    } catch (Exception e) {
	        response.put("status", 0);
	        response.put("message", "Bad Credentials, " + e.getMessage());
	        return ResponseEntity.ok(response);
	    }

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	    String jwtToken = jwtUtils.generateTokenFromUserName(userDetails);
	    List<String> roles = userDetails.getAuthorities().stream()
	            .map(item -> item.getAuthority())
	            .collect(Collectors.toList());

	    response.put("status", 1);
	    response.put("message", "Login Successfully");
	    response.put("jwttoken", jwtToken);
	    response.put("username", userDetails.getUsername());
	    response.put("roles", roles);

	    return ResponseEntity.ok(response);
	}


}
