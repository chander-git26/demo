package com.cos.master.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.master.entities.ResponseObject;
import com.cos.master.service.UserService;
import com.cos.master.utils.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/update")
public class UpdateDetailsController {

	@Autowired
	UserService userService;
	
	@Autowired
	AppUtils  appUtils;
 
	@PostMapping("/updateFatherName")
	public ResponseObject updateFatherName(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> map = null;
			map = mapper.readValue(json, Map.class);
			int id = (int) map.get("id");
			String father_name = (String) map.get("father_name");
			int updateDetails = userService.updateFatherName(father_name, id);
			if(updateDetails != 0) {
				return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
			}
			return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
		}
	

}
	
	@PostMapping("/updateAge")
	public ResponseObject updateAge(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> map = null;
			map = mapper.readValue(json, Map.class);
			int id = (int) map.get("id");
			int father_age = (int) map.get("father_age");
			int updateDetails = userService.updateAge(father_age, id);
			if(updateDetails != 0) {
				return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
			}
			return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
		}
	}	
		
		@PostMapping("/upadteFatherOccupation")
		public ResponseObject upadteFatherOccupation(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String father_occupation = (String) map.get("father_occupation");
				int updateDetails = userService.upadteFatherOccupation(father_occupation, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		
		@PostMapping("/upadteMotherName")
		public ResponseObject upadteMotherName(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String mother_Name = (String) map.get("mother_Name");
				int updateDetails = userService.upadteMotherName(mother_Name, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		
		@PostMapping("/updateMotherAge")
		public ResponseObject updateMotherAge(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				int mother_Name = (int) map.get("mother_Name");
				int updateDetails = userService.updateMotherAge(mother_Name, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		}
		
		@PostMapping("/upadteMotherOccupation")
		public ResponseObject upadteMotherOccupation(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String mother_occupation = (String) map.get("mother_occupation");
				int updateDetails = userService.upadteMotherOccupation(mother_occupation, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		
		@PostMapping("/upadteSpouseName")
		public ResponseObject upadteSpouseName(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String spouse_name = (String) map.get("spouse_name");
				int updateDetails = userService.upadteSpouseName(spouse_name, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		
		
		@PostMapping("/updateSpouseAge")
		public ResponseObject updateSpouseAge(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				int spouse_age = (int) map.get("spouse_age");
				int updateDetails = userService.updateSpouseAge(spouse_age, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		
		@PostMapping("/updateSpouseOccupation")
		public ResponseObject updateSpouseOccupation(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String spouse_occupation = (String) map.get("spouse_occupation");
				int updateDetails = userService.updateSpouseOccupation(spouse_occupation, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		
		@PostMapping("/updateNominee1Name")
		public ResponseObject updateNominee1Name(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String nominee1_name = (String) map.get("nominee1_name");
				int updateDetails = userService.updateNominee1Name(nominee1_name, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		@PostMapping("/updateNominee2Name")
		public ResponseObject updateNominee2Name(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String nominee2_name = (String) map.get("nominee2_name");
				int updateDetails = userService.updateNominee2Name(nominee2_name, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		@PostMapping("/updateOtherNomineeName")
		public ResponseObject updateOtherNomineeName(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String other_nominee_name = (String) map.get("other_nominee_name");
				int updateDetails = userService.updateOtherNomineeName(other_nominee_name, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		
		
}
		
		@PostMapping("/updateOtherNomineeAge")
		public ResponseObject updateOtherNomineeAge(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				int other_nominee_age = (int) map.get("other_nominee_age");
				int updateDetails = userService.updateOtherNomineeAge(other_nominee_age, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		}	
		
		@PostMapping("/updateOtherNomineeRelation")
		public ResponseObject updateOtherNomineeRelation(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String other_nominee_relation = (String) map.get("other_nominee_relation");
				int updateDetails = userService.updateOtherNomineeRelation(other_nominee_relation, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		}	
		
		@PostMapping("/updateSelectNumberOfChildren")
		public ResponseObject updateSelectNumberOfChildren(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				int select_number_of_children = (int) map.get("select_number_of_children");
				int updateDetails = userService.updateSelectNumberOfChildren(select_number_of_children, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		}	
		
		@PostMapping("/updatePastSurgeries")
		public ResponseObject updatePastSurgeries(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String past_surgeries = (String) map.get("past_surgeries");
				int updateDetails = userService.updatePastSurgeries(past_surgeries, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		}	
		
		@PostMapping("/updateBloodPressure")
		public ResponseObject updateBloodPressure(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String blood_pressure = (String) map.get("blood_pressure");
				int updateDetails = userService.updateBloodPressure(blood_pressure, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		}
		
		@PostMapping("/updateDiabetes")
		public ResponseObject updateDiabetes(@RequestBody String json) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				Map<String, Object> map = null;
				map = mapper.readValue(json, Map.class);
				int id = (int) map.get("id");
				String diabetes = (String) map.get("diabetes");
				int updateDetails = userService.updateDiabetes(diabetes, id);
				if(updateDetails != 0) {
					return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
				}
				return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
			} catch (Exception e) {
				return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
			}
		}	
		
}