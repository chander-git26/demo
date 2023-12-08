package com.cos.master.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cos.master.entities.FamilyResponse;
import com.cos.master.entities.MedicalInfoResponse;
import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.ProfessionalResponse;
import com.cos.master.entities.ProfileFamilyInformationEntity;
import com.cos.master.entities.ProfileMedicalInformationEntity;
import com.cos.master.entities.ProfilePersonalInformationEntity;
import com.cos.master.entities.ProfileProfessionalInformationEntity;
import com.cos.master.entities.ResponseObject;
import com.cos.master.entities.UserEntity;
import com.cos.master.repository.ProfileFamilyInformationRepository;
import com.cos.master.repository.ProfileMedicalInformationRepository;
import com.cos.master.repository.ProfilePersonalInformationRepository;
import com.cos.master.repository.ProfileProfessionalInformationRepository;
import com.cos.master.repository.UserRepository;
import com.cos.master.security.AES;
import com.cos.master.security.Security;
import com.cos.master.service.UserService;
import com.cos.master.utils.AppUtils;



@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired(required = true)
	Security security;
	
	@Autowired
	AppUtils appUtils;
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	AES aes;
	
	@Autowired
	ProfileMedicalInformationRepository medicalInformationRepo;
	
	@Autowired
	ProfileFamilyInformationRepository familyInformationRepo;
	
	@Autowired
	ProfileProfessionalInformationRepository professionalInformationRepo;
	
	@Autowired
	ProfilePersonalInformationRepository personalInformationRepo;
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		UserEntity user = new UserEntity();
		UserEntity users = null;
		try {	
			if(userEntity != null) {
				user.setFirstname(userEntity.getFirstname());
				user.setLastname(userEntity.getLastname());
				String createdUserId = String.valueOf(appUtils.generateUserId());
				user.setUserId(createdUserId);
				String encryptPassword = AES.encrypt(userEntity.getPassword());
//
//				user.setId(3);
				user.setPassword(encryptPassword);
		      		user.setMobile(userEntity.getMobile());
				user.setEmail(userEntity.getEmail());
				user.setAddress(userEntity.getAddress());
				user.setDateofbirth(userEntity.getDateofbirth());
				user.setGender(userEntity.getGender());
				user.setState(userEntity.getState());
				user.setCountry(userEntity.getCountry());
				user.setZipcode(userEntity.getZipcode());
				user.setCreatedDate(LocalDate.now());
				user.setModifieddDate(LocalDate.now());
				users = userRepo.save(user);
//				int createUser = userService.createUser(user.getFirstname(),user.getLastname(),user.getUserId(),user.getGender(),user.getDateofbirth(),user.getMobile(),user.getPassword(),user.getEmail(),user.getAddress(),user.getState(),user.getCountry(),user.getZipcode(),user.getCreatedDate(),user.getModifieddDate());
				if(users != null) {
					return new ResponseEntity<>("200",HttpStatus.CREATED);
				}else {
					return new ResponseEntity<>("400",HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/getUserDetails/{userId}")
	public ResponseObject getUserDetails(@PathVariable("userId") String userId) {
			UserEntity userEntity = new UserEntity();
			UserEntity user = userService.getUserInfo(userId);
			if(user != null) {
				return appUtils.prepareResponse("Data fetch successfully", "successfull", "200", 1, user);
			}else {
				return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, user);
			}
	}
	
	@GetMapping("/getPersonalInfo/{userId}")
	public ResponseObject getPersonalInfo(@PathVariable("userId") Integer userId) {
		try {
			if (userId != 0) {
				String user_id = String.valueOf(userId);
				PersonalInfoResponse personalInfo = userService.getPersonalInfo(user_id);
				if (personalInfo.getId() != 0) {
					return appUtils.prepareResponse("Data fetch successfully", "successful", "200", 1, personalInfo);
				} else {
					return appUtils.prepareResponse("Data not found", "Failed", "400", 0, null);
				}
			} else {
				return appUtils.prepareResponse("user id cannot be 0", "failed", "500", 0, null);
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}

	}
	
	
	@GetMapping("/getProfessionalInfo/{userId}")
	public ResponseObject getProffessionalInfo(@PathVariable("userId") Integer userId) {
		try {
			if (userId != 0) {
				String user_id = String.valueOf(userId);
				ProfessionalResponse proffesionalInfo = userService.getProffesionalInfo(user_id);
				if (proffesionalInfo.getId() != 0) {
					return appUtils.prepareResponse("Data fetch successfully", "successfull", "200", 1, proffesionalInfo);
				} else {
					return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, proffesionalInfo);
				}
			} else {
				return appUtils.prepareResponse("user id cannot be 0", "failed", "500", 0, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}

	}
	
	@GetMapping("/getfamilyInfo/{userId}")
	public ResponseObject getfamilyInfo(@PathVariable("userId") Integer userId) {
		try {
			if (userId != 0) {
				UserEntity userEntity = new UserEntity();
				String user_id = String.valueOf(userId);
				FamilyResponse user = userService.getFamilyInfo(user_id);
				if (user != null) {
					return appUtils.prepareResponse("Data fetch successfully", "successfull", "200", 1, user);
				} else {
					return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, user);
				}
			} else {
				return appUtils.prepareResponse("user id cannot be 0", "failed", "500", 0, null);
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}
	}
	
	@GetMapping("/getMedicalInfo/{userId}")
	public ResponseObject getmedicalInfo(@PathVariable("userId") Integer userId) {
		try {	
			if (userId != 0) {
				UserEntity userEntity = new UserEntity();
				String user_id = String.valueOf(userId);
				MedicalInfoResponse medicalInfo = userService.getMedicalInfo(user_id);
				if (medicalInfo.getId() != 0) {
					return appUtils.prepareResponse("Data fetch successfully", "successfull", "200", 1, medicalInfo);
				} else {
					return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, medicalInfo);
				}
			} else {
				return appUtils.prepareResponse("user id cannot be 0", "failed", "500", 0, null);
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}

	}
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody UserEntity userInf) throws Exception {
		UserEntity user = new UserEntity();
		
		String email = userInf.getEmail();
	    String password = userInf.getPassword();
//	    String encryptPassword = aes.encrypt(password);
		String userpassword = userService.getusername(email);
		String decryptPassword = aes.decrypt(userpassword);
//      	users = userRepo.save(user);
		if(password.equals(decryptPassword)) {
			return new ResponseEntity<>("200",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("400",HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/personalInformation")
	public ResponseEntity<?> createUserProfilePersonalInformation(@RequestBody ProfilePersonalInformationEntity profilePersonalInformationEntity) {
		ProfilePersonalInformationEntity userprofilepersonalUpdate = new ProfilePersonalInformationEntity();
		ProfilePersonalInformationEntity userss = null;
		try {
		if(profilePersonalInformationEntity != null) {
			userprofilepersonalUpdate.setAadharNumber(profilePersonalInformationEntity.getAadharNumber());
			userprofilepersonalUpdate.setPanNumber(profilePersonalInformationEntity.getPanNumber());
			userprofilepersonalUpdate.setUserId(profilePersonalInformationEntity.getUserId());
			userprofilepersonalUpdate.setMaritalStatus(profilePersonalInformationEntity.getMaritalStatus());
			userprofilepersonalUpdate.setBloodGroup(profilePersonalInformationEntity.getBloodGroup());
			userprofilepersonalUpdate.setHeight(profilePersonalInformationEntity.getHeight());
			userprofilepersonalUpdate.setWeight(profilePersonalInformationEntity.getWeight());
			userprofilepersonalUpdate.setSmoking(profilePersonalInformationEntity.getSmoking());
			userprofilepersonalUpdate.setAlochol(profilePersonalInformationEntity.getAlochol());
			ProfilePersonalInformationEntity createUserProfilePersonalInformation = personalInformationRepo.save(userprofilepersonalUpdate);
			if(createUserProfilePersonalInformation != null) {
				return new ResponseEntity<>("200",HttpStatus.CREATED);
//				return new ResponseEntity<>(createUserProfilePersonalInformation,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("400",HttpStatus.OK);
			}
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	ProfessionalInformation:-----
	
	@PostMapping("/professionalInformation")
	public ResponseEntity<?> createUserProfileProfessionalInformation(@RequestBody ProfileProfessionalInformationEntity profileProfessionalInformationEntity) {
		ProfileProfessionalInformationEntity userprofileprofessionalUpdate = new ProfileProfessionalInformationEntity();
		ProfileProfessionalInformationEntity userss = null;
	
		try {
			if(profileProfessionalInformationEntity != null) {
				
				userprofileprofessionalUpdate.setSourceOfIncome(profileProfessionalInformationEntity.getSourceOfIncome());
				
				userprofileprofessionalUpdate.setCompanyName(profileProfessionalInformationEntity.getCompanyName());
				userprofileprofessionalUpdate.setBussinessName(profileProfessionalInformationEntity.getBussinessName());

				userprofileprofessionalUpdate.setUserId(profileProfessionalInformationEntity.getUserId());

				
				userprofileprofessionalUpdate.setAnnualIncome(profileProfessionalInformationEntity.getAnnualIncome());
				userprofileprofessionalUpdate.setBusinessAnnualRevenue(profileProfessionalInformationEntity.getBusinessAnnualRevenue());
			
			
				
				ProfileProfessionalInformationEntity createUserProfileProfessionalInformation = professionalInformationRepo.save(userprofileprofessionalUpdate);

				if(createUserProfileProfessionalInformation != null) {
					return new ResponseEntity<>("200",HttpStatus.CREATED);
				}
				else {
					return new ResponseEntity<>("400",HttpStatus.OK);
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
	}	
//	FamilyInformation:------
	@PostMapping(value ="/familyInformation",produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseObject createUserProfileFamilyInformation(@RequestBody ProfileFamilyInformationEntity profileFamilyUpdate){
		ProfileFamilyInformationEntity userProfileFamilyUpdate = new ProfileFamilyInformationEntity();
		ProfileFamilyInformationEntity userss = null;
		try {
			if (profileFamilyUpdate != null) {
				userProfileFamilyUpdate.setUserId(profileFamilyUpdate.getUserId());
				userProfileFamilyUpdate.setFatherName(profileFamilyUpdate.getFatherName());
				userProfileFamilyUpdate.setFatherAge(profileFamilyUpdate.getFatherAge());
				userProfileFamilyUpdate.setFatherOccupation(profileFamilyUpdate.getFatherOccupation());
				userProfileFamilyUpdate.setMotherName(profileFamilyUpdate.getMotherName());
				userProfileFamilyUpdate.setMotherAge(profileFamilyUpdate.getMotherAge());
				userProfileFamilyUpdate.setMotherOccupation(profileFamilyUpdate.getMotherOccupation());
				userProfileFamilyUpdate.setSpouseName(profileFamilyUpdate.getSpouseName());
				userProfileFamilyUpdate.setSpouseAge(profileFamilyUpdate.getSpouseAge());
				userProfileFamilyUpdate.setSpouseOccupation(profileFamilyUpdate.getSpouseOccupation());
				userProfileFamilyUpdate.setNominee1Name(profileFamilyUpdate.getNominee1Name());
				userProfileFamilyUpdate.setNominee2Name(profileFamilyUpdate.getNominee2Name());
				userProfileFamilyUpdate.setOtherNomineeName(profileFamilyUpdate.getOtherNomineeName());
				userProfileFamilyUpdate.setOtherNomineeAge(profileFamilyUpdate.getOtherNomineeAge());
				userProfileFamilyUpdate.setMaritalStatus(profileFamilyUpdate.getMaritalStatus());
				userProfileFamilyUpdate.setOtherNomineeRelation(profileFamilyUpdate.getOtherNomineeRelation());
//				userprofilefamilyUpdate.setFater_upload_medical_history(((ProfileFamilyInformationEntity) father_upload_medical_history).getFater_upload_medical_history());
////				userprofilefamilyUpdate.setMother_upload_medical_history(((ProfileFamilyInformationEntity) mother_upload_medical_history).getMother_upload_medical_history());
////				userprofilefamilyUpdate.setSpouse_upload_medical_history(((ProfileFamilyInformationEntity) spouse_upload_medical_history).getSpouse_upload_medical_history());
////				userprofilefamilyUpdate.setNomine1_upload_medical_history(((ProfileFamilyInformationEntity) nomine1_upload_medical_history).getNomine1_upload_medical_history());
////				userprofilefamilyUpdate.setNomine2_upload_medical_history(((ProfileFamilyInformationEntity) nomine2_upload_medical_history).getNomine2_upload_medical_history());
				userProfileFamilyUpdate.setSelectNumberOfChildren(profileFamilyUpdate.getSelectNumberOfChildren());
				ProfileFamilyInformationEntity createUserProfileFamilyInformation = familyInformationRepo.save(userProfileFamilyUpdate);
				if (createUserProfileFamilyInformation != null) {
					return appUtils.prepareResponse("Data saved successfully", "Success", "200", 1, createUserProfileFamilyInformation);
				} else {
					return appUtils.prepareResponse("Failed to save Data", "Failed", "400", 1, null);
				}
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("internal server error", "Failer","500", 1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUtils.prepareResponse("internal server error", "Failer","500", 1, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	MedicalInformation:-------
	
	@PostMapping("/medicalInformation")
	public ResponseEntity<?> createUserProfileMedicallInformation(@RequestBody ProfileMedicalInformationEntity profileMedicalInformationEntity) {
		ProfileMedicalInformationEntity userprofilemedicalUpdate = new ProfileMedicalInformationEntity();
		try {
			if (profileMedicalInformationEntity != null) {	
				userprofilemedicalUpdate.setUserId(profileMedicalInformationEntity.getUserId());
				userprofilemedicalUpdate.setPastSurgeries(profileMedicalInformationEntity.getPastSurgeries());
				userprofilemedicalUpdate.setBloodPressure(profileMedicalInformationEntity.getBloodPressure());
				userprofilemedicalUpdate.setDiabetes(profileMedicalInformationEntity.getDiabetes());
				ProfileMedicalInformationEntity createUserProfileMedicalInformation = medicalInformationRepo.save(userprofilemedicalUpdate);

				if (createUserProfileMedicalInformation != null) {
					return new ResponseEntity<>("200", HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("400", HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}




	
	
	

	
