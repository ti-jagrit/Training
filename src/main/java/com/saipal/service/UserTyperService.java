package com.saipal.service;


import java.util.List;

import com.saipal.entity.UserType;

public interface UserTyperService {
	public UserType saveUserType(UserType userType);
	public UserType updateUserType(UserType userType);
	public List<UserType> findAllUserTypes();
	public UserType findUserTypeById(Long id);
	public void deleteUserType(Long id);
	

}
