package com.saipal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.saipal.entity.Tranning;
import com.saipal.repository.TranningRepository;
import com.saipal.request.TrainingDto;
import com.saipal.service.TranningService;

@Service
public class TranningServiceImpl implements TranningService {
	@Autowired
	private TranningRepository repository;

	@Override
	public Tranning saveTranning(Tranning tranning) {
		return repository.save(tranning);
	}

	@Override
	public Tranning updateTranning(Tranning tranning) {
		return repository.save(tranning);
	}

	@Override
	public List<Tranning> getallTrannings() {
		return repository.findAll();
	}

	@Override
	public Tranning getTranningById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteTranning(Long id) {
		repository.deleteById(id);

	}

	@Override
	public Tranning conertDto(TrainingDto trainingDto) {
		Tranning tranning = new Tranning();
		if (trainingDto.getId() != null) {
			tranning.setId(Long.parseLong(trainingDto.getId()));
		}
		tranning.setTrainingNameEn(trainingDto.getTrainingNameEn());
		tranning.setTrainingNameNp(tranning.getTrainingNameNp());
		tranning.setApproved(trainingDto.getApproved());
		tranning.setCode(trainingDto.getCode());
		return tranning;
	}

	@Override
	public Page<Tranning> getAllTranningPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repository.findAll(pageable);
	}

}
