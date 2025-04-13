package com.saipal.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.saipal.entity.Person;
import com.saipal.entity.PersonType;
import com.saipal.repository.PersonRepository;
import com.saipal.request.PersonDto;
import com.saipal.service.PersonService;
import com.saipal.service.PersonTypeService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;
	@Autowired
	private PersonTypeService personTypeService;

	@Override
	public Person savePerson(Person person) {
		return repository.save(person);
	}

	@Override
	public List<Person> findAllPersons() {
		return repository.findAll();
	}

	@Override
	public Person findPersonsById(Long id) {
		return repository.findById(id).get();

	}

	@Override
	public boolean deletePerson(Long id) {
		repository.deleteById(id);
		Optional<Person> person = repository.findById(id);
		if (person != null) {
			return true;
		}
		return false;

	}

	@Override
	public Person updatePerson(Person person) {

		return repository.save(person);

		// TODO Auto-generated method stub
	}

	@Override
	public boolean emailExist(String email) {
		if (repository.findByEmail(email) == null) {

			return false;
		}
		return true;
	}

	@Override
	public Person covetDto(PersonDto personDto) throws Exception {
		Person person = new Person();
		if (personDto.getId() != null) {
			person.setId(Long.parseLong(personDto.getId()));
		}
		person.setFullName(personDto.getFullName());
		person.setAddress(personDto.getAddress());
		person.setEmail(personDto.getEmail());
		person.setGender(personDto.getGender());
		person.setInstitution(personDto.getInstitution());
		person.setMobileNo(personDto.getMobileNo());
		PersonType pType = personTypeService.findPersonTypeById(Long.parseLong(personDto.getPersonType()));
		person.setPersonType(pType);
		return person;
	}

	@Override
	public Page<Person> getAllPersonspage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		return repository.findAll(pageable);
	}

	@Override
	public boolean findByPersonType(PersonType personType) {
	List<Person> pList=repository.findByPersonType(personType);
	if(pList.isEmpty())
		return false;
	else {
		return true;
	}
	}

}
