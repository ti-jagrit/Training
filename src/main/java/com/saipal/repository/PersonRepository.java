package com.saipal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saipal.entity.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
  Person findByEmail(String email);
  
 
}
