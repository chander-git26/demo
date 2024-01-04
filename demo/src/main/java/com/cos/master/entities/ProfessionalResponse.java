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


public class ProfessionalResponse implements Serializable {
	
	private Integer id;
	
	private String sourceOfIncome;
	
	private String companyName;
	
	private String businessName;
	
	private String annualIncome;
	
	private String businessAnnualRevenue;
	
	private String gstNumber;
	
	private String investAmount;
	
	
}

