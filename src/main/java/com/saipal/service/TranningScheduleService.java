package com.saipal.service;

import java.util.List;

import com.saipal.entity.Tranning;
import com.saipal.entity.TranningSchedule;
import com.saipal.request.TrainingScheduleDto;


public interface TranningScheduleService {
	public TranningSchedule saveTranningSchedule(TranningSchedule tranningSchedule);
	public TranningSchedule updateTranningSchedule(TranningSchedule tranningSchedule);
	public List<TranningSchedule> getallTranningSchedules();
	public List<TranningSchedule> getTrainingScheduleByTraining(Tranning tranning);
	public TranningSchedule getTranninSchedule(Long id);
	public void deleteTranningSchedule(Long id);
	public TranningSchedule convertDto(TrainingScheduleDto tsDto);


}
