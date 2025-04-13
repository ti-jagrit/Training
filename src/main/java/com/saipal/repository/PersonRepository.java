package com.saipal.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saipal.entity.Person;
import com.saipal.entity.PersonType;
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	List<Person> findByPersonType(PersonType personType);
  Person findByEmail(String email);
  Page<Person> findBy(Pageable pageable);
  
 
}
