package com.cos.master.entities;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalInfoResponse {
	private Integer id;
	private String past_surgeries;
	private String blood_pressure;
	private String diabetes;
	private MultipartFile Upload_medical_history;
}
