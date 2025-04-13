package com.saipal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.Nullable;
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
	@JsonProperty("id")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "userid")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "personid")
	private Person person;

	@Column(name = "loginname")
	private String loginName;

	@JsonIgnore
	private String password;
	
	@Nullable
	private String image;

	@ManyToOne
	@JoinColumn(name = "usertype")
	private UserType userType;

	@Column(name = "userstatus")
	private int userStatus;

}
