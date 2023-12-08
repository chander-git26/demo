package com.cos.master.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class ProfessionalResponse {
	private Integer id;
	private String source_of_income;
	private String company_name;
	private String business_name;
	private String annual_business_revenue;
	private String annual_salary;
 }
