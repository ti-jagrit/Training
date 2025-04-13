package com.saipal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.saipal.entity.Person;
import com.saipal.entity.PersonType;
import com.saipal.request.PersonDto;

public interface PersonService {
	public Person savePerson(Person person);
	
	public List<Person> findAllPersons();
	public Person findPersonsById(Long id);
	public boolean deletePerson(Long id);
	public boolean emailExist(String email);
	public Person updatePerson(Person person);
	public boolean findByPersonType(PersonType personType);
	public Person covetDto(PersonDto personDto) throws Exception;
    public Page<Person> getAllPersonspage(int page, int size);


}
