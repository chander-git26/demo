package com.cos.master.entities;

import java.io.File;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class MedicalInfoResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private  String Past_surgeries;
	private  String Blood_pressure;
	private  String Diabetes;
	private File Upload_medical_history;
}
