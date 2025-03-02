package com.saipal.service;

import java.util.List;

import com.saipal.entity.PersonType;

public interface PersonTypeService {
	public PersonType savePersonType(PersonType personType);
	public PersonType updatePersonType(PersonType personType);
	public List<PersonType> findAllPersonTypes();
	public PersonType findPersonTypeById(Long id);
	public void deletePersonType(Long id);
	

}
