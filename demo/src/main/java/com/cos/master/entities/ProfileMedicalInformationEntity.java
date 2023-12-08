package com.cos.master.entities;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

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
public class ProfileMedicalInformationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "past_surgeries")
	private String pastSurgeries;

	@Column(name = "blood_pressure")
	private String bloodPressure;

	private String diabetes;

}
