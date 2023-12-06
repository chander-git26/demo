package com.cos.master.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.master.entities.ProfileFamilyInformationEntity;
import com.cos.master.entities.ProfileMedicalInformationEntity;
import com.cos.master.entities.ProfilePersonalInformationEntity;
import com.cos.master.entities.ProfileProfessionalInformationEntity;
import com.cos.master.entities.UserEntity;
import com.cos.master.repository.ProfileFamilyInformationRepository;
import com.cos.master.repository.ProfileMedicalInformationRepository;
import com.cos.master.repository.ProfilePersonalInformationRepository;
import com.cos.master.repository.ProfileProfessionalInformationRepository;
import com.cos.master.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProfileMedicalInformationRepository profileMedicalInformationRespo;
	
	@Autowired
	ProfileFamilyInformationRepository profileFamilyInformationRespo;
	
	@Autowired
	ProfileProfessionalInformationRepository profileProfessionalInformationRespo;
	
	@Autowired
	ProfilePersonalInformationRepository profilePersonalInformationRespo;
	
	public int createUser(String firstname, String lastname, String userId, String gender, String dateofbirth, String mobile, String password, String email, String address, String state, String country, String zipcode, LocalDate createdDate, LocalDate modifiedDate){
		return userRepo.createUser(firstname, lastname, userId, gender, dateofbirth, mobile, password, email, address, state, country, zipcode, createdDate, modifiedDate);
	}
	
	
	public UserEntity getUserInfo(String userId) {
		return userRepo.fetchByUserId(userId);
	}
	public ProfileMedicalInformationEntity getUserMedicalInfo(String userId) {
		return profileMedicalInformationRespo.fetchByUserId(userId);
	}
	
	public ProfileFamilyInformationEntity getUserFamilyInfo(String userId) {
		return profileFamilyInformationRespo.fetchByUserId(userId);
	}
	
	public ProfileProfessionalInformationEntity getUserProfessionalInfo(String userId) {
		return profileProfessionalInformationRespo.fetchByUserId(userId);
	}
	
	public ProfilePersonalInformationEntity getUserPersonalInfo(String userId) {
		return profilePersonalInformationRespo.fetchByUserId(userId);
	}
}

