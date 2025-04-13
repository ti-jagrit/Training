package com.saipal.service;

import java.util.List;
import java.util.Optional;

import com.saipal.entity.UserInfo;
import com.saipal.entity.UserType;
import com.saipal.response.UserInfoResponse;

public interface UserInfoService {
	public UserInfoResponse saveUserInfo(UserInfo userInfo);
	public UserInfoResponse updateUserInfo(UserInfo userInfo);
	public List<UserInfo> findalUserInfos();
	public boolean findByUserType(UserType userType);
	public UserInfoResponse findUserInfoResponse(Long id);
	public UserInfo findUserInfoById(Long id);
	public Optional<UserInfo> findUserInfoByLoginName(String loginName);
	public void deleteUserInfo(Long id);
	public UserInfoResponse convertUserInfoResponse(UserInfo userInfo);

	
	

}
