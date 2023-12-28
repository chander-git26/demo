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
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public class FamilyResponse implements Serializable {


	private Integer id;

	@Column(name = "father_name")
	private String fatherName;
	
	@Column(name = "father_age")
	private Integer fatherAge;
	
	@Column(name = "father_occupation")
	private String fatherOccupation;

	@Column(name = "father_upload_medical_history")
	private String  fatherUploadMedicalHistory;
	

	@Column(name = "mother_name")
	private String motherName;
	
	@Column(name = "mother_age")
	private Integer motherAge;
	
	@Column(name = "mother_occupation")
	private String motherOccupation;
	
	@Column(name = "mother_upload_medical_history")
	private String  motherUploadMedicalHistory;
	

	@Column(name = "spouse_name")
	private String spouseName;
	
	@Column(name = "spouse_age")
	private Integer spouseAge;
	
	@Column(name = "spouse_occupation")
	private String spouseOccupation;
	
	@Column(name = "spouse_upload_medical_history")
	private String  spouseUploadMedicalHistory;
	

//	@Column(name = "nominee1_name")
//	private String nominee1Name;
//
//	@Column(name = "nominee2_name")
//	private String nominee2Name;
	

	@Column(name = "other_nominee_name")
	private String otherNomineeName;
	
	@Column(name = "other_nominee_age")
	private Integer otherNomineeAge;
	
	@Column(name = "other_nominee_occupation")
	private String otherNomineeOccupation;
	
	@Column(name = "upload_other_nominee_medical_history")
	private String uploadOtherNomineeMedicalHistory;
	
//	@Column(name = "other_nominee_relation")
//	private String otherNomineeRelation;
//	@Column(name = "marital_status")
//	private String maritalStatus;
//	@Column(name = "select_number_of_children")
//	private Integer selectNumberOfChildren;

}


