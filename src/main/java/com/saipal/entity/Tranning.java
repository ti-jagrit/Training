package com.saipal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="tbltraining")
@Data
@NoArgsConstructor
public class Tranning {
	
	@Id
	@Column(name = "trainingid")
	@JsonProperty("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private long id;
	private String code;
	@Column(name = "training_name_np")
	private String trainingNameNp ;
	@Column(name = "training_name_en")
	private String trainingNameEn;
	private int approved;



}
