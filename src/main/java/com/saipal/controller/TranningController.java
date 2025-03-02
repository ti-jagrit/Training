package com.saipal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saipal.entity.Tranning;
import com.saipal.service.TranningService;
import com.saipal.utils.UniqueIdGenerator;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/training")

public class TranningController {
	
	@Autowired
	public UniqueIdGenerator uniqueIdGenerator;

	@Autowired
	private TranningService tranningService;

	@PostMapping()
	public ResponseEntity<Tranning> AddTraining(@RequestBody Tranning training)throws Exception {
		training.setId(UniqueIdGenerator.generateUniqueId());
		return ResponseEntity.ok(tranningService.saveTranning(training));
	}

	@GetMapping()
	public ResponseEntity<List<Tranning>> AllTraining() throws Exception{
		return ResponseEntity.ok(tranningService.getallTrannings());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tranning> getTrainingById(@PathVariable long id) throws Exception{
		return ResponseEntity.ok(tranningService.getTranningById(id));

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTraining(@PathVariable Long id, @RequestBody Tranning tranning) throws Exception{
		return ResponseEntity.ok(tranningService.updateTranning(tranning,id));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletetraining(@PathVariable long id) throws Exception{
		tranningService.deleteTranning(id);
		return ResponseEntity.ok("Training Deleted");

	}
}
