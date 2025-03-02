package com.saipal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saipal.entity.PersonType;
import com.saipal.service.PersonTypeService;
import com.saipal.utils.UniqueIdGenerator;

@RestController
@RequestMapping("api/person-type")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonTypeController {
	
	@Autowired
	public UniqueIdGenerator uniqueIdGenerator;


	@Autowired
	private PersonTypeService personTypeService;

	@PostMapping()
	public ResponseEntity<PersonType> AddPersson(@RequestBody PersonType personType)throws Exception {

		personType.setId(UniqueIdGenerator.generateUniqueId());
		return ResponseEntity.ok(personTypeService.savePersonType(personType));
	}

	@GetMapping()
//	public ResponseEntity<List<PersonType>> allPersons() throws Exception{
	
	//for converting uuid to string for json request and response
		public ResponseEntity<?> allPersons() throws Exception{
		
		  try {
			 List<Map<String, Object>> result= personTypeService.findAllPersonTypes()
					 .stream().map(personType -> {
			            Map<String, Object> response = new HashMap<>();
			            response.put("id", String.valueOf(personType.getId())); 
			            response.put("code", personType.getCode());
			            response.put("personType", personType.getPersonType());
			            return response;
			        })
			        .collect(Collectors.toList());
			 return ResponseEntity.ok(result);
		  }catch (Exception e) {
			    Map<String, String> errorResponse = new HashMap<>();
		        errorResponse.put("error", "Failed to fetch person types");
		        errorResponse.put("details", e.getMessage());
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);		}
//		return ResponseEntity.ok(personTypeService.findAllPersonTypes());
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable long id) throws Exception{
	try {
		PersonType personType=personTypeService.findPersonTypeById(id);
		Map<String, Object> response = new HashMap<>();
        response.put("id", String.valueOf(personType.getId()));
        response.put("code", personType.getCode());
        response.put("personType", personType.getPersonType());

        return ResponseEntity.ok(response);
    } catch (Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "PersonType not found");
        errorResponse.put("details", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        
    }
			
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable long id, @RequestBody PersonType personType) throws Exception{
		PersonType p = personTypeService.updatePersonType(personType);

		return ResponseEntity.ok(p);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable long id) throws Exception{
		personTypeService.deletePersonType(id);
			return ResponseEntity.ok("Person Type Deleted");

	}
	

}
