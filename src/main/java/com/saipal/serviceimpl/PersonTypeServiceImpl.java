package com.saipal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saipal.entity.PersonType;
import com.saipal.repository.PersonTypeRepository;
import com.saipal.service.PersonTypeService;

@Service
public class PersonTypeServiceImpl implements PersonTypeService {
	@Autowired
	private PersonTypeRepository repository;

	@Override
	public PersonType savePersonType(PersonType personType) {
		// TODO Auto-generated method stub
		return repository.save(personType);
	}

	@Override
	public PersonType updatePersonType(PersonType personType) {
		// TODO Auto-generated method stub
		return repository.save(personType);
	}

	@Override
	public List<PersonType> findAllPersonTypes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public PersonType findPersonTypeById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void deletePersonType(Long id) {
//		PersonType personType= ;
		
		repository.delete(repository.findById(id).get());
		
//		repository.delete();
	}

}
