package com.cos.master.utils;

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
	public int generateOtp() {
		Random random = new Random();
		int randomNumber = random.nextInt();
		System.out.println("generated number :"+randomNumber);
		if(randomNumber <= 1000) {
			randomNumber += 1000;
			System.out.println("incremented number :"+randomNumber);
		}
		return randomNumber;
	}
	public String validateOtp(String userId){
		return userRepo.getUserOtp(userId);	
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
