package com.cos.master.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.UserEntity;

import com.cos.master.repository.UserRepository;

@Service
public class UserService {
	
	
	
	private static final String Aadhaar_number = null;
	@Autowired
	UserRepository userRepo;
	
	
	public int createUser(String firstname, String lastname, String userId, String gender, String dateofbirth, String mobile, String password, String email, String address, String state, String country, String zipcode, LocalDate createdDate, LocalDate modifiedDate){
		return userRepo.createUser(firstname, lastname, userId, gender, dateofbirth, mobile, password, email, address, state, country, zipcode, createdDate, modifiedDate);
	}
	public UserEntity getUserInfo(String userId) {
		return userRepo.fetchByUserId(userId);
	}
	
	
	public List<PersonalInfoResponse> getPersonalInfo(String user_id) {
		
	 
		PersonalInfoResponse personal = new PersonalInfoResponse();
		List<PersonalInfoResponse> pe = userRepo.getPersonalInfo(user_id);
		
	 for (int i = 0; i < pe.size(); i++) {
		
		  // personal.setAadhaar_number(Aadhaar_number);
		   
		//personal.setPan_number(pan_number);
		//personal.setBlood_Group(Blood_Group);
		   
	}
	
		
	return userRepo.getPersonalInfo(user_id);
	 
	}
	
	      
	    /*  
	       
		public List<Object> getProffessionalInfo(String user_id) {
			return userRepo.getProffessionalInfo(user_id);
	}
	
		public List<Object> getFamilyInfo(String user_id) {
			return userRepo.getFamilyInfo(user_id);
	}
		public List<Object> getMedicalInfo(String user_id) {
			return userRepo.getMedicalInfo(user_id);
	}
		
	*/
}
