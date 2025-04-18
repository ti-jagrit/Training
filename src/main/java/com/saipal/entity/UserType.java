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
@Table(name = "tblusertype")
@Data
@NoArgsConstructor
public class UserType {
	@Id
	@Column(name = "usertypeid")
	@JsonProperty("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private long id;

	@Column(name = "usertype")
	private String userType;

	private int approved;

}
