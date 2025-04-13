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

import com.saipal.entity.Tranning;
import com.saipal.entity.TranningSchedule;
import com.saipal.request.TrainingScheduleDto;
import com.saipal.service.TranningScheduleService;
import com.saipal.service.TranningService;

@RestController
@RequestMapping("api/training-schedule")
public class TranningSchaduleController {

	@Autowired
	private TranningScheduleService tScheduleService;
	
	@Autowired
	private TranningService tranningService;

	@PostMapping()
	public ResponseEntity<Map<String, Object>> saveTrainingSchcedule(@RequestBody TrainingScheduleDto tsDto){
		Map<String, Object> response = new HashMap<>();
		System.out.println(tsDto);
		try {
			TranningSchedule tranningSchedule=tScheduleService.convertDto(tsDto);
			tScheduleService.saveTranningSchedule(tranningSchedule);
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

	@GetMapping("/training/{id}")
	public ResponseEntity<Map<String, Object>> GetScheduleByTraining(@PathVariable long id) {

		Map<String, Object> response = new HashMap<>();
		try {
			Tranning tranning=tranningService.getTranningById(id);
			response.put("status", 1);
			response.put("data", tScheduleService.getTrainingScheduleByTraining(tranning));
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
	public ResponseEntity<Map<String, Object>> updateTrainingSchedulee(@PathVariable Long id, @RequestBody TrainingScheduleDto tsDto) {
		Map<String, Object> response = new HashMap<>();
		TranningSchedule tSchedule=tScheduleService.convertDto(tsDto);
		try {
			tScheduleService.updateTranningSchedule(tSchedule);
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
