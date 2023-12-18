package com.cos.master.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.master.entities.ResponseObject;
import com.cos.master.service.UserService;
import com.cos.master.utils.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/personal", produces = MediaType.APPLICATION_JSON_VALUE )


public class UpdatePersonalDetailsController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AppUtils  appUtils;
 
	
	@PostMapping("/updateAddress")
	public ResponseObject updateAddress(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> map = null;
			map = mapper.readValue(json, Map.class);
			int id = (int) map.get("id");
			String address = (String) map.get("address");
			if(address != null && id != 0 && !address.isEmpty()) {
				int updateDetails = userService.updateAddress(address, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
			}
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
		}
		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	}	
		
	@PostMapping("/updateGender")
	public ResponseObject updateGender(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> map = null;
			map = mapper.readValue(json, Map.class);
			int id = (int) map.get("id");
			String gender = (String) map.get("gender");
			if(gender != null && id != 0 && !gender.isEmpty()) {
			int updateDetails = userService.updateGender(gender, id);
			if (updateDetails != 0) {
				return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
			}

			return appUtils.prepareResponse(" Data not Updated sucessfull", "Failed", "400", 0, null);
			}
		} catch (Exception e) {
			
			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
		}
		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
		
	}
			 
	 @PostMapping("/updateDateOfBirth")
	public ResponseObject updatedateofbirth(@RequestBody String json) {
	ObjectMapper mapper = new ObjectMapper();
		try {
	
		Map<String, Object> map = null;
			map = mapper.readValue(json, Map.class);
		        int id = (int) map.get("id");
		        String dateofbirth = (String) map.get("dateofbirth");
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        String newDate = LocalDate.parse(dateofbirth, formatter).format(formatter2);
		        if(newDate.equals(null)  || id != 0 || !newDate.isEmpty()) {
				int updateDetails = userService.updateDateOfBirth(newDate,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
			} catch (Exception e) {
				e.printStackTrace();
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
		   return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	 }
  
	 @PostMapping("/updateState")
		public ResponseObject updatestate(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		
			Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
		        int id =(int) map.get("id");
		        String state = (String) map.get("state");
		        if(state != null && id != 0 && !state.isEmpty()) {
			  int updateDetails = userService.updateState(state,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
			} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
		
		 return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	 }
     
	 
	 @PostMapping("/updateCountry")
		public ResponseObject updatecountry(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		
			Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
		        int id = (int) map.get("id");
		        String country = (String) map.get("country");
		        if(country.equals(country)  || id != 0 || !country.isEmpty()) {
		       int updateDetails = userService.updateCountry(country,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
		 return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	 }
	 
	 
	 @PostMapping("/updateMaritalStatus")
		public ResponseObject updatemaritalStatus(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		
			Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
		       int id = (int) map.get("id");
		        String maritalStatus = (String) map.get("maritalStatus");
		        if(maritalStatus != null && id != 0 && !maritalStatus.isEmpty()) {
		        int updateDetails = userService.updateMaritalStatus(maritalStatus,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
		}catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);

	 }
	 
	 
	 @PostMapping("/updateBloodGroup")
		public ResponseObject updatebloodGroup(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		
			Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
		        int id = (int) map.get("id");
		        String bloodGroup = (String) map.get("bloodGroup");
		        if(bloodGroup != null && id != 0 && !bloodGroup.isEmpty()) {
				int updateDetails = userService.updateBloodGroup(bloodGroup,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	 }
	 
	 @PostMapping("/updateHeight")
		public ResponseObject updateheight(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		
			Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
		       int id = (int) map.get("id");
		        Integer height = (int) map.get("height");
		        if(height.equals(null)  || id != 0 ) {
		        int updateDetails = userService.updateHeight(height,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }	        
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
			
			return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
		}
	 
	 
	 @PostMapping("/updateWeight")
		public ResponseObject updateweight(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		      Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
		        int id = (int) map.get("id");
		        Integer weight = (int) map.get("weight");
		        if(weight.equals(null)  || id != 0 ) {
		        int updateDetails = userService.updateWeight(weight,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
		          
			return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	 }
	 
	 @PostMapping("/updateSmokingStatus")
		public ResponseObject updatesmoking(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		
			Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				 int id = (int) map.get("id");
		        String smoking = (String) map.get("smoking");
		        if(smoking != null && id != 0 && !smoking.isEmpty()) {
				int updateDetails = userService.updateSmokingStatus(smoking,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	 }
	 
	 
	 @PostMapping("/updateAlocholStatus")
		public ResponseObject updatealochol(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
		
			Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
		      int id =(int) map.get("id");
		       String alochol = (String) map.get("alochol");
		        if(alochol != null && id != 0 && !alochol.isEmpty()) {
				int updateDetails = userService.updateAlocholStatus(alochol,id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("Data updated sucessfully", "success", "200", 1, null);
				}
			return appUtils.prepareResponse("Data not updated sucessfull", "Failed", "400", 0, null);
		        }
			
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			} 
		return appUtils.prepareResponse("Failed to update data", "failed", "500", 0, null);
	 }

}	
		
		
		
		
		
		
		
		
		
		
		

