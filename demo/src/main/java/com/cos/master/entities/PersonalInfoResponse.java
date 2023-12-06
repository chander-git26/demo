package com.cos.master.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PersonalInfoResponse {

	private String Aadhaar_number;
	private String pan_number;
	private String Marital_Status;
	private String Blood_Group;
	private int Height;
	private int Weight;
	private String Smoking;
	private String Alcohol;
    
	
}
