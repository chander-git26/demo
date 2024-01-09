package com.cos.master.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cos.master.entities.PersonalInformationEntity;

public interface PersonalInformationRepository extends JpaRepository<PersonalInformationEntity, String>{
	
	@Query(value = "select MAX(user_id) from personal_info", nativeQuery = true)
	public long generateUserId();
	

	@Query(value = "select * from personal_info where user_id = ?1 ;", nativeQuery = true)
	public PersonalInformationEntity fetchByUserId(String userId);
    
	@Modifying
    @Transactional
    @Query(value = "update personal_info set gender = ?1, date_of_birth = ?2, marital_status = ?3, state = ?4, address = ?5, height = ?6, weight = ?7,smoking = ?8,alcohol = ?9, pincode = ?10 where id = ?11", nativeQuery = true)
    public int saveById(String gender,Date dateOfBirth, String martialStatus, String state, String address, String height, String weight, String smoking, String alochol, Integer pincode,  Integer userId);

}
