package com.saipal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saipal.entity.Person;
import com.saipal.service.PersonService;
import com.saipal.utils.UniqueIdGenerator;

@RestController
@RequestMapping("api/person")
public class PersonContoller {

	@Autowired
	private PersonService personService;

	@PostMapping()
	public ResponseEntity<Map<String, Object>> AddPersson(@RequestBody Person person) throws Exception {

		Map<String, Object> response = new HashMap<>();
		if (personService.emailExist(person.getEmail())) {
			response.put("status", 0);
			response.put("message", "Person Exist with email: " + person.getEmail());
		return ResponseEntity.ok(response);
		}
		try {
			person.setId(UniqueIdGenerator.generateUniqueId());
			personService.savePerson(person);			
			response.put("status", 1);
			response.put("message", "Person Saved Successfully Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<Map<String, Object>> allPersons() throws Exception {
		Map<String, Object> response = new HashMap<>();

		try {
			response.put("status", 1);
			response.put("data", personService.findAllPersons());
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getPersonById(@PathVariable long id) throws Exception {
		Person person = personService.findPersonsById(id);
		Map<String, Object> response = new HashMap<>();

		if (person != null) {
			response.put("status", 1);
			response.put("data", person);
		} else {
			response.put("status", 0);
			response.put("message", "Person not found with id" + id);

		}
		return ResponseEntity.ok(response);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updatePerson(@PathVariable long id, @RequestBody Person person)
			throws Exception {
		Map<String, Object> response = new HashMap<>();
		try {
			personService.updatePerson(person);
			response.put("status", 1);
			response.put("message", "Person Updated Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", "Person not found with id" + id);
		}

		return ResponseEntity.ok(response);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable long id) throws Exception {
	
		Map<String, Object> response = new HashMap<>();
		try {
			personService.deletePerson(id);
			response.put("status", 1);
			response.put("message", "Person Deleted Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);

	}

}
