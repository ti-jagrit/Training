package com.saipal.service;

import java.util.List;

import com.saipal.entity.Person;

public interface PersonService {
	public Person savePerson(Person person);
	
	public List<Person> findAllPersons();
	public Person findPersonsById(Long id);
	public boolean deletePerson(Long id);
	public boolean emailExist(String email);
	public Person updatePerson(Person person);
	

}
