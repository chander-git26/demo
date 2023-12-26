package com.cos.master.controller;

import java.awt.Image;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.servlet.http.HttpServletRequest;

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
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	// public static final Logger logger = (Logger)
	// LoggerFactory.getLogger(UserController.class.getName());
	public static final Logger logger = Logger.getLogger(UserController.class);
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
		logger.info("Started createUser method"+userEntity);

		UserEntity user = new UserEntity();
		UserEntity users = null;
		try {
			if (userEntity != null) {
				String firstName =userEntity.getFirstname();
				String mobile =userEntity.getMobile();
				String createdUserId = appUtils.generateUserId(firstName,mobile);
				String encryptedUserId = aes.encrypt(createdUserId);
				user.setUserId(encryptedUserId);
				String encryptedPassword = aes.encrypt(userEntity.getPassword());
				logger.info("Middle of create user method ");
				user.setPassword(encryptedPassword);
				user.setFirstname(firstName);
				user.setLastname(userEntity.getLastname());
				String encryptPassword = aes.encrypt(userEntity.getPassword());
				user.setPassword(encryptPassword);
				user.setMobile(mobile);
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
			logger.info("Inside catch block"+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getUserDetails/{userId}")
	public ResponseObject getUserDetails(@PathVariable("userId") String userId) {
		logger.info("Fetch getUserDetails method"+userId);
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
		logger.info("Display getPersonalInfo method"+userId);

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
			logger.info("Inside catch block"+e.getMessage());

			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}

	}

	@GetMapping("/getProfessionalInfo/{userId}")
	public ResponseObject getProffessionalInfo(@PathVariable("userId") Integer userId) {
		logger.info("Display getProfessionalInfo method"+userId);

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
			logger.info("inside catch block"+e.getMessage());

			e.printStackTrace();
			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}

	}

	@GetMapping("/getfamilyInfo/{userId}")
	public ResponseObject getfamilyInfo(@PathVariable("userId") Integer userId) {
		logger.info("Display getfamilyInfo method"+userId);

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
			logger.info("inside catch block"+e.getMessage());

			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}
	}

	@GetMapping("/getMedicalInfo/{userId}")
	public ResponseObject getmedicalInfo(@PathVariable("userId") Integer userId) {
		logger.info("Display getMedicalInfo method"+userId);

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
			logger.info("inside catch block"+e.getMessage());

			return appUtils.prepareResponse("some error occured", "Failed", "500", 0, null);
		}

	}

	@PostMapping("/signin")
	public ResponseEntity<String> signin(@RequestBody UserEntity userInf) throws Exception {
		logger.info("signin  method"+userInf);

		UserEntity user = new UserEntity();

		String email = userInf.getEmail();
		String password = userInf.getPassword();
//	    String encryptPassword = aes.encrypt(password);
		String userpassword = userService.getusername(email);
		String decryptPassword = aes.decrypt(userpassword);
//      	users = userRepo.save(user);
		if (password.equals(decryptPassword)) {
			return new ResponseEntity<>("Successfully signed in", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/savePersonalInformation")
	public ResponseEntity<?> createUserProfilePersonalInformation(
			@RequestBody PersonalInformationEntity profilePersonalInformationEntity) {
		logger.info("savePersonalInformation method"+profilePersonalInformationEntity);

		PersonalInformationEntity userprofilepersonalUpdate = new PersonalInformationEntity();
		PersonalInformationEntity userss = null;
		try {
			if (profilePersonalInformationEntity != null) {
				userprofilepersonalUpdate.setAddress(profilePersonalInformationEntity.getAddress());
				userprofilepersonalUpdate.setGender(profilePersonalInformationEntity.getGender());
				Date startDateString = profilePersonalInformationEntity.getDateOfBirth();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				//String newDate = LocalDate.parse(startDateString, formatter2).format(formatter2);
				userprofilepersonalUpdate.setDateOfBirth(startDateString);
				userprofilepersonalUpdate.setState(profilePersonalInformationEntity.getState());
				userprofilepersonalUpdate.setPincode(profilePersonalInformationEntity.getPincode());
				//userprofilepersonalUpdate.setCountry(profilePersonalInformationEntity.getCountry());
				userprofilepersonalUpdate.setMaritalStatus(profilePersonalInformationEntity.getMaritalStatus());
				userprofilepersonalUpdate.setHeight(profilePersonalInformationEntity.getHeight());
				userprofilepersonalUpdate.setWeight(profilePersonalInformationEntity.getWeight());
				userprofilepersonalUpdate.setSmoking(profilePersonalInformationEntity.getSmoking());
				userprofilepersonalUpdate.setAlochol(profilePersonalInformationEntity.getAlochol());
				PersonalInformationEntity createUserProfilePersonalInformation = personalInfoRepo
						.save(userprofilepersonalUpdate);
				if (createUserProfilePersonalInformation.getId() != 0) {
					return new ResponseEntity<>("200", HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("400", HttpStatus.OK);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("inside catch "+e.getMessage());

			return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/saveProfessionalInformation")
	public ResponseObject createUserProfileProfessionalInformation(
			@RequestBody ProfessionalInformationEntity profileProfessionalInformationEntity) {
		logger.info("saveProfessionalInformation method"+profileProfessionalInformationEntity);

		ProfessionalInformationEntity userprofileprofessionalUpdate = new ProfessionalInformationEntity();
		ProfessionalInformationEntity users = null;

		try {
			if (profileProfessionalInformationEntity.getSourceOfIncome().equals("business")) {
				if (profileProfessionalInformationEntity.getId() != 0
						&& profileProfessionalInformationEntity.getBusinessName() != null
						&& profileProfessionalInformationEntity.getBusinessAnnualRevenue() != 0
						&& profileProfessionalInformationEntity.getGstNumber() != null
						&& profileProfessionalInformationEntity.getBusinessType() != null
						&& profileProfessionalInformationEntity.getInvestAmount() != 0) {
					userprofileprofessionalUpdate.setId(profileProfessionalInformationEntity.getId());
					userprofileprofessionalUpdate
							.setSourceOfIncome(profileProfessionalInformationEntity.getSourceOfIncome());
					userprofileprofessionalUpdate
							.setBusinessName(profileProfessionalInformationEntity.getBusinessName());
					userprofileprofessionalUpdate
							.setBusinessAnnualRevenue(profileProfessionalInformationEntity.getBusinessAnnualRevenue());
					userprofileprofessionalUpdate.setGstNumber(profileProfessionalInformationEntity.getGstNumber());
					userprofileprofessionalUpdate
							.setBusinessType(profileProfessionalInformationEntity.getBusinessType());
					userprofileprofessionalUpdate
							.setInvestAmount(profileProfessionalInformationEntity.getInvestAmount());
					ProfessionalInformationEntity userEntity = professionalInfoRepo.save(userprofileprofessionalUpdate);
					if (userEntity.getId() != 0) {
						return appUtils.prepareResponse("Data Saved Successfully", "Success", "200", 1, null);
					} else {
						return appUtils.prepareResponse("Unable to save data", "Failed", "400", 0, null);
					}
				} else {
					return appUtils.prepareResponse("Mandatory fileds are missing", "failed", "500", 0, null);
				}
			} else if (profileProfessionalInformationEntity.getSourceOfIncome().equals("salaried")) {
				if (profileProfessionalInformationEntity.getId() != 0
						&& profileProfessionalInformationEntity.getCompanyName() != null
						&& profileProfessionalInformationEntity.getAnnualIncome() != null) {
					userprofileprofessionalUpdate.setId(profileProfessionalInformationEntity.getId());
					userprofileprofessionalUpdate
							.setSourceOfIncome(profileProfessionalInformationEntity.getSourceOfIncome());
					userprofileprofessionalUpdate.setCompanyName(profileProfessionalInformationEntity.getCompanyName());
					userprofileprofessionalUpdate
							.setAnnualIncome(profileProfessionalInformationEntity.getAnnualIncome());
					userprofileprofessionalUpdate
							.setInvestAmount(profileProfessionalInformationEntity.getInvestAmount());
					ProfessionalInformationEntity userEntity = professionalInfoRepo.save(userprofileprofessionalUpdate);
					if (userEntity.getId() != 0) {
						return appUtils.prepareResponse("Data Saved Successfully", "Success", "200", 1, null);
					} else {
						return appUtils.prepareResponse("Unable to save data", "Failed", "400", 0, null);
					}
				} else {
					return appUtils.prepareResponse("Mandatory fileds are missing", "failed", "500", 0, null);
				}
			} else {
				return appUtils.prepareResponse("Invalid source of Income", "failed", "400", 0, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("inside catch block"+e.getMessage());

			return appUtils.prepareResponse("some error Occured", "failed", "500", 0, null);
		}

	}

	// define a location
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
	@PostMapping(value = "/saveFamilyInformation", produces = MediaType.APPLICATION_JSON_VALUE)
//	    @PostMapping(value = "/familyInformation", consumes = "multipart/form-data")

//	public ResponseObject createUserProfileFamilyInformation(@RequestBody FamilyInformationEntity profileFamilyUpdate) {
	public ResponseObject createUserProfileFamilyInformation(@RequestParam String id, String fatherName,
			Integer fatherAge, String fatherOccupation,
			@RequestParam("fatherUploadMedicalHistory") List<MultipartFile> fatherUploadMedicalHistory,
			String motherName, Integer motherAge, String motherOccupation,
			@RequestParam("motherUploadMedicalHistory") List<MultipartFile> motherUploadMedicalHistory,
			String spouseName, Integer spouseAge, String spouseOccupation,
			@RequestParam("spouseUploadMedicalHistory") List<MultipartFile> spouseUploadMedicalHistory,
			@RequestParam("uploadOtherNomineeRelation") List<MultipartFile> uploadOtherNomineeRelation,
			String nominee1Name, String nominee2Name, String otherNomineeName, Integer otherNomineeAge,
			String otherNomineeRelation, String maritalStatus, Integer selectNumberOfChildren

	) {
		FamilyInformationEntity userProfileFamilyUpdate = new FamilyInformationEntity();
		logger.info("saveFamilyInformation  method"+userProfileFamilyUpdate);
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
//				userProfileFamilyUpdate.setNominee1Name(nominee1Name);
//				userProfileFamilyUpdate.setNominee2Name(nominee2Name);
				userProfileFamilyUpdate.setOtherNomineeName(otherNomineeName);
				userProfileFamilyUpdate.setOtherNomineeAge(otherNomineeAge);

				List<String> filenames = new ArrayList<>();
				for (MultipartFile file : fatherUploadMedicalHistory) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userProfileFamilyUpdate.setFatherUploadMedicalHistory(filenames.get(i));

				}
				for (MultipartFile file : motherUploadMedicalHistory) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userProfileFamilyUpdate.setMotherUploadMedicalHistory(filenames.get(i));

				}
				for (MultipartFile file : spouseUploadMedicalHistory) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userProfileFamilyUpdate.setSpouseUploadMedicalHistory(filenames.get(i));
				}

				for (MultipartFile file : uploadOtherNomineeRelation) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userProfileFamilyUpdate.setOtherNomineeUploadMedicalHistory(filenames.get(i));

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
			e.printStackTrace();
			logger.info("inside catch block"+e.getMessage());

			return appUtils.prepareResponse("internal server error", "Failer", "500", 1,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUtils.prepareResponse("internal server error", "Failer", "500", 1, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/saveMedicalInformation", consumes = "multipart/form-data")
	public ResponseObject saveMedicalInformation(@RequestParam String id, @RequestParam String pastSurgeries,
			@RequestParam("uploadBpReport") List<MultipartFile> uploadBpReport,
			@RequestParam("uploadDiabetesReport") List<MultipartFile> uploadDiabetesReport,
			@RequestParam("uploadHeartStrokeReport") List<MultipartFile> uploadHeartStrokeReport,
			@RequestParam("uploadOtherReport") List<MultipartFile> uploadOtherReport,
			@RequestParam String currentTreatments, @RequestParam String covidStatus) throws IOException {
		

		MedicalInformationEntity userprofilemedicalUpdate = new MedicalInformationEntity();
		logger.info("saveMedicalInformation  method"+userprofilemedicalUpdate);
		try {
			if (id != null) {
				userprofilemedicalUpdate.setId(Integer.parseInt(id));
				userprofilemedicalUpdate.setPastSurgeries(pastSurgeries);

				userprofilemedicalUpdate.setCurrentTreatments(currentTreatments);
				userprofilemedicalUpdate.setCovidStatus(covidStatus);
				List<String> filenames = new ArrayList<>();
				for (MultipartFile file : uploadBpReport) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userprofilemedicalUpdate.setUploadBloodPressure(filenames.get(i));

				}
				for (MultipartFile file : uploadDiabetesReport) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {

					userprofilemedicalUpdate.setUploadDiabetes(filenames.get(i));

				}
				for (MultipartFile file : uploadHeartStrokeReport) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userprofilemedicalUpdate.setUploadHeartStroke(filenames.get(i));

				}
				for (MultipartFile file : uploadOtherReport) {
					String filename = StringUtils.cleanPath(file.getOriginalFilename());
					Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(filename);
				}
				for (int i = 0; i < filenames.size(); i++) {
					userprofilemedicalUpdate.setUpload_OtherReport(filenames.get(i));

				}
				MedicalInformationEntity saveMedicalInformation = medicalInfoRepo.save(userprofilemedicalUpdate);
				if (saveMedicalInformation.getId() != 0) {
					return appUtils.prepareResponse("Data saved successfully", "Success", "200", 1,
							saveMedicalInformation);
				} else {
					return appUtils.prepareResponse("Failed to save Data", "Failed", "400", 1, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("inside catch block"+e.getMessage());

			return appUtils.prepareResponse("internal server error", "Failer", "500", 1,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUtils.prepareResponse("internal server error", "Failer", "500", 1, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// -----------------------------------------

	@GetMapping("download/{userId}")
	public ResponseEntity<?> getmedicalInfo(@PathVariable("userId") String userId) throws IOException {
		logger.info("download method"+userId);


		try {
			if (!userId.equals("0")) {
				UserEntity userEntity = new UserEntity();
				String user_id = String.valueOf(userId);
				byte[] medicalInfo = userService.getMedicallInfo(user_id);
//				if (medicalInfo.isPresent()) {
//					MedicalInfoResponse response = medicalInfo.get();
//					byte[] data = response.getUploadMedicalHistory();

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_PDF);

				ResponseEntity<Resource> responseEntity = null;
				return new ResponseEntity<>(medicalInfo, headers, HttpStatus.OK);
//				}
			} else {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("inside catch block"+e.getMessage());

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ---------------------------------------------------------

	@GetMapping("/getUserId/{email}")
	public ResponseObject getUserId(@PathVariable("email") String email) {
		logger.info("getUserId method"+email);
 
		UserEntity userEntity = new UserEntity();
		String user = userService.getUserEmail(email);
		logger.info("user mail"+user);

		if (user != null) {
			return appUtils.prepareResponse("Data fetch successfully", "Success", "200", 1, user);
		} else {
			return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, user);
		}
	}

	@PostMapping("/upload")
	public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles)
			throws IOException {
		logger.info("upload method"+multipartFiles);

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
		logger.info("inside updatePassword method"+json);
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
		logger.info("inside verifyMobileNumber method "+mobile);
		UserEntity userEntity = new UserEntity();
		String mobileNumber = userService.verifyMobileNumber(mobile);
		if (mobile != null) {
			return appUtils.prepareResponse("mobile verify successfully", "successfull", "200", 1, null);

		} else { 
			return appUtils.prepareResponse("Failed to fetch data", "failed", "400", 1, null);
		}
	}
	


	@PostMapping("/uploadImage")
	public ResponseObject uploadImage(HttpServletRequest request, @RequestParam("userId") int UserId,@RequestParam("profile") MultipartFile file) throws IOException, SerialException, SQLException {
		logger.info("upload image method"+file);

		try {
			byte[] bytes = file.getBytes();
			Blob blob = new SerialBlob(bytes);

			UserEntity userEntity = new UserEntity();
			userEntity.setProfile(blob);
			int rows = userService.uploadProfile(UserId, userEntity.getProfile());
			if (rows != 0) {
				return appUtils.prepareResponse("profile updated successfully", "success", "200", 1, null);
			} else {
				return appUtils.prepareResponse("failed to update profile", "failed", "400", 0, null);
			}
		} catch (Exception e) {
			logger.info("inside catch block"+e.getMessage());

			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
		}
	}
	
	
	
//	@GetMapping("/display")
//	public ResponseEntity<byte[]> display(@RequestParam("id") int id)  {
//		logger.info("image display method"+id);
//		try {
////    	UserService userService = null;
//		UserEntity profile = userService.viewById(id);
//		byte[] imageBytes = null;
//		imageBytes = profile.getProfile().getBytes(1, (int) profile.getProfile().length());
//		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			e.getMessage();
//		}
//		return null;
//		}
	
	
	
	@GetMapping("display/{id}")
    public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Integer id) throws SQLException {

        Optional<UserEntity> userImage = userRepo.findById(id);
        byte[] imageBytes = null;
        if (userImage.isPresent()) {

            imageBytes = userImage.get().getProfile().getBytes(1,
                    (int) userImage.get().getProfile().length());
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
	
	}

