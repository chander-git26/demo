package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.master.entities.ProfileProfessionalInformationEntity;

import java.time.LocalDate;
import java.util.List;

public interface ProfileProfessionalInformationRepository extends JpaRepository<ProfileProfessionalInformationEntity, String>{

	@Query(value = "select MAX(user_id) from professional_info", nativeQuery = true)
	public long generateUserId();
	

	@Query(value = "select * from professional_info where user_id = ?1 ;", nativeQuery = true)
	public ProfileProfessionalInformationEntity fetchByUserId(String userId);
	
}
