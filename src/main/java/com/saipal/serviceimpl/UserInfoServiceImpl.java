package com.saipal.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saipal.entity.UserInfo;
import com.saipal.entity.UserType;
import com.saipal.repository.UserInfoRepository;
import com.saipal.response.UserInfoResponse;
import com.saipal.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoRepository repository;

	
	@Override
	public UserInfoResponse saveUserInfo(UserInfo userInfo) {
		UserInfo savedUserInfo = repository.save(userInfo);

		return convertUserInfoResponse(savedUserInfo);
	}

	@Override
	public List findalUserInfos() {
	    return repository.findAll().stream()
	            .map(userInfo -> {
	                UserInfoResponse userInfoResponse = new UserInfoResponse();
	                userInfoResponse.setId(userInfo.getId());
	                userInfoResponse.setLoginName(userInfo.getLoginName());
	                userInfoResponse.setPerson(userInfo.getPerson());
	                userInfoResponse.setUserStatus(userInfo.getUserStatus());
	                userInfoResponse.setUserType(userInfo.getUserType());
	                return userInfoResponse;
	            })
	            .collect(Collectors.toList());
	}
	
	@Override
	public UserInfoResponse convertUserInfoResponse(UserInfo userInfo) {
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setId(userInfo.getId());
		userInfoResponse.setLoginName(userInfo.getLoginName());
		userInfoResponse.setPerson(userInfo.getPerson());
		userInfoResponse.setUserStatus(userInfo.getUserStatus());
		userInfoResponse.setUserType(userInfo.getUserType());
		return userInfoResponse;
	}

	@Override
	public UserInfoResponse updateUserInfo(UserInfo userInfo) {
	    Optional<UserInfo> existingUserInfo = repository.findByPerson(userInfo.getPerson());

	    if (existingUserInfo.isPresent() && existingUserInfo.get().getId() != userInfo.getId()) {
	        throw new IllegalStateException("This person is already associated with another user.");
	    }
		UserInfo user = repository.save(userInfo);
		return convertUserInfoResponse(user);
	}

	

	@Override
	public UserInfoResponse findUserInfoResponse(Long id) {
		// TODO Auto-generated method stub
		return convertUserInfoResponse(repository.findById(id).get());
	}

	@Override
	public Optional<UserInfo> findUserInfoByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findByLoginName(loginName)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with given Name:")));
	}

	@Override
	public void deleteUserInfo(Long id) {
		repository.deleteById(id);

	}

	@Override
	public UserInfo findUserInfoById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public boolean findByUserType(UserType userType) {
		List<UserInfo> user= repository.findByUserType(userType);
		if(user.isEmpty()) {
			return false;
		}
		return true;
	}

}
