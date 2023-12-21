package com.cos.master.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PersonalInfoResponse implements Serializable{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	
	private String address;
	private String gender;
	
	@Column(name = "date_of_birth")
	private String dateOfBirth;
	
	private String state;
	private String country;
	
	@Column(name = "marital_status")
	private String maritalStatus;
	
    private Integer height;
	private Integer weight;
	private String smoking;
	private String alcohol;

}
