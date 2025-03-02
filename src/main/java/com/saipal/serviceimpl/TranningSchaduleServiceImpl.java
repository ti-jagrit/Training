package com.saipal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saipal.entity.TranningSchedule;
import com.saipal.repository.TranningScheduleRepository;
import com.saipal.service.TranningScheduleService;

@Service
public class TranningSchaduleServiceImpl implements TranningScheduleService{

	@Autowired
	private TranningScheduleRepository repository;
	
	@Override
	public TranningSchedule saveTranningSchedule(TranningSchedule tranningSchedule) {
		// TODO Auto-generated method stub
		return repository.save(tranningSchedule);
	}

	@Override
	public TranningSchedule updateTranningSchedule(TranningSchedule tranningSchedule) {
		// TODO Auto-generated method stub
		return repository.save(tranningSchedule);
	}

	@Override
	public List<TranningSchedule> getallTranningSchedules() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public TranningSchedule getTranninSchedule(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void deleteTranningSchedule(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		
	}

	

}
