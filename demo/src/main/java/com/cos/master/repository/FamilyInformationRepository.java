package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.master.entities.FamilyInformationEntity;
import com.cos.master.entities.MedicalInformationEntity;

import java.io.File;
import java.time.LocalDate;
import java.util.List;


public interface FamilyInformationRepository extends JpaRepository<FamilyInformationEntity, Integer>{

	@Query(value = "select MAX(user_id) from family_info", nativeQuery = true)
	public String generateUserId();
	
	@Query(value = "select * from family_info where user_id = ?1 ;", nativeQuery = true)
	public FamilyInformationEntity fetchByUserId(String userId);
	
	@Modifying
	@Transactional
	@Query(value = "update family_info set father_name=?1, father_age=?2, father_occupation=?3, father_upload_medical_history=?4, mother_name=?5, mother_age=?6 mother_occupation=?7, mother_upload_medical_history=?8, spouse_name=?9, spouse_age=?10, spouse_occupation=?11, spouse_upload_medical_history=?12, other_nominee_name=?13, other_nominee_age=?14, other_nominee_occupation=?15, upload_other_nominee_medical_history=?16  where id = ?17", nativeQuery = true)
	public MedicalInformationEntity updateMedicalInfo(String id);
}
