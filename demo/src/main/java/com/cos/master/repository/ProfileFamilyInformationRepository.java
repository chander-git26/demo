package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import com.cos.master.entities.ProfileFamilyInformationEntity;

import java.io.File;
import java.time.LocalDate;
import java.util.List;


public interface ProfileFamilyInformationRepository extends JpaRepository<ProfileFamilyInformationEntity, Integer>{

	@Query(value = "select MAX(user_id) from family_info", nativeQuery = true)
	public long generateUserId();
	
	@Query(value = "select * from family_info where user_id = ?1 ;", nativeQuery = true)
	public ProfileFamilyInformationEntity fetchByUserId(String userId);
}
