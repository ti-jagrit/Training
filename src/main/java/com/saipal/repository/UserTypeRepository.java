package com.saipal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saipal.entity.UserType;
@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>{

}
