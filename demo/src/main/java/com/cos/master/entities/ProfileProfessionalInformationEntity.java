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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "professional_info")
public class ProfileProfessionalInformationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "source_of_income")
	private String sourceOfIncome;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "bussiness_name")
	private String bussinessName;
	
	@Column(name = "annual_income")
	private Integer annualIncome;
	
	@Column(name = "business_annual_revenue")
	private Integer businessAnnualRevenue;
	
	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name = "modified_date")
	private LocalDate modifiedDate;
	
}

