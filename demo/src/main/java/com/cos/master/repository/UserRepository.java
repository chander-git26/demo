package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.master.entities.FamilyInfoResponse;
import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.ProffesionalInfoResponse;
import com.cos.master.entities.UserEntity;

import java.time.LocalDate;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	@Query(value = "select MAX(user_id) from user_info", nativeQuery = true)
	public long generateUserId();
	
	@Query(value = "INSERT INTO `demo`.`user_info` (`firstname`, `lastname`, `user_id`, `gender`, `dateofbirth`, `mobile`, `password`, `email`, `address`, `state`, `country`, `zipcode`, `created_date`, `modified_date`) VALUES (?1, '?2', '?3', '?4', '?5', '?6', '?7', '?8', '?9', '?10', '?11', ?12, ?13, ?14);", nativeQuery = true)
	public Integer createUser(String firstname, String lastname, String userId, String gender, String dateofbirth, String mobile, String password, String email, String address, String state, String country, String zipcode, LocalDate createdDate, LocalDate modifiedDate);
	
	@Query(value = "select * from user_info where user_id = ?1 ;", nativeQuery = true)
	public UserEntity fetchByUserId(String userId);

	
	@Query(value = "Select aadharnumber,pannumber,maritalstatus,bloodgroup,height,weight,smoking,alochol from personal_info where user_id='?1'", nativeQuery = true)
	public List<PersonalInfoResponse> getPersonalInfo(String user_id);
	
	@Query(value = "Select sourceofincome,companysname,annualincome,bussinessname,annualrevenue from professional_info where user_id='?1'", nativeQuery = true)
	public List<ProffesionalInfoResponse> getProffessionalInfo(String user_id);
	
	@Query(value = "Select father_name,fater_age,father_occupation,f_upload_medical_history,mother_occupation,m_upload_medical_history,spouse_name,spouse_age,spouse_occupation,s_upload_medical_history,nomine1_name,nomine1_age,nomine1_occupation,nomine1_upload_medical_history,nomine2_name,nomine2_age,nomine2_occupation,nomine2_upload_medical_history,marital_status,selectnumberofchildren  from family_info where user_id='?1'", nativeQuery = true)
	public List<FamilyInfoResponse> getFamilyInfo(String user_id);
	
	@Query(value = "Select Past_surgeries,Blood_pressure,Diabetes,Upload_medical_history from medical_info where user_id='?1'", nativeQuery = true)
	public List<FamilyInfoResponse> getMedicalInfo(String user_id);
	
	
}
