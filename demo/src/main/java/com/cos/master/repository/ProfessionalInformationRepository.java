package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cos.master.entities.ProfessionalInformationEntity;


public interface ProfessionalInformationRepository extends JpaRepository<ProfessionalInformationEntity, String>{

	@Query(value = "select MAX(user_id) from professional_info", nativeQuery = true)
	public long generateUserId();
	

	@Query(value = "select * from professional_info where user_id = ?1 ;", nativeQuery = true)
	public ProfessionalInformationEntity fetchByUserId(String userId);
	
	@Modifying
	@Transactional
	@Query(value = "update professional_info set source_of_income=?1, annual_income=?4, business_annual_revenue=?5, gst_number=?6, business_type=?7, invest_amount=?8 where id=?1;", nativeQuery = true)
	public ProfessionalInformationEntity updateProfessionalInfo(String id);
		
}
