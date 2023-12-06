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
import com.cos.master.security.Security;
import com.cos.master.service.UserService;
import com.cos.master.utils.AppProfileFamilyInformationUtils;
import com.cos.master.utils.AppProfileMedicalInformationUtils;
import com.cos.master.utils.AppProfilePersonalInformationUtils;
import com.cos.master.utils.AppProfileProfessionalInformationUtils;
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
	AppProfileMedicalInformationUtils appProfileMedicalInformationUtils;
	
	@Autowired
	ProfileMedicalInformationRepository profileMedicalInformationRepo;
	
//	ll
	@Autowired
	AppProfileFamilyInformationUtils appProfileFamilyInformationUtils;
	
	@Autowired
	ProfileFamilyInformationRepository profileFamilyInformationRepo;
	
//	kk
	
	@Autowired
	AppProfileProfessionalInformationUtils appProfileProfessionalInformationUtils;
	
	@Autowired
	ProfileProfessionalInformationRepository profileProfessionalInformationRepo;
	
//	kd
	
	@Autowired
	AppProfilePersonalInformationUtils appProfilePersonalInformationUtils;
	
	@Autowired
	ProfilePersonalInformationRepository profilePersonalInformationRepo;
	
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
				String encryptPassword = security.encryptString(userEntity.getPassword());
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
				return appUtils.prepareResponse("Data fetch successful", "successfull", "200", 1, user);
			}else {
				return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, user);
			}
	}
	

	
	
	

	
//	PersonalInformation:-------
	
	@PostMapping("/PersonalInformation")
	public ResponseEntity<?> createUserProfilePersonalInformation(@RequestBody ProfilePersonalInformationEntity profilePersonalInformationEntity) {
		ProfilePersonalInformationEntity userprofilepersonalUpdate = new ProfilePersonalInformationEntity();
		ProfilePersonalInformationEntity userss = null;
		try {
		if(profilePersonalInformationEntity != null) {
			userprofilepersonalUpdate.setAadharnumber(profilePersonalInformationEntity.getAadharnumber());
			userprofilepersonalUpdate.setPannumber(profilePersonalInformationEntity.getPannumber());
//			long createdUserId = appProfilePersonalInformationUtils.generateUserId();
		
			String createdUserId = String.valueOf(appProfilePersonalInformationUtils.generateUserId());
			userprofilepersonalUpdate.setUserId(createdUserId);
			
//			String encryptPassword = security.encryptString(profilePersonalInformationEntity.getBloodgroup());
//			userprofilepersonalUpdate.setId(null);
//			userprofilepersonalUpdate.setBloodgroup(encryptPassword);
			
			userprofilepersonalUpdate.setBloodgroup(profilePersonalInformationEntity.getBloodgroup());
			userprofilepersonalUpdate.setHeight(profilePersonalInformationEntity.getHeight());
			userprofilepersonalUpdate.setWeight(profilePersonalInformationEntity.getWeight());
			userprofilepersonalUpdate.setSmoking(profilePersonalInformationEntity.getSmoking());
			userprofilepersonalUpdate.setAlochol(profilePersonalInformationEntity.getAlochol());
			userprofilepersonalUpdate.setCreatedDate(LocalDate.now());
//			userprofilepersonalUpdate.setUpdatedDate(LocalDate.now());
			userprofilepersonalUpdate.setModifiedDate(profilePersonalInformationEntity.getModifiedDate());

//			users = userRepo.save(user);

//			ProfilePersonalInformationEntity createUserProfilePersonalInformation = profilePersonalInformationService.createUserProfilePersonalInformation(userprofilepersonalUpdate);
			ProfilePersonalInformationEntity createUserProfilePersonalInformation = profilePersonalInformationRepo.save(userprofilepersonalUpdate);

			//			int createUserProfilePersonalInformation = profilePersonalInformationService.createUserProfilePersonalInformation(userprofilepersonalUpdate.getAadharnumber(),userprofilepersonalUpdate.getPannumber(),userprofilepersonalUpdate.getUserId()
//					,userprofilepersonalUpdate.getBloodgroup(),userprofilepersonalUpdate.getHeight(),userprofilepersonalUpdate.getWeight(),userprofilepersonalUpdate.getSmoking(),userprofilepersonalUpdate.getAlochol(),userprofilepersonalUpdate.getCreatedDate(),userprofilepersonalUpdate.getUpdatedDate());

//			if(createUserProfilePersonalInformation != null) {
//				return new ResponseEntity<>("200",HttpStatus.CREATED);
//			}
			
//			POST
//			if(createUserProfilePersonalInformation != 0) {
			if(createUserProfilePersonalInformation != null) {

				return new ResponseEntity<>("200",HttpStatus.CREATED);
//				return new ResponseEntity<>(createUserProfilePersonalInformation,HttpStatus.OK);

			}
			

			else {
				return new ResponseEntity<>("400",HttpStatus.OK);
			
			}
			
			
			//GET
//			if(user != null) {
//				return new ResponseEntity<>(user,HttpStatus.OK);
//			}else {
//				return new ResponseEntity<>(user,HttpStatus.OK);
//			}
			
		}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	ProfessionalInformation:-----
	
	@PostMapping("/ProfessionalInformation")
	public ResponseEntity<?> createUserProfileProfessionalInformation(@RequestBody ProfileProfessionalInformationEntity profileProfessionalInformationEntity) {
		ProfileProfessionalInformationEntity userprofileprofessionalUpdate = new ProfileProfessionalInformationEntity();
		ProfileProfessionalInformationEntity userss = null;
	
		try {
			if(profileProfessionalInformationEntity != null) {
				userprofileprofessionalUpdate.setSourceofincome(profileProfessionalInformationEntity.getSourceofincome());
				userprofileprofessionalUpdate.setBussinessname(profileProfessionalInformationEntity.getBussinessname());

				String createdUserId = String.valueOf(appProfileProfessionalInformationUtils.generateUserId());
				userprofileprofessionalUpdate.setUserId(createdUserId);

//				userprofileprofessionalUpdate.setId(null);

				userprofileprofessionalUpdate.setAnnualrevenue(profileProfessionalInformationEntity.getAnnualrevenue());
				userprofileprofessionalUpdate.setCreatedDate(LocalDate.now());
				userprofileprofessionalUpdate.setModifiedDate(profileProfessionalInformationEntity.getModifiedDate());
				
				ProfileProfessionalInformationEntity createUserProfileProfessionalInformation = profileProfessionalInformationRepo.save(userprofileprofessionalUpdate);
//				int createUserProfileProfessionalInformation = profileProfessionalInformationService.createUserProfileProfessionalInformation(
//						userprofileprofessionalUpdate.getSourceofincome(),userprofileprofessionalUpdate.getBussinessname(),userprofileprofessionalUpdate.getUserId()
//						,userprofileprofessionalUpdate.getAnnualrevenue(),userprofileprofessionalUpdate.getCreatedDate(),userprofileprofessionalUpdate.getUpdatedDate());


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
	
	@PostMapping("/FamilyInformation")

	public ResponseEntity<?> createUserProfileProfessionalInformation(@RequestBody ProfileFamilyInformationEntity profileFamilyInformationEntity) {
		ProfileFamilyInformationEntity userprofilefamilyUpdate = new ProfileFamilyInformationEntity();
		ProfileFamilyInformationEntity userss = null;
		
		
		try {
			if(profileFamilyInformationEntity != null) {
				
				String createdUserId = String.valueOf(appProfileFamilyInformationUtils.generateUserId());
				userprofilefamilyUpdate.setUserId(createdUserId);

				
				userprofilefamilyUpdate.setFatherName(profileFamilyInformationEntity.getFatherName());
				userprofilefamilyUpdate.setFather_occupation(profileFamilyInformationEntity.getFather_occupation());

				userprofilefamilyUpdate.setMotherName(profileFamilyInformationEntity.getMotherName());
				userprofilefamilyUpdate.setMother_occupation(profileFamilyInformationEntity.getMother_occupation());
				
				userprofilefamilyUpdate.setSpouseName(profileFamilyInformationEntity.getSpouseName());
				userprofilefamilyUpdate.setSpouse_occupation(profileFamilyInformationEntity.getSpouse_occupation());
				
				userprofilefamilyUpdate.setNomine1_Name(profileFamilyInformationEntity.getNomine1_Name());
				userprofilefamilyUpdate.setNomine1_Occupation(profileFamilyInformationEntity.getNomine1_Occupation());
				
				userprofilefamilyUpdate.setNomine2_Name(profileFamilyInformationEntity.getNomine2_Name());
				userprofilefamilyUpdate.setNomine2_Occupation(profileFamilyInformationEntity.getNomine2_Occupation());
				
				
				userprofilefamilyUpdate.setMarital_status(profileFamilyInformationEntity.getMarital_status());

				
				userprofilefamilyUpdate.setFater_age(profileFamilyInformationEntity.getFater_age());
				userprofilefamilyUpdate.setMother_age(profileFamilyInformationEntity.getMother_age());
				userprofilefamilyUpdate.setSpouse_age(profileFamilyInformationEntity.getSpouse_age());
				userprofilefamilyUpdate.setNomine1_age(profileFamilyInformationEntity.getNomine1_age());
				userprofilefamilyUpdate.setNomine2_age(profileFamilyInformationEntity.getNomine2_age());
				
				
				userprofilefamilyUpdate.setSelectnumberofchildren(profileFamilyInformationEntity.getSelectnumberofchildren());

				
			
//				userprofileprofessionalUpdate.setId(null);


				userprofilefamilyUpdate.setCreatedDate(LocalDate.now());
				userprofilefamilyUpdate.setModifiedDate(profileFamilyInformationEntity.getModifiedDate());
				
				ProfileFamilyInformationEntity createUserProfileFamilyInformation = profileFamilyInformationRepo.save(userprofilefamilyUpdate);
//				int createUserProfileProfessionalInformation = profileProfessionalInformationService.createUserProfileProfessionalInformation(
//						userprofileprofessionalUpdate.getSourceofincome(),userprofileprofessionalUpdate.getBussinessname(),userprofileprofessionalUpdate.getUserId()
//						,userprofileprofessionalUpdate.getAnnualrevenue(),userprofileprofessionalUpdate.getCreatedDate(),userprofileprofessionalUpdate.getUpdatedDate());


				if(createUserProfileFamilyInformation != null) {
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
	

	
	
//	MedicalInformation:-------
	
	@PostMapping("/medicalInformation")
	public ResponseEntity<?> createUserProfileMedicallInformation(
			@RequestBody ProfileMedicalInformationEntity profileMedicalInformationEntity) {
		ProfileMedicalInformationEntity userprofilemedicalUpdate = new ProfileMedicalInformationEntity();
		ProfileMedicalInformationEntity userss = null;
		try {
			if (profileMedicalInformationEntity != null) {
				
				userprofilemedicalUpdate.setUserId(profileMedicalInformationEntity.getUserId());
				userprofilemedicalUpdate.setPast_surgeries(profileMedicalInformationEntity.getPast_surgeries());
				userprofilemedicalUpdate.setBlood_pressure(profileMedicalInformationEntity.getBlood_pressure());
				userprofilemedicalUpdate.setDiabetes(profileMedicalInformationEntity.getDiabetes());

//				userprofileprofessionalUpdate.setId(null);

				userprofilemedicalUpdate.setCreatedDate(LocalDate.now());
				userprofilemedicalUpdate.setModifiedDate(profileMedicalInformationEntity.getModifiedDate());

				ProfileMedicalInformationEntity createUserProfileMedicalInformation = profileMedicalInformationRepo.save(userprofilemedicalUpdate);
//				int createUserProfileProfessionalInformation = profileProfessionalInformationService.createUserProfileProfessionalInformation(
//						userprofileprofessionalUpdate.getSourceofincome(),userprofileprofessionalUpdate.getBussinessname(),userprofileprofessionalUpdate.getUserId()
//						,userprofileprofessionalUpdate.getAnnualrevenue(),userprofileprofessionalUpdate.getCreatedDate(),userprofileprofessionalUpdate.getUpdatedDate());

				if (createUserProfileMedicalInformation != null) {
					return new ResponseEntity<>("200", HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("400", HttpStatus.OK);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
