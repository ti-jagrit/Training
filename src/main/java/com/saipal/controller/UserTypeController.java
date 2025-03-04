package com.saipal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saipal.entity.UserType;
import com.saipal.service.UserTyperService;
import com.saipal.utils.UniqueIdGenerator;

@RestController
@RequestMapping("api/user-type")
public class UserTypeController {
	UniqueIdGenerator uniqueIdGenerator;

	@Autowired
	private UserTyperService userTyperService;

	@PostMapping()
	public ResponseEntity<Map<String, Object>> addUserType(@RequestBody UserType userType) {
		Map<String, Object> response = new HashMap<>();
		try {
			userType.setId(UniqueIdGenerator.generateUniqueId());
			userTyperService.saveUserType(userType);
			response.put("status", 1);
			response.put("message", "User Type Saved Succeessfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<Map<String, Object>> GetALlUserType() {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", userTyperService.findAllUserTypes());
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> GetUserTypeById(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", userTyperService.findUserTypeById(id));
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateUserType(@PathVariable Long id, @RequestBody UserType userType) {
		Map<String, Object> response = new HashMap<>();
		try {
			userTyperService.updateUserType(userType);
			response.put("status", 1);
			response.put("message", "User Type Updated Succeessfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteUserType(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			userTyperService.deleteUserType(id);
			response.put("status", 1);
			response.put("message", "User Type Deleted Succeessfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);

	}
}
