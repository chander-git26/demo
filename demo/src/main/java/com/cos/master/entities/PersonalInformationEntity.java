package com.cos.master.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personal_info")

public class PersonalInformationEntity implements Serializable {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String  id;
	private String address;
	private String gender;
	

	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	private String state;
	private Integer pincode;
	@Column(name = "marital_status")
	private String maritalStatus;
	private String height;
	private Integer weight;
	private String smoking;
	private String alcohol;
    private String city;

}
