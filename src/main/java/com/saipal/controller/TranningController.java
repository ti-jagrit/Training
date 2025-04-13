package com.saipal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saipal.entity.Tranning;
import com.saipal.service.TranningService;
import com.saipal.utils.UniqueIdGenerator;

@RestController
@RequestMapping("api/training")


public class TranningController {

	@Autowired
	public UniqueIdGenerator uniqueIdGenerator;

	@Autowired
	private TranningService tranningService;

	@PostMapping()
	public ResponseEntity<Map<String, Object>> AddTraining(@RequestBody Tranning training) {
		Map<String, Object> response = new HashMap<>();
		try {
			training.setId(UniqueIdGenerator.generateUniqueId());
			tranningService.saveTranning(training);
			response.put("status", 1);
			response.put("message", "Tranning Saved Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<Map<String, Object>> AllTraining() {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", tranningService.getallTrannings());
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Map<String, Object>> AllTraining(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Map<String, Object> response = new HashMap<>();
		try {
			Page<Tranning> traningPage=tranningService.getAllTranningPage(page, size);
			response.put("data", traningPage.getContent());
			response.put("currentPage", traningPage.getNumber());
			response.put("totalPages", traningPage.getTotalPages());
			response.put("totalElements", traningPage.getTotalElements());
			response.put("pageSize", traningPage.getSize());			
			response.put("status", 1);
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}	
	
	

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getTrainingById(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", tranningService.getTranningById(id));
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateTraining(@PathVariable Long id, @RequestBody Tranning tranning){
		Map<String, Object> response = new HashMap<>();
		try {
			tranningService.updateTranning(tranning);
			response.put("status", 1);
			response.put("message","Traning Updated Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}
		

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deletetraining(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			tranningService.deleteTranning(id);
			response.put("status", 1);
			response.put("message","Traning Deleted Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}
}
