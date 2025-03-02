package com.saipal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saipal.entity.Person;
import com.saipal.entity.PersonType;
import com.saipal.service.PersonService;
import com.saipal.service.PersonTypeService;
import com.saipal.utils.UniqueIdGenerator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/person")
public class PersonContoller {

	@Autowired
	private PersonService personService;
	@Autowired
	private PersonTypeService personTypeService;
//
//	@PostMapping()
////	public ResponseEntity<?> AddPersson(@RequestBody Person person) throws Exception {
//	public ResponseEntity<?> AddPersson(@RequestBody Map<String, Object> requestBody) throws Exception {
//		if (personService.emailExist(requestBody.get("email").toString())) {
//			return ResponseEntity.badRequest().body("Person Already Exits");
//		}
//		Person person = new Person();
//		PersonType personType = new PersonType();
//		long ptid = Long.parseLong(requestBody.get("pt").toString());
//		personType = personTypeService.findPersonTypeById(ptid);
//
//		person.setPersonType(personType);
//		person.setId(UniqueIdGenerator.generateUniqueId());
//		person.setEmail(requestBody.get("email").toString());
//		person.setAddress(requestBody.get("address").toString());
//		person.setFullName(requestBody.get("fullName").toString());
//		person.setInstitution(requestBody.get("institution").toString());
//		person.setMobileNo(requestBody.get("mobileNo").toString());
//		person.setGender(Integer.parseInt(requestBody.get("gender").toString()));
//
//		return ResponseEntity.ok(personService.savePerson(person));
//	}
//
////		System.out.println(person.getpt);
//////		String reqpt = person.getPersonType().toString();
//////		PersonType pt = personTypeService.findPersonTypeById(Long.parseLong(reqpt));
////		person.setId(UniqueIdGenerator.generateUniqueId());
//////		person.setPersonType(pt);
////		return ResponseEntity.ok(personService.savePerson(person));

	@GetMapping()
	public ResponseEntity<List<Person>> allPersons() throws Exception {
		return ResponseEntity.ok(personService.findAllPersons());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable long id) throws Exception {
		Person person = personService.findPersonsById(id);
		if (person != null)
			return ResponseEntity.ok(person);
		else {
			return ResponseEntity.badRequest().body("User Not Found with id" + id);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable long id, @RequestBody Person person) throws Exception {
		Person p = personService.updatePerson(person);

		return ResponseEntity.ok(p);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable long id) throws Exception {
		if (personService.deletePerson(id)) {
			return ResponseEntity.ok("Person Deleted");
		}
		return ResponseEntity.badRequest().body("cannot delete Person");

	}

}
