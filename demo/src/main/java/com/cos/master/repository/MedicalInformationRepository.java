package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.master.entities.MedicalInformationEntity;


public interface MedicalInformationRepository extends JpaRepository<MedicalInformationEntity, Integer>{

	@Query(value = "select MAX(user_id) from medical_info", nativeQuery = true)
	public long generateUserId();


	@Query(value = "select * from medical_info where user_id = ?1 ;", nativeQuery = true)
	public MedicalInformationEntity fetchByUserId(String userId);
	
	
}

