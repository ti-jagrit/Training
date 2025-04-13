package com.saipal.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tblpersontype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonType {
	@Id
	@Column(name = "persontypeid")
	@JsonProperty("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private long id;
	
	private String code;

	@Column(name = "persontype")
	private String PersonType;
	


}
