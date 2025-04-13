package com.saipal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saipal.entity.Tranning;
import com.saipal.entity.TranningSchedule;
import com.saipal.repository.TranningScheduleRepository;
import com.saipal.request.TrainingScheduleDto;
import com.saipal.service.TranningScheduleService;
import com.saipal.service.TranningService;
import com.saipal.utils.UniqueIdGenerator;

@Service
public class TranningSchaduleServiceImpl implements TranningScheduleService{

	@Autowired
	private TranningScheduleRepository repository;
	
	@Autowired
	private TranningService tranningService;
	
	@Autowired
	UniqueIdGenerator uniqueIdGenerator;
	
	@Override
	public TranningSchedule saveTranningSchedule(TranningSchedule tranningSchedule) {
		return repository.save(tranningSchedule);
	}

	@Override
	public TranningSchedule updateTranningSchedule(TranningSchedule tranningSchedule) {
		return repository.save(tranningSchedule);
	}

	@Override
	public List<TranningSchedule> getallTranningSchedules() {
		return repository.findAll();
	}

	@Override
	public TranningSchedule getTranninSchedule(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteTranningSchedule(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<TranningSchedule> getTrainingScheduleByTraining(Tranning tranning) {
		return repository.findByTranning(tranning);
	}

	@SuppressWarnings("static-access")
	@Override
	public TranningSchedule convertDto(TrainingScheduleDto  tsDto) {
		TranningSchedule ts=new TranningSchedule();
		if(tsDto.getId()!=null) {
			ts.setId(Long.parseLong(tsDto.getId()));
		}else {
			ts.setId(uniqueIdGenerator.generateUniqueId());
		}
		
		ts.setStartDate(tsDto.getStartDate());
		ts.setStartTime(tsDto.getStartTime());
		ts.setEndDate(tsDto.getEndDate());
		ts.setEndTime(tsDto.getEndTime());
		ts.setTranning(tranningService.getTranningById(Long.parseLong(tsDto.getTraining())));
		return ts;
	}

	

}
