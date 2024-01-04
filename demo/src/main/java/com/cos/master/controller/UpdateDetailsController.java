package com.cos.master.controller;
import java.io.IOException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.master.entities.FamilyInformationEntity;
import com.cos.master.entities.MedicalInformationEntity;
import com.cos.master.entities.PersonalInformationEntity;
import com.cos.master.entities.ProfessionalInformationEntity;
import com.cos.master.entities.ResponseObject;
import com.cos.master.repository.FamilyInformationRepository;
import com.cos.master.repository.MedicalInformationRepository;
import com.cos.master.repository.PersonalInformationRepository;
import com.cos.master.repository.ProfessionalInformationRepository;
import com.cos.master.service.UserService;
import com.cos.master.utils.AppUtils;

//
//import static java.nio.file.Files.copy;
//import static java.nio.file.Paths.get;
//import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.cos.master.entities.MedicalInformationEntity;
//import com.cos.master.entities.ProfessionalInformationEntity;
//import com.cos.master.entities.ResponseObject;
//import com.cos.master.repository.MedicalInformationRepository;
//import com.cos.master.service.UserService;
//import com.cos.master.utils.AppUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//
@RestController
@RequestMapping(value = "/update")
public class UpdateDetailsController {
public static final Logger logger = Logger.getLogger(UpdateDetailsController.class);
	private static final String DIRECTORY = null;

	@Autowired
	UserService userService;

	@Autowired
	AppUtils appUtils;
	
	@Autowired
	MedicalInformationRepository medicalInfoRepo;
	
	@Autowired
	ProfessionalInformationRepository professionalInfoRepo;
	
	@Autowired
	FamilyInformationRepository familyInfoRepo;
	
	@Autowired
	PersonalInformationRepository personalInfoRepo;

//
//	@PostMapping("/updateFatherName")
//	public ResponseObject updateFatherName(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String father_name = (String) map.get("father_name");
//			int updateDetails = userService.updateFatherName(father_name, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("FatherName updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("FatherName cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updateAge")
//	public ResponseObject updateAge(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			int father_age = (int) map.get("father_age");
//			int updateDetails = userService.updateFatherAge(father_age, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("Age updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("Age cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/upadteFatherOccupation")
//	public ResponseObject upadteFatherOccupation(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String father_occupation = (String) map.get("father_occupation");
//			int updateDetails = userService.upadteFatherOccupation(father_occupation, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("FatherOccupation updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("FatherOccupation cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updatefatherUploadMedicalHistory")
//	public ResponseObject updatefatherUploadMedicalHistory(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			byte[] father_upload_medical_history = (byte[]) map.get("father_upload_medical_history");
//			int updateDetails = userService.updateFatherUploadMedicalHistory(father_upload_medical_history, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("UploadMedicalHistory updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("UploadMedicalHistory cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/upadteMotherName")
//	public ResponseObject upadteMotherName(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String mother_Name = (String) map.get("mother_Name");
//			int updateDetails = userService.upadteMotherName(mother_Name, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("MotherName updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("MotherName cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updateMotherAge")
//	public ResponseObject updateMotherAge(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			int mother_Name = (int) map.get("mother_Name");
//			int updateDetails = userService.updateMotherAge(mother_Name, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("MotherAge updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("MotherAge cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/upadteMotherOccupation")
//	public ResponseObject upadteMotherOccupation(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String mother_occupation = (String) map.get("mother_occupation");
//			int updateDetails = userService.upadteMotherOccupation(mother_occupation, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("MotherOccupation updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("MotherOccupation cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updatemotherUploadMedicalHistory")
//	public ResponseObject updatemotherUploadMedicalHistory(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			byte[] mother_upload_medical_history = (byte[]) map.get("mother_upload_medical_history");
//			int updateDetails = userService.updateMotherUploadMedicalHistory(mother_upload_medical_history, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("motherUploadMedicalHistory updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("motherUploadMedicalHistory cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/upadteSpouseName")
//	public ResponseObject upadteSpouseName(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String spouse_name = (String) map.get("spouse_name");
//			int updateDetails = userService.upadteSpouseName(spouse_name, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("SpouseName updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("SpouseName cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updateSpouseAge")
//	public ResponseObject updateSpouseAge(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			int spouse_age = (int) map.get("spouse_age");
//			int updateDetails = userService.updateSpouseAge(spouse_age, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("SpouseAge updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("SpouseAge cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updateSpouseOccupation")
//	public ResponseObject updateSpouseOccupation(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String spouse_occupation = (String) map.get("spouse_occupation");
//			int updateDetails = userService.updateSpouseOccupation(spouse_occupation, id);
//			if (updateDetails != 0) {
//
//				return appUtils.prepareResponse("SpouseOccupation updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("SpouseOccupation cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updatespouseUploadMedicalHistory")
//	public ResponseObject updatespouseUploadMedicalHistory(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			byte[] spouse_upload_medical_history = (byte[]) map.get("spouse_upload_medical_history");
//			int updateDetails = userService.updateSpouseUploadMedicalHistory(spouse_upload_medical_history, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("Spouse Medical History updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
////		@PostMapping("/updateNomineeName")
////		public ResponseObject updateNomineeName(@RequestBody String json) {
////			ObjectMapper mapper = new ObjectMapper();
////			try {
////				Map<String, Object> map = null;
////				map = mapper.readValue(json, Map.class);
////				int id = (int) map.get("id");
////				String nominee_name = (String) map.get("nominee_name");
////				int updateDetails = userService.updateNomineeName(nominee_name, id);
////				if(updateDetails != 0) {
////					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
////				}
////				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
////			} catch (Exception e) {
////				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
////			}
////		
////		
////
////		@PostMapping("/updateNomineeAge")
////		public ResponseObject updateNominee2Name(@RequestBody String json) {
////			ObjectMapper mapper = new ObjectMapper();
////			try {
////				Map<String, Object> map = null;
////				map = mapper.readValue(json, Map.class);
////				int id = (int) map.get("id");
////				String nominee2_name = (String) map.get("nominee2_name");
////				int updateDetails = userService.updateNominee2Name(nominee2_name, id);
////				if(updateDetails != 0) {
////					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
////				}
////				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
////			} catch (Exception e) {
////				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
////			}
////		
//
////}
//	@PostMapping("/updateOtherNomineeName")
//	public ResponseObject updateOtherNomineeName(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String other_nominee_name = (String) map.get("other_nominee_name");
//			int updateDetails = userService.updateOtherNomineeName(other_nominee_name, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("OtherNomineeName updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("OtherNomineeName cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//	}
//
//	@PostMapping("/updateOtherNomineeAge")
//	public ResponseObject updateOtherNomineeAge(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			int other_nominee_age = (int) map.get("other_nominee_age");
//			int updateDetails = userService.updateOtherNomineeAge(other_nominee_age, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("OtherNomineeAge updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("OtherNomineeAge cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/updateotherNomineeOccupation")
//	public ResponseObject updateotherNomineeOccupation(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String other_nominee_occupation = (String) map.get("other_nominee_occupation");
//			int updateDetails = userService.updateOtherNomineeOccupation(other_nominee_occupation, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("otherNominee Occupation updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("otherNominee Occupation cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/updateotherNomineeUploadMedicalHistory")
//	public ResponseObject updateotherNomineeUploadMedicalHistory(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			byte[] other_nominee_upload_medical_history = (byte[]) map.get("other_nominee_upload_medical_history");
//			int updateDetails = userService.updateOtherNomineeMedicalHistory(other_nominee_upload_medical_history,id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("otherNominee MedicalHistory updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("otherNominee MedicalHistory cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/updatePastSurgeries")
//	public ResponseObject updatePastSurgeries(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String past_surgeries = (String) map.get("past_surgeries");
//			if (past_surgeries != null && id != 0 && !past_surgeries.isEmpty()) {
//				int updateDetails = userService.updatePastSurgeries(past_surgeries, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("past surgeries updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("failed to update data", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "400", 0, null);
//	}
//
//	@PostMapping("/updateCurrentTreatments")
//	public ResponseObject updateCurrentTreatments(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String currentTreatments = (String) map.get("currentTreatments");
//			if (currentTreatments != null && id != 0 && !currentTreatments.isEmpty()) {
//				int updateDetails = userService.updateCurrentTreatments(currentTreatments, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("current Treatments", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("failed to update data", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "400", 0, null);
//	}
//
//	@PostMapping("/updateCovidStatus")
//	public ResponseObject updateCovidStatus(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String covidStatus = (String) map.get("covidStatus");
//			int updateDetails = userService.updateCovidStatus(covidStatus, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("covid Status sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("failed to update data", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/updateDiabetes")
//	public ResponseObject updateDiabetes(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String diabetes = (String) map.get("diabetes");
//			int updateDetails = userService.updateDiabetes(diabetes, id);
//			if (updateDetails != 0) {
//				return appUtils.prepareResponse("Diabetes updated sucessfully", "success", "200", 1, null);
//			}
//			return appUtils.prepareResponse("Diabetes cannot be sucessfull", "Failed", "400", 0, null);
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//	}
//
//	@PostMapping("/updateAddress")
//	public ResponseObject updateAddress(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String address = (String) map.get("address");
//			if (address != null && id != 0 && !address.isEmpty()) {
//				int updateDetails = userService.updateAddress(address, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("Address updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("Address not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateGender")
//	public ResponseObject updateGender(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String gender = (String) map.get("gender");
//			if (gender != null && id != 0 && !gender.isEmpty()) {
//				int updateDetails = userService.updateGender(gender, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("Gender updated sucessfully", "success", "200", 1, null);
//				}
//
//				return appUtils.prepareResponse(" Gender not Updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//
//	}
//
//	@PostMapping("/updateDateOfBirth")
//	public ResponseObject updatedateofbirth(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String dateofbirth = (String) map.get("dateofbirth");
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			String newDate = LocalDate.parse(dateofbirth, formatter).format(formatter2);
//			if (newDate.equals(null) || id != 0 || !newDate.isEmpty()) {
//				int updateDetails = userService.updateDateOfBirth(newDate, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("DateOfBirth updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("DateOfBirth not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateState")
//	public ResponseObject updatestate(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String state = (String) map.get("state");
//			if (state != null && id != 0 && !state.isEmpty()) {
//				int updateDetails = userService.updateState(state, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("State updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("State not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updatePincode")
//	public ResponseObject updatePincode(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			Integer pincode = (int) map.get("pincode");
//			if (pincode.equals(null) || id != 0) {
//				int updateDetails = userService.updatePincode(pincode, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("pincode updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("pincode not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateMaritalStatus")
//	public ResponseObject updatemaritalStatus(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String maritalStatus = (String) map.get("maritalStatus");
//			if (maritalStatus != null && id != 0 && !maritalStatus.isEmpty()) {
//				int updateDetails = userService.updateMaritalStatus(maritalStatus, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("maritalStatus updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("maritalStatus not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//
//	}
//
//	@PostMapping("/updateHeight")
//	public ResponseObject updateheight(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String height = (String) map.get("height");
//			if (height.equals(null) || id != 0) {
//				int updateDetails = userService.updateHeight(height, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("height updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("height not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateWeight")
//	public ResponseObject updateweight(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			Integer weight = (int) map.get("weight");
//			if (weight.equals(null) || id != 0) {
//				int updateDetails = userService.updateWeight(weight, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("weight updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("weight not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateSmokingStatus")
//	public ResponseObject updatesmoking(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String smoking = (String) map.get("smoking");
//			if (smoking != null && id != 0 && !smoking.isEmpty()) {
//				int updateDetails = userService.updateSmokingStatus(smoking, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("smoking updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("smoking not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateAlocholStatus")
//	public ResponseObject updatealochol(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String alochol = (String) map.get("alochol");
//			if (alochol != null && id != 0 && !alochol.isEmpty()) {
//				int updateDetails = userService.updateAlocholStatus(alochol, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("alochol updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("alochol not updated sucessfull", "Failed", "400", 0, null);
//			}
//
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateSourceofIncome")
//	public ResponseObject updateSourceofIncome(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String sourceOfIncome = (String) map.get("sourceOfIncome");
//			if (sourceOfIncome != null && id != 0 && !sourceOfIncome.isEmpty()) {
//				int updateDetails = userService.updateSourceofIncome(sourceOfIncome, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("sourceOfIncome updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("sourceOfIncome not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateCompanyName")
//	public ResponseObject updatecompanyName(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);
//			int id = (int) map.get("id");
//			String companyName = (String) map.get("companyName");
//			if (companyName != null && id != 0 && !companyName.isEmpty()) {
//				int updateDetails = userService.updateCompanyName(companyName, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("companyName updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("companyName not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateBusinessName")
//	public ResponseObject updatebusinessName(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			Map<String, Object> map = null;
//			map = mapper.readValue(json, Map.class);  
//			
//			
//			
//			
//			
//			 
//			int id = (int) map.get("id");
//			String businessName = (String) map.get("businessName");
//			if (businessName != null && id != 0 && !businessName.isEmpty()) {
//				int updateDetails = userService.updateBusinessName(businessName, id);
//				if (updateDetails != 0) {
//					return appUtils.prepareResponse("businessName updated sucessfully", "success", "200", 1, null);
//				}
//				return appUtils.prepareResponse("businessName not updated sucessfull", "Failed", "400", 0, null);
//			}
//		} catch (Exception e) {
//			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//		}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	}
//
//	@PostMapping("/updateAnnualIncome")
//			public ResponseObject updateAnnualIncome(@RequestBody String json) {
//			ObjectMapper mapper = new ObjectMapper();
//			try {
//				Map<String, Object> map = null;
//					map = mapper.readValue(json, Map.class);
//			      int id =(int) map.get("id");
//			       int annualIncome = (int) map.get("annualIncome");
//			        if(annualIncome != 0 && id !=0) {
//					int updateDetails = userService.updateAnnualIncome(annualIncome,id);
//					if(updateDetails != 0) {
//						return appUtils.prepareResponse("annualIncome updated sucessfully", "success", "200", 1, null);
//					}
//					
//					
//				return appUtils.prepareResponse("annualIncome not updated sucessfull", "Failed", "400", 0, null);
//			        }
//				} catch (Exception e) {
//					return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//				} 
//			return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//		 }
//	
//	 @PostMapping("/updateBusinessannualRevenue")
//		public ResponseObject updateBusinessannualRevenue(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//		      Map<String, Object> map = null;
//				map = mapper.readValue(json, Map.class);
//		        int id = (int) map.get("id");
//		        Integer businessAnnualRevenue = (int) map.get("businessAnnualRevenue");
//		        if(businessAnnualRevenue.equals(null)  || id != 0 ) {
//		        int updateDetails = userService.updateBusinessannualRevenue(businessAnnualRevenue,id);
//				if(updateDetails != 0) {
//					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
//				}
//			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
//		        }
//			} catch (Exception e) {
//				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//			} 
//
//			return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	 }
//
//	 @PostMapping("/updateGstNumber")
//		public ResponseObject updateGstNumber(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//		      Map<String, Object> map = null;
//				map = mapper.readValue(json, Map.class);
//		        int id = (int) map.get("id");
//		        Integer gstNumber = (int) map.get("gstNumber");
//		        if(gstNumber.equals(null)  || id != 0 ) {
//		        int updateDetails = userService.updateGstNumber(gstNumber,id);
//				if(updateDetails != 0) {
//					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
//				}
//			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
//		        }
//			} catch (Exception e) {
//				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//			} 
//
//			return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	 }
//
//	 @PostMapping("/updateBusinessType")
//		public ResponseObject updateBusinessType(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//
//			Map<String, Object> map = null;
//				map = mapper.readValue(json, Map.class);
//		       int id = (int) map.get("id");
//		        String businessType = (String) map.get("businessType");
//		        if(businessType != null && id != 0 && !businessType.isEmpty()) {
//		        int updateDetails = userService.updateBusinessType(businessType,id);
//				if(updateDetails != 0) {
//					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
//				}
//			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
//		        }
//		}catch (Exception e) {
//				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//			}
//		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//
//	 }
//
//	 @PostMapping("/updateInvestAmount")
//		public ResponseObject updateInvestAmount(@RequestBody String json) {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//		      Map<String, Object> map = null;
//				map = mapper.readValue(json, Map.class);
//		        int id = (int) map.get("id");
//		        Integer investAmount = (int) map.get("investAmount");
//		        if(investAmount.equals(null)  || id != 0 ) {
//		        int updateDetails = userService.updateInvestAmount(investAmount,id);
//				if(updateDetails != 0) {
//					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
//				}
//			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
//		        }
//			} catch (Exception e) {
//				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
//			} 
//
//			return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
//	 }
//
//	 
//	 
//	 
//	 
	@PostMapping("/updatePersonalInfo")
	public ResponseEntity<?> createUserProfilePersonalInformation(
			@RequestBody PersonalInformationEntity personalInformationEntity) {
		logger.info("updatePersonalInfo method"+personalInformationEntity);

		PersonalInformationEntity userprofilepersonalUpdate = new PersonalInformationEntity();
		PersonalInformationEntity userss = null;
		try {
			if (personalInformationEntity.getGender() != null && personalInformationEntity.getMaritalStatus() != null && personalInformationEntity.getDateOfBirth() != null && personalInformationEntity.getState() != null & personalInformationEntity.getPincode() != null && personalInformationEntity.getSmoking() != null && personalInformationEntity.getAlcohol() != null )
			{
			
				userprofilepersonalUpdate.setId(personalInformationEntity.getId());
				userprofilepersonalUpdate.setAddress(personalInformationEntity.getAddress());
				userprofilepersonalUpdate.setGender(personalInformationEntity.getGender());
				
				Date startDateString = personalInformationEntity.getDateOfBirth();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				//String newDate = LocalDate.parse(startDateString, formatter2).format(formatter2);
				userprofilepersonalUpdate.setDateOfBirth(startDateString);
				
				userprofilepersonalUpdate.setState(personalInformationEntity.getState());
				userprofilepersonalUpdate.setPincode(personalInformationEntity.getPincode());
				//userprofilepersonalUpdate.setCountry(profilePersonalInformationEntity.getCountry());
				userprofilepersonalUpdate.setMaritalStatus(personalInformationEntity.getMaritalStatus());
				userprofilepersonalUpdate.setHeight(personalInformationEntity.getHeight());
				userprofilepersonalUpdate.setWeight(personalInformationEntity.getWeight());
				userprofilepersonalUpdate.setSmoking(personalInformationEntity.getSmoking());
				userprofilepersonalUpdate.setAlcohol(personalInformationEntity.getAlcohol());
				userprofilepersonalUpdate.setCity(personalInformationEntity.getCity());
				PersonalInformationEntity createUserProfilePersonalInformation = personalInfoRepo
						.save(userprofilepersonalUpdate);
				if (createUserProfilePersonalInformation.getId() != null) {
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
	
	
	
	
	
	
	
	
	
	 @PostMapping("/updateProfessionalInfo")
		public ResponseObject updateProfessionalInfo(
				@RequestBody ProfessionalInformationEntity profileProfessionalInformationEntity) {
			logger.info("updateProfessionalInfo method"+profileProfessionalInformationEntity);

			ProfessionalInformationEntity userprofileprofessionalUpdate = new ProfessionalInformationEntity();
			ProfessionalInformationEntity users = null;
 
			
			 
			try {  
				
				  
				if (profileProfessionalInformationEntity.getSourceOfIncome().equals("business")) {
					if (profileProfessionalInformationEntity.getId() != null
							
							&& profileProfessionalInformationEntity.getBusinessAnnualRevenue() != 0
							&& profileProfessionalInformationEntity.getGstNumber() != null
							&& profileProfessionalInformationEntity.getBusinessType() != null
							&& profileProfessionalInformationEntity.getInvestAmount() != 0) {
						userprofileprofessionalUpdate.setId(profileProfessionalInformationEntity.getId());
						userprofileprofessionalUpdate
								.setSourceOfIncome(profileProfessionalInformationEntity.getSourceOfIncome());
						userprofileprofessionalUpdate
								.setBusinessAnnualRevenue(profileProfessionalInformationEntity.getBusinessAnnualRevenue());
						userprofileprofessionalUpdate.setGstNumber(profileProfessionalInformationEntity.getGstNumber());
						userprofileprofessionalUpdate
								.setBusinessType(profileProfessionalInformationEntity.getBusinessType());
						userprofileprofessionalUpdate
								.setInvestAmount(profileProfessionalInformationEntity.getInvestAmount());
						ProfessionalInformationEntity userEntity = professionalInfoRepo.save(userprofileprofessionalUpdate);
						if (userEntity.getId() != null) {
							return appUtils.prepareResponse("Data Saved Successfully", "Success", "200", 1, null);
						} else {
							return appUtils.prepareResponse("Unable to save data", "Failed", "400", 0, null);
						}
					} else {
						return appUtils.prepareResponse("Mandatory fileds are missing", "failed", "500", 0, null);
					}
				} else if (profileProfessionalInformationEntity.getSourceOfIncome().equals("employment")) {
					if (profileProfessionalInformationEntity.getId() != null
							
							&& profileProfessionalInformationEntity.getAnnualIncome() != null) {
						userprofileprofessionalUpdate.setId(profileProfessionalInformationEntity.getId());
						userprofileprofessionalUpdate
								.setSourceOfIncome(profileProfessionalInformationEntity.getSourceOfIncome());
						
						userprofileprofessionalUpdate
								.setAnnualIncome(profileProfessionalInformationEntity.getAnnualIncome());
						userprofileprofessionalUpdate
								.setInvestAmount(profileProfessionalInformationEntity.getInvestAmount());
						ProfessionalInformationEntity userEntity = professionalInfoRepo.save(userprofileprofessionalUpdate);
						if (userEntity.getId() != null) {
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
   
	
	
	@PostMapping(value = "/updateMedicalInfo", consumes = "multipart/form-data")
	public ResponseObject updateMedicalInfo(@RequestParam String id, @RequestParam(value = "pastSurgeries",required =  false) String pastSurgeries,
			@RequestParam(value = "uploadBpReport", required =  false ) List<MultipartFile> uploadBpReport,
			@RequestParam(value = "uploadDiabetesReport", required = false) List<MultipartFile> uploadDiabetesReport,
			@RequestParam(value = "uploadHeartStrokeReport", required = false) List<MultipartFile> uploadHeartStrokeReport,
			@RequestParam(value = "uploadOtherReport", required = false) List<MultipartFile> uploadOtherReport, @RequestParam(value = "uploadAsthmaReport", required = false) List<MultipartFile> uploadAsthmaReport,
			@RequestParam(value = "currentTreatments",required = false) String currentTreatments, @RequestParam(value = "covidStatus", required = false) String covidStatus, @RequestParam String bloodGroup) throws IOException {
		MedicalInformationEntity userprofilemedicalUpdate = new MedicalInformationEntity();
		logger.info("updateMedicalInfo  method" + userprofilemedicalUpdate);
		try {
			if (id != null) {
				userprofilemedicalUpdate.setId(id);
				userprofilemedicalUpdate.setPastSurgeries(pastSurgeries);
				userprofilemedicalUpdate.setCurrentTreatments(currentTreatments);
				userprofilemedicalUpdate.setCovidStatus(covidStatus);
				userprofilemedicalUpdate.setBloodGroup(bloodGroup);
				List<byte[]> byteArraysBp = new ArrayList<>();
				List<byte[]> byteArraysDiabetes = new ArrayList<>();
				List<byte[]> byteArraysHeartStroke = new ArrayList<>();
				List<byte[]> byteArrayAsthmaReport = new ArrayList<>();
				List<byte[]> byteArraysOther = new ArrayList<>();
				if (uploadBpReport != null) {
					for (MultipartFile file : uploadBpReport) {
						try {
							byte[] bytes = file.getBytes(); // Get bytes from MultipartFile
//							String filename = "sample_pdf.pdf";
//							Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//							Files.write(fileStorage, bytes);
							byteArraysBp.add(bytes);
//							System.out.println("File saved successfully at: " + fileStorage.toString());

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					// Usage of byte arrays
					for (byte[] byteArray : byteArraysBp) {
			            userprofilemedicalUpdate.setUploadBpReport(byteArraysBp.isEmpty() ? null : byteArraysBp.get(0));

					}
				}
				if (uploadAsthmaReport != null) {
					for (MultipartFile file : uploadAsthmaReport) {
						try {
							byte[] bytes = file.getBytes(); // Get bytes from MultipartFile
//							String filename = "sample_pdf.pdf";
//							Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//							Files.write(fileStorage, bytes);
							byteArrayAsthmaReport.add(bytes);	
//					        System.out.println("File saved successfully at: " + fileStorage.toString());				
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					for (byte[] byteArray1 : byteArrayAsthmaReport) {
			            userprofilemedicalUpdate.setUploadAsthmaReport(byteArrayAsthmaReport.isEmpty() ? null : byteArrayAsthmaReport.get(0));
					}
				}
				if (uploadDiabetesReport != null) {
					for (MultipartFile file : uploadDiabetesReport) {
						try {
							byte[] bytes = file.getBytes(); // Get bytes from MultipartFile
//							String filename = "sample_pdf.pdf";
//							Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//							Files.write(fileStorage, bytes);
							byteArraysDiabetes.add(bytes);						
//					        System.out.println("File saved successfully at: " + fileStorage.toString());						
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					for (byte[] byteArray1 : byteArraysDiabetes) {
			            userprofilemedicalUpdate.setUploadDiabetesReport(byteArraysDiabetes.isEmpty() ? null : byteArraysDiabetes.get(0));
					}
				}
				if (uploadHeartStrokeReport != null) {
					for (MultipartFile file : uploadHeartStrokeReport) {
						try {
							byte[] bytes = file.getBytes(); // Get bytes from MultipartFile					
//							   String filename = "sample_pdf.pdf";			                
//				                Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//								copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//						        Files.write(fileStorage, bytes);		
						        byteArraysHeartStroke.add(bytes);	
//					        System.out.println("File saved successfully at: " + fileStorage.toString());
							
						} catch (IOException e) {
							// Handle exception (e.g., log or throw)
							e.printStackTrace();
						}
					}
					for (byte[] byteArray2 : byteArraysHeartStroke) {
			            userprofilemedicalUpdate.setUploadHeartStrokeReport(byteArraysHeartStroke.isEmpty() ? null : byteArraysHeartStroke.get(0));
					}
				}
				if (uploadOtherReport != null) {
					for (MultipartFile file : uploadOtherReport) {
						try {
							byte[] bytes = file.getBytes(); // Get bytes from MultipartFile
//							String filename = "sample_pdf.pdf";	
//			                Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//					        Files.write(fileStorage, bytes);
					        byteArraysOther.add(bytes);
//					        System.out.println("File saved successfully at: " + fileStorage.toString());
						} catch (IOException e) {
							// Handle exception (e.g., log or throw)
							e.printStackTrace();
						}
					}
					// Usage of byte arrays
					for (byte[] byteArray3 : byteArraysOther) {
			            userprofilemedicalUpdate.setUploadOtherReport(byteArraysOther.isEmpty() ? null : byteArraysOther.get(0));
					}
				}
				MedicalInformationEntity saveMedicalInformation = medicalInfoRepo.save(userprofilemedicalUpdate);
				if (saveMedicalInformation.getId() != null) {
					return appUtils.prepareResponse("Data saved successfully", "Success", "200", 1,null);
				} else {
					return appUtils.prepareResponse("Failed to save Data", "Failed", "400", 1, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("inside catch block" + e.getMessage());

			return appUtils.prepareResponse("internal server error", "Failer", "500", 1,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUtils.prepareResponse("internal server error", "Failer", "500", 1, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	@PostMapping(value = "/updateFamilyInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseObject createUserProfileFamilyInformation(@RequestParam String id,@RequestParam(value = "fatherName", required = false) String fatherName, @RequestParam(value = "fatherAge", required = false) Integer fatherAge, @RequestParam(value = "fatherOccupation", required = false) String fatherOccupation,
			@RequestParam(value = "fatherUploadMedicalHistory", required = false) List<MultipartFile> fatherUploadMedicalHistory,
			@RequestParam(value = "motherName", required = false) String motherName, @RequestParam(value = "motherAge", required = false) Integer motherAge, @RequestParam(value = "motherOccupation", required = false) String motherOccupation,
			@RequestParam(value = "motherUploadMedicalHistory", required = false) List<MultipartFile> motherUploadMedicalHistory,
			@RequestParam(value = "spouseName", required = false) String spouseName, @RequestParam(value = "spouseAge", required = false) Integer spouseAge, @RequestParam(value = "spouseOccupation", required = false) String spouseOccupation,
			@RequestParam(value = "spouseUploadMedicalHistory", required = false) List<MultipartFile> spouseUploadMedicalHistory,
			@RequestParam(value = "otherName", required = false) String otherName, @RequestParam(value = "otherAge", required = false) Integer otherAge, @RequestParam(value = "otherOccupation", required = false) String otherOccupation,
			@RequestParam(value = "uploadOtherMedicalHistory", required = false) List<MultipartFile> uploadOtherMedicalHistory) {
		FamilyInformationEntity userProfileFamilyUpdate = new FamilyInformationEntity();
		FamilyInformationEntity userss = null;
		try {
//		if (profileFamilyUpdate != null) {
			if (id != null) {
				userProfileFamilyUpdate.setId(id);
				userProfileFamilyUpdate.setFatherName(fatherName);
//			userProfileFamilyUpdate.setFatherAge(Integer.parseInt(fatherAge));
				userProfileFamilyUpdate.setFatherAge(fatherAge);
				userProfileFamilyUpdate.setFatherOccupation(fatherOccupation);
//			userProfileFamilyUpdate.setFatherUploadMedicalHistory(fatherUploadMedicalHistory)
				userProfileFamilyUpdate.setMotherName(motherName);
				userProfileFamilyUpdate.setMotherAge(motherAge);
				userProfileFamilyUpdate.setMotherOccupation(motherOccupation);
//			userProfileFamilyUpdate.setMotherUploadMedicalHistory(motherUploadMedicalHistory);
				userProfileFamilyUpdate.setSpouseName(spouseName);
				userProfileFamilyUpdate.setSpouseAge(spouseAge);
				userProfileFamilyUpdate.setSpouseOccupation(spouseOccupation);
//			userProfileFamilyUpdate.setSpouseUploadMedicalHistory(spouseUploadMedicalHistory);
				userProfileFamilyUpdate.setOtherName(otherName);
				userProfileFamilyUpdate.setOtherAge(otherAge);
				userProfileFamilyUpdate.setOtherOccupation(otherOccupation);
//			userProfileFamilyUpdate.setuploadOtherNomineeMedicalHistory(uploadotherMedicalHistory);
				List<byte[]> byteArraysfatherUploadMedicalHistory = new ArrayList<>();
				List<byte[]> byteArraysmotherUploadMedicalHistory = new ArrayList<>();
				List<byte[]> byteArraysspouseUploadMedicalHistory = new ArrayList<>();
				List<byte[]> byteArraysuploadOtherNomineeMedicalHistory = new ArrayList<>();
				if (fatherUploadMedicalHistory != null) {
					for (MultipartFile file : fatherUploadMedicalHistory) {
						try {
							byte[] bytes = file.getBytes(); // Get bytes from MultipartFile
//							String filename = "sample_pdf.pdf";
//							Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//							Files.write(fileStorage, bytes);
							byteArraysfatherUploadMedicalHistory.add(bytes);
//							System.out.println("File saved successfully at: " + fileStorage.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					// Usage of byte arrays
					for (byte[] byteArray : byteArraysfatherUploadMedicalHistory) {
						userProfileFamilyUpdate
								.setFatherUploadMedicalHistory(byteArraysfatherUploadMedicalHistory.isEmpty() ? null
										: byteArraysfatherUploadMedicalHistory.get(0));
					}
				}
				if (motherUploadMedicalHistory != null) {
					for (MultipartFile file : motherUploadMedicalHistory) {
						try {
							byte[] bytes = file.getBytes();
//							String filename = "sample_pdf.pdf";
//							Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//							Files.write(fileStorage, bytes);
							byteArraysmotherUploadMedicalHistory.add(bytes);
//							System.out.println("File saved successfully at: " + fileStorage.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					// Usage of byte arrays
					for (byte[] byteArray : byteArraysmotherUploadMedicalHistory) {
						userProfileFamilyUpdate
								.setMotherUploadMedicalHistory(byteArraysmotherUploadMedicalHistory.isEmpty() ? null
										: byteArraysmotherUploadMedicalHistory.get(0));
					}
				}
				if (spouseUploadMedicalHistory != null) {
					for (MultipartFile file : spouseUploadMedicalHistory) {
						try {
							byte[] bytes = file.getBytes();
//							String filename = "sample_pdf.pdf";
//							Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//							Files.write(fileStorage, bytes);
							byteArraysspouseUploadMedicalHistory.add(bytes);
//							System.out.println("File saved successfully at: " + fileStorage.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					for (byte[] byteArray : byteArraysspouseUploadMedicalHistory) {
						userProfileFamilyUpdate
								.setSpouseUploadMedicalHistory(byteArraysspouseUploadMedicalHistory.isEmpty() ? null
										: byteArraysspouseUploadMedicalHistory.get(0));
					}
				}
				if (uploadOtherMedicalHistory != null) {
					for (MultipartFile file : uploadOtherMedicalHistory) {
						try {
							byte[] bytes = file.getBytes();
//							String filename = "sample_pdf.pdf";
//							Path fileStorage = Paths.get(DIRECTORY, filename).toAbsolutePath().normalize();
//							copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
//							Files.write(fileStorage, bytes);
							byteArraysuploadOtherNomineeMedicalHistory.add(bytes);
//							System.out.println("File saved successfully at: " + fileStorage.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					for (byte[] byteArray : byteArraysuploadOtherNomineeMedicalHistory) {
						userProfileFamilyUpdate.setOtherMedicalHistory(byteArraysuploadOtherNomineeMedicalHistory.isEmpty() ? null: byteArraysuploadOtherNomineeMedicalHistory.get(0));
					}
				}
				FamilyInformationEntity createUserProfileFamilyInformation = familyInfoRepo.save(userProfileFamilyUpdate);
				if (createUserProfileFamilyInformation.getId() != null) {
					return appUtils.prepareResponse("Data saved successfully", "Success", "200", 1,null);
				} else {
					return appUtils.prepareResponse("Failed to save Data", "Failed", "400", 1, null);
				}
			}
			// Usage of byte arrays
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("inside catch block" + e.getMessage());
			return appUtils.prepareResponse("internal server error", "Failer", "500", 1,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUtils.prepareResponse("internal server error", "Failer", "500", 1, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
}

