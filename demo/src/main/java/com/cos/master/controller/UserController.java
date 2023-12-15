package com.cos.master.controller;

import java.awt.Image;
import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.master.entities.FamilyInformationEntity;
import com.cos.master.entities.FamilyResponse;
import com.cos.master.entities.MedicalInfoResponse;
import com.cos.master.entities.MedicalInformationEntity;
import com.cos.master.entities.PersonalInfoResponse;
import com.cos.master.entities.PersonalInformationEntity;
import com.cos.master.entities.ProfessionalInformationEntity;
import com.cos.master.entities.ProfessionalResponse;
import com.cos.master.entities.ResponseObject;
import com.cos.master.entities.UserEntity;
import com.cos.master.repository.FamilyInformationRepository;
import com.cos.master.repository.MedicalInformationRepository;
import com.cos.master.repository.PersonalInformationRepository;
import com.cos.master.repository.ProfessionalInformationRepository;
import com.cos.master.repository.UserRepository;
import com.cos.master.security.AES;
import com.cos.master.security.Security;
import com.cos.master.service.UserService;
import com.cos.master.utils.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	@Autowired
	UserService userService;

	Security security;

	@Autowired
	AppUtils appUtils;

	@Autowired
	UserRepository userRepo;

	@Autowired
	FamilyInformationRepository familyInfoRepo;

	@Autowired
	MedicalInformationRepository medicalInfoRepo;

	@Autowired
	PersonalInformationRepository personalInfoRepo;

	@Autowired
	ProfessionalInformationRepository professionalInfoRepo;

	@Autowired
	AES aes;

	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) {
		UserEntity user = new UserEntity();
		UserEntity users = null;
		try {
			if (userEntity != null) {
				user.setFirstname(userEntity.getFirstname());
				user.setLastname(userEntity.getLastname());
				String createdUserId = String.valueOf(appUtils.generateUserId());
				user.setUserId(createdUserId);
				String encryptPassword = aes.encrypt(userEntity.getPassword());

				user.setPassword(encryptPassword);
				user.setMobile(userEntity.getMobile());
				user.setEmail(userEntity.getEmail());

				user.setCreatedDate(LocalDate.now());
				user.setModifieddDate(LocalDate.now());
				users = userRepo.save(user);
//				int createUser = userService.createUser(user.getFirstname(),user.getLastname(),user.getUserId(),user.getMobile(),user.getPassword(),user.getEmail(),user.getCreatedDate(),user.getModifieddDate());
				if (users != null) {
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

	@GetMapping("/getUserDetails/{userId}")
	public ResponseObject getUserDetails(@PathVariable("userId") String userId) {
		UserEntity userEntity = new UserEntity();
		UserEntity user = userService.getUserInfo(userId);
		if (user != null) {
			return appUtils.prepareResponse("Data fetch successfully", "successfull", "200", 1, user);
		} else {
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
					return appUtils.prepareResponse("Data fetch successfully", "Success", "200", 1, personalInfo);
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
					return appUtils.prepareResponse("Data fetch successfully", "Success", "200", 1, proffesionalInfo);
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
					return appUtils.prepareResponse("Data fetch successfully", "Success", "200", 1, user);
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
					return appUtils.prepareResponse("Data fetch successfully", "Success", "200", 1, medicalInfo);
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
	public ResponseEntity<String> signin(@RequestBody UserEntity userInf) throws Exception {
		UserEntity user = new UserEntity();

		String email = userInf.getEmail();
		String password = userInf.getPassword();
//	    String encryptPassword = aes.encrypt(password);
		String userpassword = userService.getusername(email);
		String decryptPassword = aes.decrypt(userpassword);
//      	users = userRepo.save(user);
		if(password.equals(decryptPassword)) {
			return new ResponseEntity<>("Successfully signed in", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/personalInformation")
	public ResponseEntity<?> createUserProfilePersonalInformation(
			@RequestBody PersonalInformationEntity profilePersonalInformationEntity) {
		PersonalInformationEntity userprofilepersonalUpdate = new PersonalInformationEntity();
		PersonalInformationEntity userss = null;
		try {
			if (profilePersonalInformationEntity != null) {
				userprofilepersonalUpdate.setAddress(profilePersonalInformationEntity.getAddress());
				userprofilepersonalUpdate.setGender(profilePersonalInformationEntity.getGender());
				userprofilepersonalUpdate.setDateofbirth(profilePersonalInformationEntity.getDateofbirth());
				userprofilepersonalUpdate.setState(profilePersonalInformationEntity.getState());
				userprofilepersonalUpdate.setCountry(profilePersonalInformationEntity.getCountry());
				userprofilepersonalUpdate.setMaritalStatus(profilePersonalInformationEntity.getMaritalStatus());
				userprofilepersonalUpdate.setBloodGroup(profilePersonalInformationEntity.getBloodGroup());
				userprofilepersonalUpdate.setHeight(profilePersonalInformationEntity.getHeight());
				userprofilepersonalUpdate.setWeight(profilePersonalInformationEntity.getWeight());
				userprofilepersonalUpdate.setSmoking(profilePersonalInformationEntity.getSmoking());
				userprofilepersonalUpdate.setAlochol(profilePersonalInformationEntity.getAlochol());
				PersonalInformationEntity createUserProfilePersonalInformation = personalInfoRepo.save(userprofilepersonalUpdate);
				if (createUserProfilePersonalInformation.getId() != 0) {
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

	@PostMapping("/professionalInformation")
	public ResponseEntity<?> createUserProfileProfessionalInformation(
			@RequestBody ProfessionalInformationEntity profileProfessionalInformationEntity) {
		ProfessionalInformationEntity userprofileprofessionalUpdate = new ProfessionalInformationEntity();
		ProfessionalInformationEntity userss = null;

		try {
			if (profileProfessionalInformationEntity != null) {

				userprofileprofessionalUpdate
						.setSourceOfIncome(profileProfessionalInformationEntity.getSourceOfIncome());

				userprofileprofessionalUpdate.setCompanyName(profileProfessionalInformationEntity.getCompanyName());
				userprofileprofessionalUpdate.setBusinessName(profileProfessionalInformationEntity.getBusinessName());
				userprofileprofessionalUpdate.setId(profileProfessionalInformationEntity.getId());
				userprofileprofessionalUpdate.setAnnualIncome(profileProfessionalInformationEntity.getAnnualIncome());
				userprofileprofessionalUpdate
						.setBusinessAnnualRevenue(profileProfessionalInformationEntity.getBusinessAnnualRevenue());

				ProfessionalInformationEntity createUserProfileProfessionalInformation = professionalInfoRepo
						.save(userprofileprofessionalUpdate);

				if (createUserProfileProfessionalInformation.getId() != 0) {
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

	// define a location
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

	@PostMapping(value = "/familyInformation", produces = MediaType.APPLICATION_JSON_VALUE)
//	    @PostMapping(value = "/familyInformation", consumes = "multipart/form-data")

//	public ResponseObject createUserProfileFamilyInformation(@RequestBody FamilyInformationEntity profileFamilyUpdate) {
	public ResponseObject createUserProfileFamilyInformation(@RequestParam String id,String fatherName,Integer fatherAge,String fatherOccupation
			
			,@RequestParam("fatherUploadMedicalHistory")List<MultipartFile> fatherUploadMedicalHistory
			,String motherName,Integer motherAge,String motherOccupation
			,@RequestParam("motherUploadMedicalHistory")List<MultipartFile> motherUploadMedicalHistory
			,String spouseName,Integer spouseAge,String spouseOccupation
			,@RequestParam("spouseUploadMedicalHistory")List<MultipartFile> spouseUploadMedicalHistory
			,String nominee1Name,String nominee2Name,String otherNomineeName,Integer otherNomineeAge,String otherNomineeRelation
			,String maritalStatus,Integer selectNumberOfChildren

			) {
	
	FamilyInformationEntity userProfileFamilyUpdate = new FamilyInformationEntity();
		FamilyInformationEntity userss = null;
		try {
//			if (profileFamilyUpdate != null) {
			if (id != null) {
				userProfileFamilyUpdate.setId(Integer.parseInt(id));
				userProfileFamilyUpdate.setFatherName(fatherName);			
//				userProfileFamilyUpdate.setFatherAge(Integer.parseInt(fatherAge));
				userProfileFamilyUpdate.setFatherAge(fatherAge);
				userProfileFamilyUpdate.setFatherOccupation(fatherOccupation);
//				userProfileFamilyUpdate.setFatherUploadMedicalHistory(fatherUploadMedicalHistory);
				userProfileFamilyUpdate.setMotherName(motherName);
				userProfileFamilyUpdate.setMotherAge(motherAge);
				userProfileFamilyUpdate.setMotherOccupation(motherOccupation);
//				userProfileFamilyUpdate.setMotherUploadMedicalHistory(motherUploadMedicalHistory);
				userProfileFamilyUpdate.setSpouseName(spouseName);
				userProfileFamilyUpdate.setSpouseAge(spouseAge);
				userProfileFamilyUpdate.setSpouseOccupation(spouseOccupation);				
//				userProfileFamilyUpdate.setSpouseUploadMedicalHistory(spouseUploadMedicalHistory);	
				userProfileFamilyUpdate.setNominee1Name(nominee1Name);
				userProfileFamilyUpdate.setNominee2Name(nominee2Name);
				userProfileFamilyUpdate.setOtherNomineeName(otherNomineeName);
				userProfileFamilyUpdate.setOtherNomineeAge(otherNomineeAge);
				userProfileFamilyUpdate.setOtherNomineeRelation(otherNomineeRelation);
				userProfileFamilyUpdate.setMaritalStatus(maritalStatus);
				userProfileFamilyUpdate.setSelectNumberOfChildren(selectNumberOfChildren);
				List<String> filenames = new ArrayList<>();	 
				 for(MultipartFile file : fatherUploadMedicalHistory) {
			            String filename = StringUtils.cleanPath(file.getOriginalFilename());
			            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
			            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
			            filenames.add(filename);
			        } 
				 for (int i = 0; i < filenames.size(); i++) {
//					 userProfileFamilyUpdate.setFatherUploadMedicalHistory(filenames.get(i));

				}	 
				 for(MultipartFile file : motherUploadMedicalHistory) {
			            String filename = StringUtils.cleanPath(file.getOriginalFilename());
			            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
			            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
			            filenames.add(filename);
			        }				 
				 for (int i = 0; i < filenames.size(); i++) {
//					 userProfileFamilyUpdate.setMotherUploadMedicalHistory(filenames.get(i));

				}				 
				 for(MultipartFile file : spouseUploadMedicalHistory) {
			            String filename = StringUtils.cleanPath(file.getOriginalFilename());
			            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
			            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
			            filenames.add(filename);
			        }	 
				 for (int i = 0; i < filenames.size(); i++) {
//					 userProfileFamilyUpdate.setSpouseUploadMedicalHistory(filenames.get(i));
				}	
				FamilyInformationEntity createUserProfileFamilyInformation = familyInfoRepo
						.save(userProfileFamilyUpdate);
				if (createUserProfileFamilyInformation.getId() != 0) {
					return appUtils.prepareResponse("Data saved successfully", "Success", "200", 1,
							createUserProfileFamilyInformation);
				} else {
					return appUtils.prepareResponse("Failed to save Data", "Failed", "400", 1, null);
				}
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("internal server error", "Failer", "500", 1,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUtils.prepareResponse("internal server error", "Failer", "500", 1, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/medicalInformation", consumes = "multipart/form-data")
	public ResponseObject createUserProfileMedicallInformation(@RequestParam String id,
			@RequestParam String pastSurgeries, @RequestParam String bloodPressure, @RequestParam String diabetes,
			@RequestParam("uploadMedicalHistory") List<MultipartFile> uploadMedicalHistory) throws IOException {

		MedicalInformationEntity userprofilemedicalUpdate = new MedicalInformationEntity();
		try {
			if (id != null) {
				userprofilemedicalUpdate.setId(Integer.parseInt(id));	
				userprofilemedicalUpdate.setPastSurgeries(pastSurgeries);
				userprofilemedicalUpdate.setBloodPressure(Integer.parseInt(diabetes));
				userprofilemedicalUpdate.setDiabetes(diabetes);
				userprofilemedicalUpdate.setPastSurgeries(pastSurgeries);
				userprofilemedicalUpdate.setBloodPressure(Integer.parseInt(bloodPressure));
				userprofilemedicalUpdate.setDiabetes(diabetes);
				List<String> filenames = new ArrayList<>();

				for (MultipartFile file : uploadMedicalHistory) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userprofilemedicalUpdate.setUploadMedicalHistory(filenames.get(i));
				}
				MedicalInformationEntity createUserProfileMedicalInformation = medicalInfoRepo.save(userprofilemedicalUpdate);
				return appUtils.prepareResponse("Data Saved successfully", "Success", "200", 1, filenames);
				} 
			else {
				return appUtils.prepareResponse("id cannot be empty", "Failed", "400", 0, null);
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("Some error occurred", "Failed", "500", 0, null);

		}

	}

	// -----------------------------------------

	@GetMapping("download/{userId}")
	public ResponseObject getmedicalInfo(@PathVariable("userId") String userId) throws IOException {

		try {
			if (!userId.equals("0")) {
				UserEntity userEntity = new UserEntity();
				String user_id = String.valueOf(userId);
				MedicalInfoResponse medicalInfo = userService.getMedicalInfo(user_id);

				Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(userId);
				if (!Files.exists(filePath)) {
					throw new FileNotFoundException(userId + " was not found on the server");
				}
				Resource resource = new UrlResource(filePath.toUri());
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.add("File-Name", userId);
				httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

				ResponseEntity<Resource> responseEntity = ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath))).headers(httpHeaders).body(resource);
				return new ResponseObject();
			} else {
				return appUtils.prepareResponse("user id cannot be 0", "failed", "500", 0, null);
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}
	}

	// ---------------------------------------------------------

	@GetMapping("/getUserId/{email}")
	public ResponseObject getUserId(@PathVariable("email") String email) {
		UserEntity userEntity = new UserEntity();
		String user = userService.getUserEmail(email);
		if (user != null) {
			return appUtils.prepareResponse("Data fetch successfully", "Success", "200", 1, user);
		} else {
			return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, user);
		}
	}
	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles)
			throws IOException {
		List<String> filenames = new ArrayList<>();
		for (MultipartFile file : multipartFiles) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
			copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
			filenames.add(filename);
		}
		return ResponseEntity.ok().body(filenames);
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody String json) throws Exception {
		UserEntity user = new UserEntity();
		Map<String, Object> data = null;
		ObjectMapper mapper = new ObjectMapper();
		data = mapper.readValue(json, Map.class);
		String mobilenumber = (String) data.get("mobile");
		String password = (String) data.get("password");
		String usermobile = userService.getUserMobile(mobilenumber);

		String encryptPassword = aes.encrypt(password);
		int updatepassword = userRepo.updatePassword(encryptPassword, mobilenumber);
		if (updatepassword != 0) {
			return new ResponseEntity<>("200", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("400", HttpStatus.OK);
		}

	}
	@GetMapping("/verifyMobilenumber/{mobile}")
	public ResponseObject verifyMobileNumber(@PathVariable("mobile") String mobile) {
		UserEntity userEntity = new UserEntity();
		String mobileNumber = userService.verifyMobileNumber(mobile);
		if (mobile != null) {
			return appUtils.prepareResponse("mobile verify successfully", "successfull", "200", 1, null);

		} else {
			return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, null);
		}
	}
	
}
