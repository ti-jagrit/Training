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

import com.saipal.entity.TranningSchedule;
import com.saipal.service.TranningScheduleService;
import com.saipal.utils.UniqueIdGenerator;

@RestController
@RequestMapping("api/training-schedule")
public class TranningSchaduleController {

	@Autowired
	public UniqueIdGenerator uniqueIdGenerator;

	@Autowired
	private TranningScheduleService tScheduleService;

	@PostMapping()
	public ResponseEntity<Map<String, Object>> saveTrainingSchcedule(@RequestBody TranningSchedule ts){
		Map<String, Object> response = new HashMap<>();
		try {
			ts.setId(UniqueIdGenerator.generateUniqueId());
			tScheduleService.saveTranningSchedule(ts);
			response.put("status", 1);
			response.put("message", "Training Schedule Saved.");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getAllTraningSchedule() {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("status", 1);
			response.put("data", tScheduleService.getallTranningSchedules());
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> GetTranningScheduleById(@PathVariable long id) {

		Map<String, Object> response = new HashMap<>();
		try {
			tScheduleService.getTranninSchedule(id);
			response.put("status", 1);
			response.put("data", tScheduleService.getTranninSchedule(id));
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateTrainingSchedulee(@PathVariable Long id, @RequestBody TranningSchedule tranningSchedule) {
		Map<String, Object> response = new HashMap<>();
		try {
			tScheduleService.updateTranningSchedule(tranningSchedule);
			response.put("status", 1);
			response.put("message", "Traning Schedule Updated");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteTrainingSchedule(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			tScheduleService.deleteTranningSchedule(id);
			response.put("status", 1);
			response.put("message", "Training Schedule Deleted Successfully");
		} catch (Exception e) {
			response.put("status", 0);
			response.put("message", e.getMessage());
		}

		return ResponseEntity.ok(response);

	}

}
