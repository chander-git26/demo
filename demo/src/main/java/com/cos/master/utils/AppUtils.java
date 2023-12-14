package com.cos.master.utils;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cos.master.entities.ResponseObject;
import com.cos.master.repository.UserRepository;

@Component
public class AppUtils {
	@Autowired
	UserRepository userRepo;
	
	public long generateUserId() {
		long userId = 1000;
		String newUserId = userRepo.generateUserId();
		if(newUserId.isEmpty() || newUserId.equals("null")) {
			return userId;
		}else {
			long id = Long.parseLong(newUserId);
			++id;
			return id;
		}
	}
	public String generateOtp() {
		String generatedOtp = "";
		generatedOtp = new DecimalFormat("000000").format(new Random().nextInt(999999));
		if(generatedOtp != null) {
			return generatedOtp;
		}
		return generatedOtp;
	}
	public String validateOtp(String mobile){
		return userRepo.getUserOtp(mobile);	
	}
	public ResponseObject prepareResponse(String reason, String status, String statusCode, int resultCount, Object dataObject ) {
		ResponseObject responseObject= new ResponseObject();
		responseObject.setReason(reason);
		responseObject.setStatus(status);
		responseObject.setStausCode(statusCode);
		responseObject.setCount(resultCount);
		responseObject.setData(dataObject);
		
		return responseObject;
	}
}
