package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.cos.master.entities.FamilyInformationEntity;

import java.io.File;
import java.time.LocalDate;
import java.util.List;


public interface FamilyInformationRepository extends JpaRepository<FamilyInformationEntity, Integer>{

	@Query(value = "select MAX(user_id) from family_info", nativeQuery = true)
	public String generateUserId();
	
	@Query(value = "select * from family_info where user_id = ?1 ;", nativeQuery = true)
	public FamilyInformationEntity fetchByUserId(String userId);
}
