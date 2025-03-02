package com.saipal.controller;

import java.util.List;

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
	public ResponseEntity<UserType> addUserType(@RequestBody UserType userType) {
		userType.setId(UniqueIdGenerator.generateUniqueId());
		return ResponseEntity.ok(userTyperService.saveUserType(userType));
	}

	@GetMapping()
	public ResponseEntity<List<UserType>> GetALlUserType() {
		return ResponseEntity.ok(userTyperService.findAllUserTypes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> GetUserTypeById(@PathVariable long id) throws Exception {
		return ResponseEntity.ok(userTyperService.findUserTypeById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserType(@PathVariable Long id, @RequestBody UserType userType) throws Exception {
		return ResponseEntity.ok(userTyperService.updateUserType(userType));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserType(@PathVariable long id) throws Exception {
		UserType userType = userTyperService.findUserTypeById(id);
		if (userType == null) {
			return ResponseEntity.badRequest().body("User Type Doesnt exists with Id: " + id);
		} else {
			userTyperService.deleteUserType(id);
			return ResponseEntity.ok("User Type Deleted");
		}
	}
}
