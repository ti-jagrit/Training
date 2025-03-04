package com.saipal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saipal.entity.Tranning;
import com.saipal.repository.TranningRepository;
import com.saipal.service.TranningService;

@Service
public class TranningServiceImpl implements TranningService {
	@Autowired
	private TranningRepository repository;

	@Override
	public Tranning saveTranning(Tranning tranning) {
		// TODO Auto-generated method stub
		return repository.save(tranning);
	}

	@Override
	public Tranning updateTranning(Tranning tranning) {
	
		return repository.save(tranning);

	}

	@Override
	public List<Tranning> getallTrannings() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Tranning getTranningById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public void deleteTranning(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);

	}

}
