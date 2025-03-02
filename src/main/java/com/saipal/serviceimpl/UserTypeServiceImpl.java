package com.saipal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saipal.entity.UserType;
import com.saipal.repository.UserTypeRepository;
import com.saipal.service.UserTyperService;

@Service
public class UserTypeServiceImpl implements UserTyperService {
	
	@Autowired
	private UserTypeRepository repository;

	@Override
	public UserType saveUserType(UserType userType) {
		// TODO Auto-generated method stub
		return repository.save(userType);
	}

	@Override
	public UserType updateUserType(UserType userType) {
		// TODO Auto-generated method stub
		return repository.save(userType);
	}

	@Override
	public List<UserType> findAllUserTypes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public UserType findUserTypeById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void deleteUserType(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		
	}

}
