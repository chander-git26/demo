package com.cos.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
//
//	@Query(value = "select password from user_info where email ='raju@gmail.com';", nativeQuery = true)
//	public String fetchByUserInfo(String email);
	
	@Query(value = "select password from user_info where email = :email", nativeQuery = true)
	public String fetchByUserInfo(@Param("email") String email);
//	@Query(value = "SELECT AES_DECRYPT(password, '[B@2ac1fdc4') FROM user_info WHERE email = :email", nativeQuery = true)
//	public String fetchByUserInfo(@Param("email") String email);
//	
	
}
