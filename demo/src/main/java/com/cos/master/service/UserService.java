package com.cos.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.master.entities.FamilyResponse;
import com.cos.master.entities.MedicalInfoResponse;
import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.ProfessionalResponse;
import com.cos.master.entities.UserEntity;
import com.cos.master.repository.UserRepository;
import com.cos.master.security.AES;

import java.sql.Blob;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AES aes;
	
	public UserEntity getUserInfo(String userId) {
		
		return userRepo.fetchByUserId(userId);
	}
	
	public String getUserEmail(String email) {
		return userRepo.getUserEmail(email);
	}
	public int getUserOtp(String mobile) {
		return userRepo.getUserOtp(mobile);
	}
	
//	public int updatePassword(String password,String mobile)
//	{
//		return  userRepo.updatePassword(password,mobile);
//	}
	
	public String getUserMobile(String mobile) {
		return userRepo.getUserMobile(mobile);
	}
	
	public PersonalInfoResponse getPersonalInfo(String userId) {
		PersonalInfoResponse personalInfo = new PersonalInfoResponse();
		List<Object[]> personalInfoList = userRepo.getPersonalInfo(userId);
		for (int i = 0; i < personalInfoList.size(); i++) {
			Object[] data = personalInfoList.get(i);
			personalInfo.setId(Integer.parseInt(setData(data, 0)));
			personalInfo.setAddress(setData(data, 1));
			personalInfo.setGender(setData(data, 2));
			personalInfo.setDateOfBirth(setData(data, 3));
			personalInfo.setState(setData(data, 4));
			personalInfo.setPincode(Integer.parseInt(setData(data, 5)));
			personalInfo.setMaritalStatus(setData(data, 6));
			personalInfo.setHeight(Integer.parseInt(setData(data, 7)));
			personalInfo.setWeight(Integer.parseInt(setData(data, 8)));
			personalInfo.setSmoking(setData(data, 9));
			personalInfo.setAlcohol(setData(data, 10));
			
			
		}
		return personalInfo;
	}
	public ProfessionalResponse getProffesionalInfo(String userId) {
		ProfessionalResponse proffesionalInfo = new ProfessionalResponse();
		List<Object[]> professionalInfoList = userRepo.getProfessionallInfo(userId);
		for (int i = 0; i < professionalInfoList.size(); i++) {
			Object[] data = professionalInfoList.get(i);
			proffesionalInfo.setId(Integer.parseInt(setData(data, 0)));
			proffesionalInfo.setSourceOfIncome(setData(data, 1));
			proffesionalInfo.setCompanyName(setData(data, 2));
			proffesionalInfo.setBusinessName(setData(data, 3));
			proffesionalInfo.setAnnualIncome(Integer.parseInt(setData(data, 4)));
			proffesionalInfo.setBusinessAnnualRevenue(Integer.parseInt(setData(data, 5)));
			proffesionalInfo.setGstNumber(setData(data, 6));
			proffesionalInfo.setInvestAmount(Integer.parseInt(setData(data, 7)));
		}
		return proffesionalInfo;

	}
	public FamilyResponse getFamilyInfo(String userId) {
		FamilyResponse familyInfo = new FamilyResponse();
		List<Object[]> professionalInfoList = userRepo.getFamilylInfo(userId);
		for (int i = 0; i < professionalInfoList.size(); i++) {
			Object[] data = professionalInfoList.get(i);
			familyInfo.setId(Integer.parseInt(setData(data, 0)));
			familyInfo.setFatherName(setData(data, 1));
			familyInfo.setFatherAge(Integer.parseInt(setData(data, 2)));
			familyInfo.setFatherOccupation(setData(data, 3));
			familyInfo.setFatherUploadMedicalHistory(setData(data, 4));
			familyInfo.setMotherName(setData(data, 5));
			familyInfo.setMotherAge(Integer.parseInt(setData(data, 6)));
			familyInfo.setMotherOccupation(setData(data, 7));
			familyInfo.setMotherUploadMedicalHistory(setData(data, 8));
			familyInfo.setSpouseName(setData(data, 9));
			familyInfo.setSpouseAge(Integer.parseInt(setData(data, 10)));
			familyInfo.setSpouseOccupation(setData(data, 11));
			familyInfo.setSpouseUploadMedicalHistory(setData(data, 12));
			familyInfo.setOtherNomineeName(setData(data, 13));
			familyInfo.setOtherNomineeAge(Integer.parseInt(setData(data, 14)));
		    familyInfo.setOtherNomineeOccupation(setData(data,15));
		    familyInfo.setOtherNomineeUploadMedicalHistory(setData(data, 16));
			
		}
		return familyInfo;

	}
	public MedicalInfoResponse getMedicalInfo(String userId) {
		MedicalInfoResponse medicalInfo = new MedicalInfoResponse();
		List<Object[]> professionalInfoList = userRepo.getMedicallInfo(userId);
		for (int i = 0; i < professionalInfoList.size(); i++) {
			Object[] data = professionalInfoList.get(i);
			medicalInfo.setId(Integer.parseInt(setData(data, 0)));
			medicalInfo.setPastSurgeries(setData(data, 1));
			//medicalInfo.setBloodPressure(Integer.parseInt(setData(data, 2)));
			//medicalInfo.setDiabetes(setData(data, 3));
//			medicalInfo.setUpload_medical_history(setData(data, 3));
			medicalInfo.setCovidStatus(setData(data, 2));
			medicalInfo.setCurrentTreatments(setData(data, 3));
			medicalInfo.setBloodPressure(setData(data, 4));
			medicalInfo.setDiabetes(setData(data, 5));
			medicalInfo.setHeartStroke(setData(data, 6));
			medicalInfo.setOtherReport(setData(data, 7));
		}
		return medicalInfo;

	}
	public byte[] getMedicallInfo(String userId) {
		return userRepo.getMedicalInfo(userId);
	}
	public int updateAddress(String address,int id) {
		return userRepo.updateAddress(address, id);
	}
	
	
	public int updateGender(String gender,int id) {
		return userRepo.updategender(gender,id);
	}
	
	public int updateDateOfBirth(String dateofbirth,int id) {
		return userRepo.updatedateofbirth(dateofbirth,id);
	}
	
	public int updateState(String state,int id) {
		return userRepo.updatestate(state,id);
	}
	
	public int updatePincode(int pincode,int id) {
		return userRepo.updatepincode(pincode,id);
	}
	
	public int updateMaritalStatus(String maritalStatus,int id) {
		return userRepo.updateMaritalStatus(maritalStatus,id);
	}

//	public int updateBloodGroup(String bloodGroup,int id) {
//		return userRepo.updateBloodGroup(bloodGroup,id);
//	}
	
	public int updateHeight(int height,int id) {
		return userRepo.updateHeight(height,id);
	}
	
	public int updateWeight(int weight,int id) {
		return userRepo.updateWeight(weight,id);
	}
	
	public int updateSmokingStatus(String smoking,int id) {
		return userRepo.updateSmoking(smoking,id);
	}
	
	public int updateAlocholStatus(String alochol,int id) {
		return userRepo.updateAlochol(alochol,id);
	}
	
	public String setData(Object[] data, int index) {
		return data[index] !=null ? data[index].toString() : null;
	}
	
	
	public String verifyMobileNumber(String mobileNumber) {
		return userRepo.getMobileNumber(mobileNumber);
	}
	
	public String getusername(String email) {
		return userRepo.fetchByUserInfo(email);
	}
	
	public int updateFatherName(String father_name,int id) {
		return userRepo.updateFatherName(father_name,id);
	}
	
	public int updateAge(int father_age,int id) {
		return userRepo.updateAge(father_age,id);
	}
	
	public int upadteFatherOccupation(String father_occupation,int id) {
		return userRepo.upadteFatherOccupation(father_occupation,id);
	}
	
	public int updateMotherAge(int mother_age,int id) {
		return userRepo.updateMotherAge(mother_age,id);
	}
	
	public int upadteMotherName(String mother_Name,int id) {
		return userRepo.upadteMotherName(mother_Name,id);
	}
	
	public int upadteMotherOccupation(String mother_occupation,int id) {
		return userRepo.upadteMotherOccupation(mother_occupation,id);
	}
	public int upadteSpouseName(String spouse_name,int id) {
		return userRepo.upadteSpouseName(spouse_name,id);
	}
	
	public int updateSpouseAge(int spouse_age,int id) {
		return userRepo.updateSpouseAge(spouse_age,id);
	}
	
	public int updateSpouseOccupation(String spouse_occupation,int id) {
		return userRepo.updateSpouseOccupation(spouse_occupation,id);
	}
	
//	public int updateNominee1Name(String nominee1_name,int id) {
//		return userRepo.updateNominee1Name(nominee1_name,id);
//	}
//	
//	public int updateNominee2Name(String other_nominee_name,int id) {
//		return userRepo.updateNominee2Name(other_nominee_name,id);
//	}
	
	public int updateOtherNomineeName(String other_nominee_name,int id) {
		return userRepo.updateOtherNomineeName(other_nominee_name,id);
	}
	
	public int updateOtherNomineeAge(int other_nominee_age,int id) {
		return userRepo.updateOtherNomineeAge(other_nominee_age,id);
	}
	
	public int updateOtherNomineeOccupation(String other_nominee_occupation,int id) {
		return userRepo.updateOtherNomineeOccupation(other_nominee_occupation,id);
	}
	
//	public int updateOtherNomineeRelation(String other_nominee_relation,int id) {
//		return userRepo.updateOtherNomineeRelation(other_nominee_relation,id);
//	}
//
//	public int updateSelectNumberOfChildren(int select_number_of_children,int id) {
//		return userRepo.updateSelectNumberOfChildren(select_number_of_children,id);
//	}
	
	public int updatePastSurgeries(String past_surgeries,int id) {
		return userRepo.updatePastSurgeries(past_surgeries,id);
	}
	
	public int updateBloodPressure(String blood_pressure,int id) {
		return userRepo.updateBloodPressure(blood_pressure,id);
	}

	public int updateDiabetes(String diabetes,int id) {
		return userRepo.updateDiabetes(diabetes,id);
	}
	public int uploadProfile1(int userId,Blob profile) {
		return userRepo.uploadProfile(userId, profile);
	}

	public List<UserEntity> viewAll() {
		return (List<UserEntity>) userRepo.findAll();
	}

	public UserEntity viewById(int id) {
		return userRepo.findById(id).get();
	}
	public int updateSourceofIncome(String source_of_income,int id) {
		return userRepo.updateSourceofIncome(source_of_income,id);
	}
	public int updateCompanyName(String company_name,int id) {
		return userRepo.updateCompanyName(company_name,id);
	}
	public int updateBusinessName(String business_name,int id) {
		return userRepo.updateBusinessName(business_name,id);
	}
	public int updateBusinessannualRevenue(int business_annual_revenue,int id) {
		return userRepo.updateBusinessannualRevenue(business_annual_revenue,id);
	}
	public int updateannualIncome(int annual_income,int id) {
		return userRepo.updateannualIncome(annual_income,id);
	}
	
	public int uploadProfile(int userId,Blob image) {
		return userRepo.uploadProfile(userId, image);
	}

	public List<UserEntity> viewAll1() {
		return (List<UserEntity>) userRepo.findAll();
	}

	public UserEntity viewById1(int id) {
		return userRepo.findById(id).get();
	}

	
}
