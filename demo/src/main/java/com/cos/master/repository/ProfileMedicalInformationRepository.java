package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.cos.master.entities.ProfileMedicalInformationEntity;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public interface ProfileMedicalInformationRepository extends JpaRepository<ProfileMedicalInformationEntity, Integer>{

	@Query(value = "select MAX(user_id) from medical_info", nativeQuery = true)
	public long generateUserId();


	@Query(value = "select * from medical_info where user_id = ?1 ;", nativeQuery = true)
	public ProfileMedicalInformationEntity fetchByUserId(String userId);
	
	
}

