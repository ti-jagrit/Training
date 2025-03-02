package com.saipal.service;

import java.util.List;

import com.saipal.entity.TranningSchedule;


public interface TranningScheduleService {
	public TranningSchedule saveTranningSchedule(TranningSchedule tranningSchedule);
	public TranningSchedule updateTranningSchedule(TranningSchedule tranningSchedule);
	public List<TranningSchedule> getallTranningSchedules();
	public TranningSchedule getTranninSchedule(Long id);
	public void deleteTranningSchedule(Long id);


}
