package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cos.master.entities.MedicalInformationEntity;
import com.cos.master.entities.ProfessionalInformationEntity;


public interface MedicalInformationRepository extends JpaRepository<MedicalInformationEntity, Integer>{

	@Query(value = "select MAX(user_id) from medical_info", nativeQuery = true)
	public long generateUserId();


	@Query(value = "select * from medical_info where user_id = ?1 ;", nativeQuery = true)
	public MedicalInformationEntity fetchByUserId(String userId);
	
	@Modifying
	@Transactional
	@Query(value = "update medical_info set past_surgeries=?1, blood_group=?2, upload_asthma_report=?3, upload_bp_report=?4, upload_diabetes_report=?5, upload_heart_stroke_report=?6 upload_other_report=?7, current_treatments=?8, covid_status=?9 where id = ?10", nativeQuery = true)
	public MedicalInformationEntity updateMedicalInfo(String id);
	
	
}

