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
//@Table(name = "medical_info")
public class MedicalInfoResponse implements Serializable{
	
	
	private Integer id;
	
//	@Column(name = "past_surgeries")
	private String pastSurgeries;
	private String bloodGroup;
	private byte[] uploadAsthmaReport;
//	@Column(name = "upload_bp_report")
	private byte[] uploadBpReport;
	
//	@Column(name = "upload_diabetes_report")
	private byte[] uploadDiabetesReport;
	

//	@Column(name = "upload_heart_stroke_report")
	private byte[] uploadHeartStrokeReport;

//	@Column(name = "upload_other_report")
	private byte[] uploadOtherReport;
	
//	@Column(name="current_treatments")
	private String currentTreatments;
	
//	@Column(name="covid_status")
	private String covidStatus;
	
}