package com.saipal.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbluserinfo")
@Data
@NoArgsConstructor
public class UserInfo {
	
	@Id
	@Column(name = "userid")
	private long id;
	@OneToOne
	@JsonBackReference  
	@JoinColumn(name = "personid")
	private Person person;
	
	@Column(name = "loginname")
	private String loginName;
	
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "usertype")
	private UserType userType;
	
	@Column(name = "userstatus")
	private int userStatus;
	
	
	
	

}
