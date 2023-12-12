package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.master.entities.PersonalInformationEntity;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformationEntity, Integer>{
	
	@Query(value = "select MAX(user_id) from personal_info", nativeQuery = true)
	public long generateUserId();
	

	@Query(value = "select * from personal_info where user_id = ?1 ;", nativeQuery = true)
	public PersonalInformationEntity fetchByUserId(String userId);

}
