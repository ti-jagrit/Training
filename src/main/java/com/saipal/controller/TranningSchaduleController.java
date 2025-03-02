package com.saipal.controller;

import java.util.List;

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
	public ResponseEntity<TranningSchedule> AddPersson(@RequestBody TranningSchedule ts) throws Exception {
		ts.setId(UniqueIdGenerator.generateUniqueId());
		return ResponseEntity.ok(tScheduleService.saveTranningSchedule(ts));
	}

	@GetMapping()
	public ResponseEntity<List<TranningSchedule>> allPersons() throws Exception {
		return ResponseEntity.ok(tScheduleService.getallTranningSchedules());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> GetTranningScheduleById(@PathVariable long id) throws Exception {
		TranningSchedule tSchedule = tScheduleService.getTranninSchedule(id);
		if (tSchedule != null) {
			return ResponseEntity.ok(tSchedule);
		} else {
			return ResponseEntity.badRequest().body("Tranning Schedule not Found with id: " + id);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTrainingSchedulee(@PathVariable Long id, @RequestBody TranningSchedule tranningSchedule)
			throws Exception {
		return ResponseEntity.ok(tScheduleService.updateTranningSchedule(tranningSchedule));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletetraining(@PathVariable long id) throws Exception {
		tScheduleService.deleteTranningSchedule(id);
		return ResponseEntity.ok("Training Deleted");

	}

}
