package com.cos.master.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.master.entities.FamilyResponse;
import com.cos.master.entities.MedicalInfoResponse;
import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.ProfessionalResponse;
import com.cos.master.entities.UserEntity;
import com.cos.master.entities.UserResponse;
import com.cos.master.repository.UserRepository;
import com.cos.master.security.AES;

import java.nio.charset.StandardCharsets;
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

	public UserResponse getUserOtp(String value) {
		List<Object[]> userData = null;
		UserResponse userResponse = new UserResponse();
		boolean containsDigit = false;
		if (Character.isDigit(value.charAt(0))) {
			containsDigit = true;
		}
		if (containsDigit == true) {
			userData = userRepo.getUserOtpByMobile(value);
			for (int i = 0; i < userData.size(); i++) {
				Object[] data = userData.get(i);
				userResponse.setMobile(setData(data, 0));
				userResponse.setOtp(setData(data, 1));
			}
		}
		else {
			userData = userRepo.getUserOtpByEmail(value);
			for (int i = 0; i < userData.size(); i++) {
				Object[] data = userData.get(i);
				userResponse.setEmail(setData(data, 0));
				userResponse.setOtp(setData(data, 1));
			}
		}
		return userResponse;
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
			personalInfo.setHeight(setData(data, 7));
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
			proffesionalInfo.setAnnualIncome(setData(data, 4));
			proffesionalInfo.setBusinessAnnualRevenue(setData(data, 5));
			proffesionalInfo.setGstNumber(setData(data, 6));
			proffesionalInfo.setInvestAmount(setData(data, 7));
		}
		return proffesionalInfo;

	}

//   public byte[] getFamilyInfo(String userId) {
//	return userRepo.getFamilyInfo(userId);
//}
	public FamilyResponse getFamilyInfo(String userId) {
		FamilyResponse familyInfo = new FamilyResponse();
		List<Object[]> professionalInfoList = userRepo.getFamilylInfo(userId);
		for (int i = 0; i < professionalInfoList.size(); i++) {
			Object[] data = professionalInfoList.get(i);
			familyInfo.setId(Integer.parseInt(setData(data, 0)));
			familyInfo.setFatherName(setData(data, 1));
			familyInfo.setFatherAge(Integer.parseInt(setData(data, 2)));
			familyInfo.setFatherOccupation(setData(data, 3));
//			familyInfo.setFatherUploadMedicalHistory(setData(data, 4));
			familyInfo.setMotherName(setData(data, 5));
			familyInfo.setMotherAge(Integer.parseInt(setData(data, 6)));
			familyInfo.setMotherOccupation(setData(data, 7));
//			familyInfo.setMotherUploadMedicalHistory(setData(data, 8));
			familyInfo.setSpouseName(setData(data, 9));
			familyInfo.setSpouseAge(Integer.parseInt(setData(data, 10)));
			familyInfo.setSpouseOccupation(setData(data, 11));
//			familyInfo.setSpouseUploadMedicalHistory(setData(data, 12));
			familyInfo.setOtherNomineeName(setData(data, 13));
			familyInfo.setOtherNomineeAge(Integer.parseInt(setData(data, 14)));
			familyInfo.setOtherNomineeOccupation(setData(data, 15));
//			familyInfo.setUploadOtherNomineeMedicalHistory(setData(data, 16));

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
			medicalInfo.setBloodGroup(setData(data, 2));
//			medicalInfo.setUploadAsthmaReport(convertStringToByteArray(setData(data, 3)));
//			medicalInfo.setUploadBpReport(convertStringToByteArray(setData(data, 4)));
//			medicalInfo.setUploadDiabetesReport(convertStringToByteArray(setData(data, 5)));
//			medicalInfo.setUploadHeartStrokeReport(convertStringToByteArray(setData(data, 6)));
//			medicalInfo.setUploadOtherReport(convertStringToByteArray(setData(data, 7)));
			medicalInfo.setCurrentTreatments(setData(data, 8));
			medicalInfo.setCovidStatus(setData(data, 9));

		}
		return medicalInfo;

	}

//	public byte[] getMedicalBpInfo(String userId) {
//		MedicalInfoResponse medicalInfo = new MedicalInfoResponse();
//		List<Object[]> professionalInfoList = userRepo.getMedicalBpInfo(userId);
//		for (int i = 0; i < professionalInfoList.size(); i++) {
//			Object[] data = professionalInfoList.get(i);
//			
//			medicalInfo.setUploadBpReport(convertStringToByteArray(setData(data, 1)));
//			medicalInfo.setUploadDiabetesReport(convertStringToByteArray(setData(data, 2)));
//			medicalInfo.setUploadHeartStrokeReport(convertStringToByteArray(setData(data, 3)));
//			medicalInfo.setUploadOtherReport(convertStringToByteArray(setData(data, 4)));
//
//		}
//		return medicalInfo.getUploadBpReport();
////		return medicalInfo.getUploadDiabetesReport();
////		return medicalInfo.getUploadHeartStrokeReport();
////		return medicalInfo.getUploadOtherReport();
//
//	}
//
//	public byte[] getMedicalDiabetesInfo(String userId) {
//		MedicalInfoResponse medicalInfo = new MedicalInfoResponse();
//		List<Object[]> professionalInfoList = userRepo.getMedicallInfo(userId);
//		for (int i = 0; i < professionalInfoList.size(); i++) {
//			Object[] data = professionalInfoList.get(i);
//	        
//	        medicalInfo.setUploadBpReport(convertStringToByteArray(setData(data, 1)));
//	        medicalInfo.setUploadDiabetesReport(convertStringToByteArray(setData(data, 2)));
//	        medicalInfo.setUploadHeartStrokeReport(convertStringToByteArray(setData(data, 3)));
//	        medicalInfo.setUploadOtherReport(convertStringToByteArray(setData(data, 4)));
//
//		}
//		return medicalInfo.getUploadDiabetesReport();
//
//	}

	public byte[] getFatherMedicalHistoryInfo(String userId) {

		return userRepo.getFamilyInfo(userId);
	}

	public byte[] getMotherUploadMedicalHistoryInfo(String userId) {

		return userRepo.getFamilyInfo(userId);
	}

	public byte[] getSpouseUploadMedicalHistoryInfo(String userId) {

		return userRepo.getFamilyInfo(userId);
	}

	public byte[] getOtherMedicalHistoryInfo(String userId) {

		return userRepo.getFamilyInfo(userId);
	}

	public byte[] getMedicalBpInfo(String userId) {

		return userRepo.getMedicalInfo(userId);
	}

	public byte[] getMedicalDiabetesInfo(String userId) {

		return userRepo.getMedicalInfo(userId);
	}

	public byte[] getMedicalHeartStrokeInfo(String userId) {

		return userRepo.getMedicalInfo(userId);
	}

	public byte[] getMedicalOtherInfo(String userId) {

		return userRepo.getMedicalInfo(userId);
	}

//	public byte[] getMedicallInfo(String userId) {
//		return userRepo.getMedicalInfo(userId);
//	}

	private byte[] convertStringToByteArray(String data) {
		// Example conversion using UTF-8 encoding
		return data.getBytes(StandardCharsets.UTF_8);
	}

	public byte[] getMedicallInfo(String userId) {
		return userRepo.getMedicalInfo(userId);
	}

	public int updateAddress(String address, int id) {
		return userRepo.updateAddress(address, id);
	}

	public int updateGender(String gender, int id) {
		return userRepo.updategender(gender, id);
	}

	public int updateDateOfBirth(String dateofbirth, int id) {
		return userRepo.updatedateofbirth(dateofbirth, id);
	}

	public int updateState(String state, int id) {
		return userRepo.updatestate(state, id);
	}

	public int updatePincode(int pincode, int id) {
		return userRepo.updatepincode(pincode, id);
	}

	public int updateMaritalStatus(String maritalStatus, int id) {
		return userRepo.updateMaritalStatus(maritalStatus, id);
	}

//	public int updateBloodGroup(String bloodGroup,int id) {
//		return userRepo.updateBloodGroup(bloodGroup,id);
//	}

	public int updateHeight(String height, int id) {
		return userRepo.updateHeight(height, id);
	}

	public int updateWeight(int weight, int id) {
		return userRepo.updateWeight(weight, id);
	}

	public int updateSmokingStatus(String smoking, int id) {
		return userRepo.updateSmoking(smoking, id);
	}

	public int updateAlocholStatus(String alochol, int id) {
		return userRepo.updateAlochol(alochol, id);
	}

	public String setData(Object[] data, int index) {
		return data[index] != null ? data[index].toString() : null;
	}

	public UserResponse verifyMobileNumber(String mobileNumber) {
		List<Object[]> list = userRepo.getMobileNumber(mobileNumber);
		UserResponse userResponse = new UserResponse();
		for (int i = 0; i < list.size(); i++) {
			Object[] data = list.get(i);
			userResponse.setUserId(setData(data, 0));
			userResponse.setMobile(setData(data, 1));
		}
		 return userResponse;
	}
	
	public String verifyMobileNum(String mobileNumber) {
		return userRepo.verifyMobileNum(mobileNumber);
	}
	public UserResponse getuserInfo(String email) {
		List<Object[]> list = userRepo.fetchByUserInfo(email);
		UserResponse userResponse = new UserResponse();
		for (int i = 0; i < list.size(); i++) {
			Object[] data = list.get(i);
			userResponse.setId(setData(data, 0));
			userResponse.setFirstName(setData(data, 1));
			userResponse.setLastName(setData(data, 2) );
			userResponse.setUserId(setData(data, 3));
			userResponse.setMobile(setData(data, 4));
			userResponse.setEmail(setData(data, 5));
			userResponse.setPassword(setData(data, 6));
		}
		return userResponse;
	}

	public int updateFatherName(String father_name, int id) {
		return userRepo.updateFatherName(father_name, id);
	}

	public int updateFatherAge(int father_age, int id) {
		return userRepo.updateFatherAge(father_age, id);
	}

	public int upadteFatherOccupation(String father_occupation, int id) {
		return userRepo.upadteFatherOccupation(father_occupation, id);
	}

	public int updateFatherUploadMedicalHistory(byte[] father_upload_medical_history, int id) {
		return userRepo.updateFatherUploadMedicalHistory(father_upload_medical_history, id);
	}

	public int updateMotherAge(int mother_age, int id) {
		return userRepo.updateMotherAge(mother_age, id);
	}

	public int upadteMotherName(String mother_Name, int id) {
		return userRepo.upadteMotherName(mother_Name, id);
	}

	public int upadteMotherOccupation(String mother_occupation, int id) {
		return userRepo.upadteMotherOccupation(mother_occupation, id);
	}

	public int updateMotherUploadMedicalHistory(byte[] mother_upload_medical_history, int id) {
		return userRepo.updateMotherUploadMedicalHistory(mother_upload_medical_history, id);
	}

	public int upadteSpouseName(String spouse_name, int id) {
		return userRepo.upadteSpouseName(spouse_name, id);
	}

	public int updateSpouseAge(int spouse_age, int id) {
		return userRepo.updateSpouseAge(spouse_age, id);
	}

	public int updateSpouseOccupation(String spouse_occupation, int id) {
		return userRepo.updateSpouseOccupation(spouse_occupation, id);
	}

	public int updateSpouseUploadMedicalHistory(byte[] spouse_upload_medical_history, int id) {
		return userRepo.updateSpouseUploadMedicalHistory(spouse_upload_medical_history, id);
	}

//	public int updateNominee1Name(String nominee1_name,int id) {
//		return userRepo.updateNominee1Name(nominee1_name,id);
//	}
//	
//	public int updateNominee2Name(String other_nominee_name,int id) {
//		return userRepo.updateNominee2Name(other_nominee_name,id);
//	}

	public int updateOtherNomineeName(String other_nominee_name, int id) {
		return userRepo.updateOtherNomineeName(other_nominee_name, id);
	}

	public int updateOtherNomineeAge(int other_nominee_age, int id) {
		return userRepo.updateOtherNomineeAge(other_nominee_age, id);
	}

	public int updateOtherNomineeOccupation(String other_nominee_occupation, int id) {
		return userRepo.updateOtherNomineeOccupation(other_nominee_occupation, id);
	}

	public int updateOtherNomineeMedicalHistory(byte[] upload_other_nominee_medical_history, int id) {
		return userRepo.updateUploadOtherNomineeMedicalHistory(upload_other_nominee_medical_history, id);
	}

//	public int updateOtherNomineeRelation(String other_nominee_relation,int id) {
//		return userRepo.updateOtherNomineeRelation(other_nominee_relation,id);
//	}
//
//	public int updateSelectNumberOfChildren(int select_number_of_children,int id) {
//		return userRepo.updateSelectNumberOfChildren(select_number_of_children,id);
//	}

	public int updatePastSurgeries(String past_surgeries, int id) {
		return userRepo.updatePastSurgeries(past_surgeries, id);
	}

	public int updateBloodPressure(String blood_pressure, int id) {
		return userRepo.updateBloodPressure(blood_pressure, id);
	}

	public int updateDiabetes(String diabetes, int id) {
		return userRepo.updateDiabetes(diabetes, id);
	}

	public int uploadProfile1(int userId, Blob profile) {
		return userRepo.uploadProfile(userId, profile);
	}

	public List<UserEntity> viewAll() {
		return (List<UserEntity>) userRepo.findAll();
	}

	public UserEntity viewById(int id) {
		return userRepo.findById(id).get();
	}

	public int updateSourceofIncome(String source_of_income, int id) {
		return userRepo.updateSourceofIncome(source_of_income, id);
	}

	public int updateCompanyName(String company_name, int id) {
		return userRepo.updateCompanyName(company_name, id);
	}

	public int updateBusinessName(String business_name, int id) {
		return userRepo.updateBusinessName(business_name, id);
	}

	public int updateBusinessannualRevenue(int business_annual_revenue, int id) {
		return userRepo.updateBusinessannualRevenue(business_annual_revenue, id);
	}

	public int updateAnnualIncome(int annual_income, int id) {
		return userRepo.updateAnnualIncome(annual_income, id);
	}

	public int updateGstNumber(int gst_umber, int id) {
		return userRepo.updateGstNumber(gst_umber, id);
	}

	public int updateBusinessType(String business_type, int id) {
		return userRepo.updateBusinessType(business_type, id);
	}

	public int updateInvestAmount(int invest_amount, int id) {
		return userRepo.updateInvestAmount(invest_amount, id);
	}

	public int uploadProfile(int userId, Blob image) {
		return userRepo.uploadProfile(userId, image);
	}

	public List<UserEntity> viewAll1() {
		return (List<UserEntity>) userRepo.findAll();
	}

	public UserEntity viewById1(int id) {
		return userRepo.findById(id).get();
	}

	public int updateCurrentTreatments(String current_treatments, int id) {
		return userRepo.updateCurrentTreatments(current_treatments, id);
	}

	public int updateCovidStatus(String covid_status, int id) {
		return userRepo.updateCovidStatus(covid_status, id);
	}
	
	public UserResponse verifyEmail(String emailId) {
		 List<Object[]> dataList = userRepo.verifyEmail(emailId);
			UserResponse userResponse = new UserResponse();
			for (int i = 0; i < dataList.size(); i++) {
				Object[] data = dataList.get(i);
				userResponse.setUserId(setData(data, 0));
				userResponse.setEmail(setData(data, 1));
			}
		 return userResponse;
	}
}
