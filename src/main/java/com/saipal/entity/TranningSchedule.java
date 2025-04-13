package com.saipal.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbltraining_schedule")
@Data
@NoArgsConstructor
public class TranningSchedule {
	@Id
	@Column(name = "scheduleid")
	@JsonProperty("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "trainingid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Tranning tranning;
	
	@Column(name = "startdate")
	private LocalDate startDate;
	
	@Column(name = "starttime")
	private LocalTime startTime;
	

	@Column(name = "enddate")
	private LocalDate endDate;
	
	@Column(name = "endtime")
	private LocalTime endTime;
	

}
