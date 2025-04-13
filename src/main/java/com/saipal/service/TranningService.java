package com.saipal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.saipal.entity.Tranning;
import com.saipal.request.TrainingDto;

public interface TranningService {
	public Tranning saveTranning(Tranning tranning);
	public Tranning updateTranning(Tranning tranning);
	public List<Tranning> getallTrannings();
	public Tranning getTranningById(Long id);
	public void deleteTranning(Long id);
	public Tranning conertDto(TrainingDto trainingDto);
	public Page<Tranning> getAllTranningPage(int page, int size);


}
