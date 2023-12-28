package com.cos.master.entities;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

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
@Entity
@Table(name = "family_info")
public class FamilyInformationEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "father_age")
	private Integer fatherAge;

	@Column(name = "father_occupation")
	private String fatherOccupation;

	@Column(name = "father_upload_medical_history")
	private byte[] fatherUploadMedicalHistory;

//	private MultipartFile  father_upload_medical_history;

	@Column(name = "mother_name")
	private String motherName;
	@Column(name = "mother_age")
	private Integer motherAge;
	@Column(name = "mother_occupation")
	private String motherOccupation;

	@Column(name = "mother_upload_medical_history")
	private byte[] motherUploadMedicalHistory;
	//private MultipartFile   mother_upload_medical_history;

	@Column(name = "spouse_name")
	private String spouseName;
	@Column(name = "spouse_age")
	private Integer spouseAge;
	@Column(name = "spouse_occupation")
	private String spouseOccupation;

	@Column(name = "spouse_upload_medical_history")
	private byte[] spouseUploadMedicalHistory;
//	private MultipartFile  spouse_upload_medical_history;

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
	private byte[] uploadOtherNomineeMedicalHistory;

//
//   @Column(name="upload_other_nominee_relation")
//   private String uploadOtherNomineeRelation;

}
