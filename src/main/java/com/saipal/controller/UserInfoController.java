package com.saipal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saipal.entity.UserInfo;
import com.saipal.service.UserInfoService;
import com.saipal.utils.UniqueIdGenerator;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/user-info")
public class UserInfoController {
	UniqueIdGenerator uniqueIdGenerator;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getUserInfos() {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", userInfoService.findalUserInfos());
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getUserInfoById(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", userInfoService.findUserInfoResponse(id));
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> saveUserInfo(@RequestBody UserInfo userInfo) {

		Map<String, Object> response = new HashMap<>();
		try {
			userInfo.setId(UniqueIdGenerator.generateUniqueId());
			userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
			userInfoService.saveUserInfo(userInfo);
			response.put("status", 1);
			response.put("message", "User Info Saved Successfully.");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateUserInfo(@PathVariable Long id, @RequestBody UserInfo userInfo) {
		Map<String, Object> response = new HashMap<>();
		System.out.println(userInfo);
		try {
			UserInfo ui = userInfoService.findUserInfoById(id);
			userInfo.setPassword(ui.getPassword());
			userInfoService.updateUserInfo(userInfo);
			response.put("status", 1);
			response.put("message", "User Info Updated Succeessfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteUserInfo(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			userInfoService.deleteUserInfo(id);
			response.put("status", 1);
			response.put("message", "User Info Deleted Succeessfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/user/{name}")
	public ResponseEntity<Map<String, Object>> getUserByLoginName(@PathVariable String name) {
	    System.out.println("request: " + name);

	    Map<String, Object> response = new HashMap<>();

	    try {
	        Optional<UserInfo> user = userInfoService.findUserInfoByLoginName(name);
	        
	        if (user.isPresent()) {
	        	System.out.println(user.get().getId());
	        	UserInfo userInfo=userInfoService.findUserInfoById(user.get().getId());  
	        	userInfo.getPerson();
	            response.put("status", 1);
	            response.put("data", userInfo);
	        } else {
	            response.put("status", 0);
	            response.put("message", "User not found");
	        }

	    } catch (Exception e) {
	        response.put("status", 0);
	        response.put("message", e.getMessage());
	    }

	    return ResponseEntity.ok(response);
	}

	
	
	@PostMapping("/image/{id}")
	public ResponseEntity<Map<String, Object>> uploadUserImage(
	        @RequestParam("image") MultipartFile file, @PathVariable Long id) {
	    Map<String, Object> response = new HashMap<>();        
	    final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";
	    System.out.println("Received file: " + file.getOriginalFilename());
	    System.out.println("File size: " + file.getSize());
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        try {
        	UserInfo userInfo=userInfoService.findUserInfoById(id);
        	String path=userInfo.getLoginName()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        	System.out.println(path);
        String uploadPath = uploadDir.getAbsolutePath() +"/"+path;
        File uploadFile = new File(uploadPath);
            file.transferTo(uploadFile);	
            userInfo.setImage("uploads/"+path);
            userInfoService.updateUserInfo(userInfo);
	        response.put("status", 1);
	        response.put("message", "File uploaded successfully");
	    } catch (Exception e) {
	        response.put("status", 0);
	        response.put("message", e.getMessage());
	    }
	    return ResponseEntity.ok(response);
	}

	@PostMapping("/pass/{id}")
	public ResponseEntity<Map<String, Object>> ChangePassword(@PathVariable long id,
			@RequestBody Map<String, String> request) {
		System.out.println(request.get("cp")+"id:"+id);
		Map<String, Object> response = new HashMap<>();
		try {
			String cp = request.get("cp");
			String np = request.get("np");
			UserInfo userInfo = userInfoService.findUserInfoById(id);
			if (passwordEncoder.matches(cp, userInfo.getPassword())) {
				userInfo.setPassword(passwordEncoder.encode(np));
				userInfoService.updateUserInfo(userInfo);
				response.put("status", 1);
				response.put("message", "Password Changed Succeessfully");
			}else {
				response.put("status", 0);
				response.put("message", "Current Password Doesnt Match");
			}

		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}
}
