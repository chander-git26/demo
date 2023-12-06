package com.cos.master.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProffesionalInfoResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String Source_of_Income;
	private String Company_Name;
	private String Business_Name;
	private String Annual_Revenue;
	private int Annual_Income;
	
}
