package com.saipal.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saipal.entity.Person;
import com.saipal.repository.PersonRepository;
import com.saipal.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;

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

}
