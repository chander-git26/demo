package com.cos.master.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.generic.ObjectType;
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

import com.cos.master.entities.ResponseObject;
import com.cos.master.entities.UserEntity;
import com.cos.master.entities.UserResponse;
import com.cos.master.repository.UserRepository;
import com.cos.master.service.UserService;
import com.cos.master.utils.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE )
public class LoginController {
	public static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	AppUtils appUtils;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/generateOtp")
	public ResponseObject generateOtp(@RequestBody String json) {
		logger.info("generateOtp method "+json);

		
		Map<String, Object> map = null;
		try {
		ObjectMapper mapper = new ObjectMapper();
		map = mapper.readValue(json, Map.class);
		String mobile = (String) map.get("mobile");
			if (mobile != null) {
				String mobileNum = userService.verifyMobileNum(mobile);
				if (mobileNum == null || mobile.isEmpty()) {
					return appUtils.prepareResponse("Mobile number not registered", "Failed", "400", 0, null);
				}else {
					String otp = appUtils.generateOtp();
					if (otp != null) {
						int rowsAffected = userRepo.saveOtp(otp, mobile);
						if (rowsAffected != 0) {
							Map<String, Object> otpMap = new HashMap<>();
							otpMap.put("otp", otp);
							return appUtils.prepareResponse("User otp generated", "success", "200", 1, null);
						}
					}
				}
			}
			return appUtils.prepareResponse("mobile number cannot be empty", "Failed", "400", 0, null);
		} catch (Exception e) {
			logger.info("inside catch block  "+e.getMessage());

			return appUtils.prepareResponse("some error occured", "failed", "500", 0, null);
		}
	}

	@PostMapping("/validateOtp")
	public ResponseEntity<Object> getUserOtp(@RequestBody String json) {
	    logger.info("validateOtp method " + json);
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        Map<String, Object> map = null;
	        map = mapper.readValue(json, Map.class);
	        String otp = (String) map.get("otp");
	        String mobile = (String) map.get("mobile");
	        if (otp != null && mobile != null) {
	        	UserResponse userData = userService.getUserOtp(mobile);
	            if (otp.equals(userData.getOtp())) {
	                String userId = userService.getUserMobile(mobile);
	                if (userId != null) {         
//	                    return appUtils.prepareResponse("OTP Verified Successfully", "success", "200", 1, userData.getUserId());
	                	return new ResponseEntity<Object>("OTP Verified Successfully", HttpStatus.OK);
	                } else {
//	                    return appUtils.prepareResponse("User ID not found", "Failed", "500", 0, null);
	                	return new ResponseEntity<Object>("User ID not found", HttpStatus.UNAUTHORIZED);
	                }
	            } else {
	              //  return appUtils.prepareResponse("Incorrect OTP", "Failed", "400", 0, null);
	                return new ResponseEntity<Object>("Incorrect OTP", HttpStatus.UNAUTHORIZED);
	            }
	        } else {
	           // return appUtils.prepareResponse("Mandatory field are missing", "Failed", "400", 0, null);
	            return new ResponseEntity<Object>("Mandatory field are missing", HttpStatus.UNAUTHORIZED);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
//	        return appUtils.prepareResponse("Some error occurred", "failed", "500", 0, null);
	        return new ResponseEntity<Object>("Some error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
