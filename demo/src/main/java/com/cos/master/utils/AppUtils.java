package com.cos.master.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cos.master.entities.ResponseObject;
import com.cos.master.repository.UserRepository;

@Component
public class AppUtils {
	@Autowired
	UserRepository userRepo;
	
	public long generateUserId() {
		long newUserId =userRepo.generateUserId();
		newUserId ++;
		return newUserId;
		
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
