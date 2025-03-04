package com.saipal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tblperson")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Id
	@Column(name = "personid")
	private long id;

	@ManyToOne
	@JoinColumn(name = "persontypeid")
	private PersonType personType;

	@Column(name = "fullname")
	private String fullName;

	
	private int gender;

	private String address;
	private String email;

	@Column(name = "mobileno")
	private String mobileNo;
	private String institution;

	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
	@JsonBackReference
	private UserInfo userInfo;
}
