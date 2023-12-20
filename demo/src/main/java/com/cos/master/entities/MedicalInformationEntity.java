package com.cos.master.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "medical_info")
public class MedicalInformationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "past_surgeries")
	private String pastSurgeries;

	@Column(name = "upload_bp_report")
	private String uploadBloodPressure;
    @Column(name="upload_diabetes_report")
	private String uploadDiabetes;
	
	@Column(name = "upload_heart_stroke_report")
	private String uploadHeartStroke;

	@Column(name="upload_other_report")
	private String upload_OtherReport;
	
	@Column(name="current_treatments")
	private String currentTreatments;
	
	@Column(name="covid_status")
	private String covidStatus;
}
