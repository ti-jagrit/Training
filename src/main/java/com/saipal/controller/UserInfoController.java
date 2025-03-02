package com.saipal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saipal.entity.UserInfo;
import com.saipal.response.UserInfoResponse;
import com.saipal.service.UserInfoService;
import com.saipal.utils.UniqueIdGenerator;

@RestController
@RequestMapping("api/user-info")
public class UserInfoController {
	UniqueIdGenerator uniqueIdGenerator;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<List<?>> getUserInfos() throws Exception {
		return ResponseEntity.ok(userInfoService.findalUserInfos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserInfoById(@PathVariable long id) throws Exception {
		return ResponseEntity.ok(userInfoService.findUserInfoResponse(id));

	}

	@PostMapping
	public ResponseEntity<UserInfoResponse> saveUserInfo(@RequestBody UserInfo userInfo) throws Exception {
		userInfo.setId(UniqueIdGenerator.generateUniqueId());
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

		return ResponseEntity.ok(userInfoService.saveUserInfo(userInfo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserInfo(@PathVariable Long id, @RequestBody UserInfo userInfo) throws Exception {
		UserInfo ui = userInfoService.findUserInfoById(id);
		userInfo.setPassword(ui.getPassword());

		return ResponseEntity.ok((userInfoService.updateUserInfo(userInfo)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletetraining(@PathVariable long id) throws Exception {
		userInfoService.deleteUserInfo(id);
		return ResponseEntity.ok("User Info Deleted");

	}

	@PostMapping("/pass/{id}")
	public ResponseEntity<?> ChangePassword(@PathVariable long id, @RequestBody Map<String, String> request)
			throws Exception {
		String cp = request.get("cp");
		String np = request.get("np");

		UserInfo userInfo = userInfoService.findUserInfoById(id);
		if (passwordEncoder.matches(cp, userInfo.getPassword())) {
			userInfo.setPassword(passwordEncoder.encode(np));
			userInfoService.updateUserInfo(userInfo);
			return ResponseEntity.ok("Password Changed");
		} else {
			return ResponseEntity.badRequest().body("Password Doesnt Match");
		}

	}
}
