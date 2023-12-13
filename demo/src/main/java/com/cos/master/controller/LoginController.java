package com.cos.master.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.master.entities.ResponseObject;
import com.cos.master.repository.UserRepository;
import com.cos.master.utils.AppUtils;

@RestController
@RequestMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE )
public class LoginController {
	@Autowired
	AppUtils appUtils;
	
	@Autowired
	UserRepository userRepo;
	
//	@GetMapping("/verifyMobileNumber/{mobile}")
//	public ResponseObject verifyMobile(@PathVariable("mobile") String mobile){
//		
//		String otp = appUtils.generateOtp();
//		if(otp != null) {
//			int rowsAffected = userRepo.saveOtp(otp,mobile);
//			if(rowsAffected !=0) {
//				Map<String, Object> otpMap = new HashMap<>();
//				otpMap.put("otp", otp);
//				return  appUtils.prepareResponse("User otp generated", "success", "200", 1, null);	
//			}
//		}
//		return  appUtils.prepareResponse("User otp generated", "success", "400", 0, null);
//	}
}
