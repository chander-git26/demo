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
 
	@PostMapping("/updateFathername")
	public ResponseObject updateFathername(@RequestBody String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, Object> map = null;
			map = mapper.readValue(json, Map.class);
			int id = (int) map.get("id");
			String father_name = (String) map.get("father_name");
			int updateDetails = userService.updateFathername(father_name, id);
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
			int age = (int) map.get("age");
			int updateDetails = userService.updateAge(age, id);
			if(updateDetails != 0) {
				return appUtils.prepareResponse("address updated sucessfully", "success", "200", 1, null);
			}
			return appUtils.prepareResponse("address cannot be sucessfull", "Failed", "400", 0, null);
		} catch (Exception e) {
			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
		}
}
}