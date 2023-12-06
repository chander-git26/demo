package com.cos.master.entities;

import java.io.File;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class FamilyInfoResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String father_name; 
	private int age;
	private String father_occupation;
	private File upload_medical_history;
	private String mother_name;
	private String mother_age;
	private String mother_occupation;
	private File  m_Upload_medical_history;
	private String s_name;
	private String sage;
	private String s_occupation;
	private File s_Upload_medical_history;
	private String nomine1_name;
	private String nomine1_age;
	private String	nomine1_occupation;
	private File	nomine1_Upload_medical_history;
	private String	nomine2_name;
	private String nomine2_age;
	private String	nomine2_occupation;
	private File	nomine2_Upload_medical_history;
	private String	maritalstatus;
	private String selectnumberofchildren;

	


	  

}
