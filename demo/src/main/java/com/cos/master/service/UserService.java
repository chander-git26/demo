package com.cos.master.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.master.entities.FamilyResponse;
import com.cos.master.entities.MedicalInfoResponse;
import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.ProfessionalResponse;
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
	
	public String getuseremail(String email) {
		return userRepo.getuseremail(email);
	}
	
	public PersonalInfoResponse getPersonalInfo(String userId) {
		PersonalInfoResponse personalInfo = new PersonalInfoResponse();
		List<Object[]> personalInfoList = userRepo.getPersonalInfo(userId);
		for (int i = 0; i < personalInfoList.size(); i++) {
			Object[] data = personalInfoList.get(i);
			personalInfo.setId(Integer.parseInt(setData(data, 0)));
			personalInfo.setAddress(setData(data, 1));
			personalInfo.setGender(setData(data, 2));
			personalInfo.setDateofbirth(setData(data, 3));
			personalInfo.setState(setData(data, 4));
			personalInfo.setCountry(setData(data, 5));
			personalInfo.setMarital_status(setData(data, 6));
			personalInfo.setBlood_Group(setData(data, 7));
			personalInfo.setHeight(Integer.parseInt(setData(data, 8)));
			personalInfo.setWeight(Integer.parseInt(setData(data, 9)));
			personalInfo.setSmoking(setData(data, 10));
			personalInfo.setAlcohol(setData(data, 11));
			
			
		}
		return personalInfo;
	}
	public ProfessionalResponse getProffesionalInfo(String userId) {
		ProfessionalResponse proffesionalInfo = new ProfessionalResponse();
		List<Object[]> professionalInfoList = userRepo.getProfessionallInfo(userId);
		for (int i = 0; i < professionalInfoList.size(); i++) {
			Object[] data = professionalInfoList.get(i);
			proffesionalInfo.setId(Integer.parseInt(setData(data, 0)));
			proffesionalInfo.setSource_of_income(setData(data, 1));
			proffesionalInfo.setCompany_name(setData(data, 2));
			proffesionalInfo.setBusiness_name(setData(data, 3));
			proffesionalInfo.setAnnual_business_revenue(setData(data, 4));;
			proffesionalInfo.setAnnual_salary(setData(data, 5));
		}
		return proffesionalInfo;

	}
	public FamilyResponse getFamilyInfo(String userId) {
		FamilyResponse familyInfo = new FamilyResponse();
		List<Object[]> professionalInfoList = userRepo.getFamilylInfo(userId);
		for (int i = 0; i < professionalInfoList.size(); i++) {
			Object[] data = professionalInfoList.get(i);
			familyInfo.setId(Integer.parseInt(setData(data, 0)));
			familyInfo.setFather_name(setData(data, 1));
			familyInfo.setAge(Integer.parseInt(setData(data, 2)));
			familyInfo.setFather_occupation(setData(data, 3));
			familyInfo.setFather_medical_history(setData(data, 4));
			familyInfo.setMother_name(setData(data, 5));
			familyInfo.setMother_age(Integer.parseInt(setData(data, 6)));
			familyInfo.setMother_occupation(setData(data, 7));
			familyInfo.setMother_medical_history(setData(data, 8));
			familyInfo.setSpouse_name(setData(data, 9));
//			familyInfo.setSpouse_age(Integer.parseInt(setData(data, 10)));
			familyInfo.setSpouse_occupation(setData(data, 11));
			familyInfo.setSpouse_medical_history(setData(data, 12));
			familyInfo.setNominee1_name(setData(data, 13));
			familyInfo.setNominee2_name(setData(data, 14));
			familyInfo.setOther_nominee_name(setData(data, 15));
//			familyInfo.setOther_nominee_age(Integer.parseInt(setData(data, 16)));
			familyInfo.setOther_nominee_relationship(setData(data, 17));
			
		}
		return familyInfo;

	}
	public MedicalInfoResponse getMedicalInfo(String userId) {
		MedicalInfoResponse medicalInfo = new MedicalInfoResponse();
		List<Object[]> professionalInfoList = userRepo.getMedicallInfo(userId);
		for (int i = 0; i < professionalInfoList.size(); i++) {
			Object[] data = professionalInfoList.get(i);
			medicalInfo.setId(Integer.parseInt(setData(data, 0)));
			medicalInfo.setPast_surgeries(setData(data, 1));
			medicalInfo.setBlood_pressure(setData(data, 2));
			medicalInfo.setDiabetes(setData(data, 3));
//			medicalInfo.setUpload_medical_history(setData(data, 3));
		}
		return medicalInfo;

	}
	public String setData(Object[] data, int index) {
		return data[index] !=null ? data[index].toString() : null;
	}
	public String getusername(String email) {
		return userRepo.fetchByUserInfo(email);
	}
}
