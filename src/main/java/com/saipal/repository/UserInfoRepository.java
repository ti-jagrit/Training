package com.saipal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saipal.entity.Person;
import com.saipal.entity.UserInfo;
import com.saipal.entity.UserType;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	public Optional<UserInfo> findByLoginName(String loginName);
	List<UserInfo> findByUserType(UserType userType);

	public Optional<UserInfo> findByPerson(Person person);

}
