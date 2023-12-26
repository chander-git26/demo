package com.cos.master.utils;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.Base64.Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.cos.master.entities.ResponseObject;
import com.cos.master.repository.UserRepository;


import jakarta.persistence.Cache;



@Component
public class AppUtils {
	 public  static final  Logger logger = LoggerFactory.getLogger(Logger.class.getName());
	@Autowired
	UserRepository userRepo;
	
	public String generateUserId(String firstName, String mobile) {
		String userId = "";
		try {
			
			for (int i = 0; i < firstName.length()-1; i++) {
				if (mobile.substring(0,10) !=null) {
					userId += firstName.charAt(i);
					userId += mobile.charAt(i);
				}else {
					return userId;
				}
			}
			return userId;
		} catch (Exception e) {
			return userId;
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
//	public String validateOtp(String mobile){
//		return userRepo.getUserOtp(mobile);	
//	}
	public ResponseObject prepareResponse(String reason, String status, String statusCode, int resultCount, Object dataObject ) {
		ResponseObject responseObject= new ResponseObject();
		responseObject.setReason(reason);
		responseObject.setStatus(status);
		responseObject.setStausCode(statusCode);
		responseObject.setCount(resultCount);
		responseObject.setData(dataObject);
		
		return responseObject;
	}
	
	
	@Scheduled(cron = "0 0 9 25 * ?")
	public String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[100];
		random.nextBytes(bytes);
		Encoder encoder = Base64.getUrlEncoder().withoutPadding();
		String token = encoder.encodeToString(bytes);
		//System.out.println(token);

		return token;
	}

}
