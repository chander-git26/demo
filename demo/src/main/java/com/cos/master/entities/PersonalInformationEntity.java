package com.cos.master.entities;

import java.io.Serializable;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	@Column(name = "aadhar_number")
	private String aadharNumber;
	@Column(name = "pan_number")
	private String panNumber;
	@Column(name = "marital_status")
	private String maritalStatus;
	@Column(name = "blood_group")
	private String bloodGroup;
	private Integer height;
	private Integer weight;
	private String smoking;
	private String alochol;

}
