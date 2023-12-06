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
import com.cos.master.security.AES;
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
	AES aes;
	
	
	
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
				String encryptPassword = aes.encrypt(userEntity.getPassword());
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
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody UserEntity userInf) throws Exception {
		UserEntity user = new UserEntity();
		
		String email = userInf.getEmail();
	    String password = userInf.getPassword();
//	    String encryptPassword = aes.encrypt(password);
		UserEntity userInfo = userService.getusername(email);
		String decryptPassword = aes.decrypt(userInfo.getPassword());
//      	users = userRepo.save(user);
		if(password.equals(decryptPassword)) {
			return new ResponseEntity<>("200",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("400",HttpStatus.OK);
		}
		
	
	}
}




	
	
	

	
