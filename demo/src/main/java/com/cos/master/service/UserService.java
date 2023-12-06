package com.cos.master.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.master.entities.UserEntity;
import com.cos.master.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	
	public int createUser(String firstname, String lastname, String userId, String gender, String dateofbirth, String mobile, String password, String email, String address, String state, String country, String zipcode, LocalDate createdDate, LocalDate modifiedDate){
		return userRepo.createUser(firstname, lastname, userId, gender, dateofbirth, mobile, password, email, address, state, country, zipcode, createdDate, modifiedDate);
	}
	
	
	public UserEntity getUserInfo(String userId) {
		return userRepo.fetchByUserId(userId);
	}
	public UserEntity getusername(String email) {
		return userRepo.fetchByUserInfo(email);
}


	
}

