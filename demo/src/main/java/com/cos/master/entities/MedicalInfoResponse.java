package com.cos.master.entities;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class MedicalInfoResponse implements Serializable{
	

	private Integer id;
	
	@Column(name = "past_surgeries")
	private String pastSurgeries;
	
	@Column(name = "blood_pressure")
	private Integer bloodPressure;
	
	@Column(name = "diabetes")
	private String diabetes;
	
//	private MultipartFile Upload_medical_history;
	
	@Column(name = "Upload_medical_history")
	private String UploadMedicalHistory;

}