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

import com.saipal.entity.PersonType;
import com.saipal.service.PersonService;
import com.saipal.service.PersonTypeService;
import com.saipal.utils.UniqueIdGenerator;

@RestController
@RequestMapping("api/person-type")
public class PersonTypeController {

	@Autowired
	public UniqueIdGenerator uniqueIdGenerator;

	@Autowired
	private PersonTypeService personTypeService;

	@Autowired
	private PersonService personService;

	@PostMapping()
	public ResponseEntity<Map<String, Object>> AddPersonType(@RequestBody PersonType personType) throws Exception {

		Map<String, Object> response = new HashMap<>();
		try {
			personType.setId(UniqueIdGenerator.generateUniqueId());
			personTypeService.savePersonType(personType);
			response.put("status", 1);
			response.put("message", "Person Type Saved Successfully");
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
			response.put("data", personTypeService.findAllPersonTypes());
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getPersonById(@PathVariable long id) throws Exception {

		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", personTypeService.findPersonTypeById(id));
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updatePerson(@PathVariable long id, @RequestBody PersonType personType)
			throws Exception {

		Map<String, Object> response = new HashMap<>();
		try {
			personTypeService.updatePersonType(personType);
			response.put("status", 1);
			response.put("message", "Person Type Update Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable long id) throws Exception {
		Map<String, Object> response = new HashMap<>();
		try {
			System.out.println(id);
			if (!personService.findByPersonType(personTypeService.findPersonTypeById(id))) {
				personTypeService.deletePersonType(id);
				response.put("status", 1);
				response.put("message", "Person Type Deleted Successfully");
			} else {
				response.put("status", 0);
				response.put("message", "Cannot Delete, Person Exist in Person Type");
			}
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

}
