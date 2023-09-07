package com.example.vehicle.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_master")
@AllArgsConstructor
@NoArgsConstructor
public class UserMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "First_Name")
	private String firstname;
	
	@Column(name = "Last_Name")
	private String lastname;
	
	@Column(name = "User_Name")
	private String username;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Password")
	private String password;
	
}
