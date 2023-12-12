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

	@Column(name = "blood_pressure")
	private Integer bloodPressure;

	private String diabetes;
	
	@Column(name = "upload_medical_history")
	private String uploadMedicalHistory;

}
