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

	@Column(name = "upload_bp_report")
	private String bloodPressure;
    @Column(name="upload_diabetes_report")
	private String diabetes;
	
	@Column(name = "upload_heart_stroke_report")
	private String heartStroke;

	@Column(name="upload_other_report")
	private String otherReport;
	
	@Column(name="current_treatments")
	private String currentTreatments;
	
	@Column(name="covid_status")
	private String covidStatus;
}