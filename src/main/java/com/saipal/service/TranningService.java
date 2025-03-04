package com.saipal.service;

import java.util.List;

import com.saipal.entity.Tranning;

public interface TranningService {
	public Tranning saveTranning(Tranning tranning);
	public Tranning updateTranning(Tranning tranning);
	public List<Tranning> getallTrannings();
	public Tranning getTranningById(Long id);
	public void deleteTranning(Long id);

}
