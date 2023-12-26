package com.cos.master.repository;

import org.hibernate.annotations.PartitionKey;
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

import java.awt.Image;
import java.sql.Blob;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	@Query(value = "select MAX(user_id) from user_info", nativeQuery = true)
	public String generateUserId();

	@Query(value = "INSERT INTO `demo`.`user_info` (`firstname`, `lastname`, `user_id`, `mobile`, `password`, `email`,  `created_date`, `modified_date`) VALUES (?1, '?2', '?3', '?4', '?5', '?6', '?7', '?8');", nativeQuery = true)
	public Integer createUser(String firstname, String lastname, String userId,  String password,  String email, String mobile,LocalDate createdDate, LocalDate modifiedDate);
	
	@Query(value = "select * from user_info where user_id = ?1", nativeQuery = true)
	public UserEntity fetchByUserId(String userId);

	@Query(value = "select password from user_info where email = :email", nativeQuery = true)
	public String fetchByUserInfo(@Param("email") String email);
	
	
	
	@Query(value = "Select id,address,gender,date_of_birth,state,pincode,marital_status,height,weight,smoking,alochol from personal_info where id=:userId", nativeQuery = true)
	public List<Object[]> getPersonalInfo(@Param("userId") String userId);
	
	@Query(value = "select id,source_of_income,company_name,business_name, annual_income, business_annual_revenue,gst_number,invest_amount from professional_info where id =:userId", nativeQuery = true)
	public List<Object[]> getProfessionallInfo(@Param("userId") String userId);

	
//	@Query(value = "select id,father_name,father_age,father_occupation,father_upload_medical_history,mother_name,mother_age,mother_occupation,mother_upload_medical_history,spouse_name,spouse_age,spouse_occupation,spouse_upload_medical_history,nominee1_name,nominee2_name,other_nominee_name,other_nominee_age,other_nominee_relation,marital_status,select_number_of_children from family_info where id =:userId", nativeQuery = true)
//	public List<Object[]> getFamilylInfo(@Param("userId") String userId);
	
	@Query(value = "select id,father_name,father_age,father_occupation,father_upload_medical_history,mother_name,mother_age,mother_occupation,mother_upload_medical_history,spouse_name,spouse_age,spouse_occupation,spouse_upload_medical_history,other_nominee_name,other_nominee_age,other_nominee_occupation,other_nominee_upload_medical_history from family_info where id =:userId", nativeQuery = true)
	public List<Object[]> getFamilylInfo(@Param("userId") String userId);
	
	@Query(value = "select id,past_surgeries,upload_bp_report,upload_diabetes_report,upload_heart_stroke_report,upload_other_report,current_treatments,covid_status from medical_info where id = :userId", nativeQuery = true)
	public List<Object[]> getMedicallInfo(@Param("userId") String userId);
	
	@Query(value = "select upload_other_report from medical_info where id = :userId", nativeQuery = true)
	public byte[] getMedicalInfo(@Param("userId") String userId);
	
	@Query(value = "select otp from user_info where mobile =:mobile", nativeQuery = true)
	public int getUserOtp(@Param("mobile") String mobile);

	@Query(value = "select user_id from user_info where email=?1", nativeQuery = true)
	public String getUserEmail( String email);
	
	@Query(value = "select mobile from user_info where mobile=?1", nativeQuery = true)
	public String getMobileNumber(String  mobileNumber);
    
	@Query(value = "select profile from user_info where userId =:userId",nativeQuery = true)
	public String findById(@Param("userId") String userId);
	
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
	
	//update
	@Modifying
	@Transactional
	@Query(value ="update personal_info set address =:address where id =:userId", nativeQuery = true)
	public int updateAddress(@Param("address") String address,@Param("userId") int userId);
	
	@Modifying
	@Transactional
	@Query(value ="update personal_info set gender =:gender where id =:userId", nativeQuery = true)
	public int updategender(@Param("gender") String gender,@Param("userId") int userId);
	
	
	
	@Modifying
	@Transactional
	@Query(value ="update personal_info set dateofbirth =:dateofbirth where id =:userId", nativeQuery = true)
	public int updatedateofbirth(@Param("dateofbirth") String dateofbirth,@Param("userId") int userId);
	
	@Modifying
	@Transactional
	@Query(value ="update personal_info set state =:state where id =:userId", nativeQuery = true)
	public int updatestate(@Param("state") String state,@Param("userId") int userId);
	

	@Modifying
	@Transactional
	@Query(value ="update personal_info set pincode =:pincode where id =:userId", nativeQuery = true)
	public int updatepincode(@Param("pincode") int pincode,@Param("userId") int userId);
	
	@Modifying
	@Transactional
	@Query(value ="update personal_info set maritalStatus =:maritalStatus where id =:userId", nativeQuery = true)
	public int updateMaritalStatus(@Param("maritalStatus") String maritalStatus,@Param("userId") int userId);
	
//	@Modifying
//	@Transactional
//	@Query(value ="update personal_info set bloodGroup =:bloodGroup where id =:userId", nativeQuery = true)
//	public int updateBloodGroup(@Param("bloodGroup") String bloodGroup,@Param("userId") int userId);

	@Modifying
	@Transactional
	@Query(value ="update personal_info set height =:height where id =:userId", nativeQuery = true)
	public int updateHeight(@Param("height") int height,@Param("userId") int userId);
	
	@Modifying
	@Transactional
	@Query(value ="update personal_info set weight=:weight where id =:userId", nativeQuery = true)
	public int updateWeight(@Param("weight") int weight,@Param("userId") int userId);
	
	
	@Modifying
	@Transactional
	@Query(value ="update personal_info set smoking=:smoking where id =:userId", nativeQuery = true)
	public int updateSmoking(@Param("smoking") String smoking,@Param("userId") int  userId);
	
	@Modifying
	@Transactional
	@Query(value ="update personal_info set alochol=:alochol where id =:userId", nativeQuery = true)
	public int updateAlochol(@Param("alochol") String alochol,@Param("userId") int  userId);

	@Modifying
	@Transactional
	@Query(value ="update family_info set father_name =:father_name where id =:id", nativeQuery = true)
	public int updateFatherName(@Param("father_name") String father_name,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set father_age =:father_age where id =:id", nativeQuery = true)
	public int updateAge(@Param("father_age") int father_age,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set father_occupation =:father_occupation where id =:id", nativeQuery = true)
	public int upadteFatherOccupation(@Param("father_occupation") String father_occupation,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set mother_age =:mother_age where id =:id", nativeQuery = true)
	public int updateMotherAge(@Param("mother_age") int mother_age,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set mother_Name =:mother_Name where id =:id", nativeQuery = true)
	public int upadteMotherName(@Param("mother_Name") String mother_Name,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set mother_occupation =:mother_occupation where id =:id", nativeQuery = true)
	public int upadteMotherOccupation(@Param("mother_occupation") String mother_occupation,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set spouse_name =:spouse_name where id =:id", nativeQuery = true)
	public int upadteSpouseName(@Param("spouse_name") String spouse_name,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set spouse_age =:spouse_age where id =:id", nativeQuery = true)
	public int updateSpouseAge(@Param("spouse_age") int spouse_age,@Param("id") int id);
	
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set spouse_occupation =:spouse_occupation where id =:id", nativeQuery = true)
	public int updateSpouseOccupation(@Param("spouse_occupation") String spouse_occupation,@Param("id") int id);
	
//	@Modifying
//	@Transactional
//	@Query(value ="update family_info set nominee1_name =:nominee1_name where id =:id", nativeQuery = true)
//	public int updateNominee1Name(@Param("nominee1_name") String nominee1_name,@Param("id") int id);
//	
//	@Modifying
//	@Transactional
//	@Query(value ="update family_info set nominee2_name =:nominee2_name where id =:id", nativeQuery = true)
//	public int updateNominee2Name(@Param("nominee2_name") String nominee2_name,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set other_nominee_name =:other_nominee_name where id =:id", nativeQuery = true)
	public int updateOtherNomineeName(@Param("other_nominee_name") String other_nominee_name,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set other_nominee_age =:other_nominee_age where id =:id", nativeQuery = true)
	public int updateOtherNomineeAge(@Param("other_nominee_age") int other_nominee_age,@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value ="update family_info set other_nominee_occupation =:other_nominee_occupation where id =:id", nativeQuery = true)
	public int updateOtherNomineeOccupation(@Param("other_nominee_occupation") String other_nominee_occupation,@Param("id") int id);
	
	
//	@Modifying
//	@Transactional
//	@Query(value ="update family_info set other_nominee_relation =:other_nominee_relation where id =:id", nativeQuery = true)
//	public int updateOtherNomineeRelation(@Param("other_nominee_relation") String other_nominee_relation,@Param("id") int id);
//
//	@Modifying
//	@Transactional
//	@Query(value ="update family_info set select_number_of_children =:select_number_of_children where id =:id", nativeQuery = true)
//	public int updateSelectNumberOfChildren(@Param("select_number_of_children") int select_number_of_children,@Param("id") int id);

	@Modifying
	@Transactional
	@Query(value ="update medical_info set past_surgeries =:past_surgeries where id =:id", nativeQuery = true)
	public int updatePastSurgeries(@Param("past_surgeries") String past_surgeries,@Param("id") int id);
	

	@Modifying
	@Transactional
	@Query(value ="update medical_info set blood_pressure =:blood_pressure where id =:id", nativeQuery = true)
	public int updateBloodPressure(@Param("blood_pressure") String blood_pressure,@Param("id") int id);
	

	@Modifying
	@Transactional
	@Query(value ="update medical_info set diabetes =:diabetes where id =:id", nativeQuery = true)
	public int updateDiabetes(@Param("diabetes") String diabetes,@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query(value = "update user_info set profile =:profile where id=:userId", nativeQuery = true)
	public int uploadProfile(@Param("userId") int userId, @Param("profile") Blob profile);
	
	@Modifying
	@Transactional
	@Query(value ="update professional_info set source_of_income =:source_of_income where id =:id", nativeQuery = true)
	public int updateSourceofIncome(String source_of_income, int id);
	
	@Modifying
	@Transactional
	@Query(value ="update professional_info set company_name =:company_name where id =:id", nativeQuery = true)
	public int updateCompanyName(String company_name, int id);
	
	@Modifying
	@Transactional
	@Query(value ="update professional_info set business_name =:business_name where id =:id", nativeQuery = true)
    public int updateBusinessName(String business_name, int id);
	
	@Modifying
	@Transactional
	@Query(value ="update professional_info set business_annual_revenue =:business_annual_revenue where id =:id", nativeQuery = true)
    public int updateBusinessannualRevenue(int business_annual_revenue, int id);

	@Modifying
	@Transactional
	@Query(value ="update professional_info set annual_income =:annual_income where id =:id", nativeQuery = true)
    public int updateannualIncome(int annual_income, int id);
	
}

