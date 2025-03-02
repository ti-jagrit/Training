package com.saipal.entity;

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
	private long id;
	private String code;
	@Column(name = "training_name_np")
	private String tranningNameNp ;
	@Column(name = "training_name_en")
	private String tranningNameEn;
	private int approved;



}
