package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.ProfessionalResponse;
import com.cos.master.entities.UserEntity;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	@Query(value = "select MAX(user_id) from user_info", nativeQuery = true)
	public String generateUserId();

	@Query(value = "INSERT INTO `demo`.`user_info` (`firstname`, `lastname`, `user_id`, `mobile`, `password`, `email`,  `created_date`, `modified_date`) VALUES (?1, '?2', '?3', '?4', '?5', '?6', '?7', '?8');", nativeQuery = true)
	public Integer createUser(String firstname, String lastname, String userId,  String password,  String email, String mobile,LocalDate createdDate, LocalDate modifiedDate);
	
	@Query(value = "select * from user_info where user_id = ?1", nativeQuery = true)
	public UserEntity fetchByUserId(String userId);

	@Query(value = "select password from user_info where email = :email", nativeQuery = true)
	public String fetchByUserInfo(@Param("email") String email);
	
	
	
	@Query(value = "Select id,address,gender,date_of_birth,state,country,marital_status,blood_group,height,weight,smoking,alochol from personal_info where id=:userId", nativeQuery = true)
	public List<Object[]> getPersonalInfo(@Param("userId") String userId);
	
	@Query(value = "select id,source_of_income,company_name,business_name, annual_income, business_annual_revenue from professional_info where id =:userId", nativeQuery = true)
	public List<Object[]> getProfessionallInfo(@Param("userId") String userId);
	
	@Query(value = "select id,father_name,father_age,father_occupation,fater_upload_medical_history,mother_name,mother_age,mother_occupation,mother_upload_medical_history,spouse_name,spouse_age,spouse_occupation,spouse_upload_medical_history,nominee1_name,nominee2_name,other_nominee_name,other_nominee_age,other_nominee_relation,marital_status,select_number_of_children from family_info where id =:userId", nativeQuery = true)
	public List<Object[]> getFamilylInfo(@Param("userId") String userId);
	
	@Query(value = "select id,past_surgeries,blood_pressure,diabetes,upload_medical_history from medical_info where id = :userId", nativeQuery = true)
	public List<Object[]> getMedicallInfo(@Param("userId") String userId);
	
	
	
//	@Query(value = "select otp from user_info where user_id =:mobile ", nativeQuery = true)
//	public String getUserOtp(@Param("mobile") String mobile);

	@Query(value = "select user_id from user_info where email=?1", nativeQuery = true)
	public String getUserEmail( String email);
	
	@Query(value = "select mobile from user_info where mobile=?1", nativeQuery = true)
	public String getMobileNumber(String  mobileNumber);

	@Modifying
	@Transactional

	@Query(value = "update user_info set password=:updatedPassword where mobile=:moblieNumber ", nativeQuery=true)
	public int updatePassword(@Param("updatedPassword")String updatedPassword,@Param("moblieNumber")String moblieNumber);
	
	@Query(value = "select mobile from user_info where mobile=?1", nativeQuery = true)
	public String getUserMobile(@Param("mobile")String mobile);
	
	@Modifying
	@Transactional
	@Query(value ="update user_info set otp =:otp where mobile =:mobile", nativeQuery = true)
	public int saveOtp(@Param("otp") String otp,@Param("mobile") String mobile);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set father_name =:father_name where id =:id", nativeQuery = true)
	public int updateFathername(@Param("father_name") String father_name,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set age =:age where id =:id", nativeQuery = true)
	public int updateAge(@Param("age") int age,@Param("id") int id);
	
}

