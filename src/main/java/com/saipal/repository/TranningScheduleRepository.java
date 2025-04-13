package com.saipal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saipal.entity.Tranning;
import com.saipal.entity.TranningSchedule;
@Repository
public interface TranningScheduleRepository extends JpaRepository<TranningSchedule, Long> {
	List<TranningSchedule> findByTranning(Tranning tranning);

}
