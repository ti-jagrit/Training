package com.saipal.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class TrainingScheduleDto {
	private String id;
	private String training;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalDate endDate;
	private LocalTime endTime;

}
