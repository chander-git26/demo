package com.cos.master.entities;

import java.io.Serializable;

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
@Table(name = "professional_info")
public class ProfessionalInformationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	
	@Column(name = "source_of_income")
	private String sourceOfIncome;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(name = "annual_income")
	private Integer annualIncome;
	
	@Column(name = "business_annual_revenue")
	private Integer businessAnnualRevenue;
	
}

