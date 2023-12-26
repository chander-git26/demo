package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.master.entities.ProfilePersonalInformationEntity;

import java.time.LocalDate;
import java.util.List;

public interface ProfilePersonalInformationRepository extends JpaRepository<ProfilePersonalInformationEntity, Integer>{
	
	@Query(value = "select MAX(user_id) from personal_info", nativeQuery = true)
	public long generateUserId();
	

	@Query(value = "select * from personal_info where user_id = ?1 ;", nativeQuery = true)
	public ProfilePersonalInformationEntity fetchByUserId(String userId);

}
