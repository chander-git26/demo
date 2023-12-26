package com.cos.master.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.master.entities.ResponseObject;
import com.cos.master.utils.AppUtils;

@RestController
@RequestMapping("healthPackage")
public class HealthPackagesController {
	@Autowired
	AppUtils appUtils;
	
	@GetMapping("/getAllHealthPackages")
	public ResponseObject getAllHealthPackages(){
		return null;
	}
}
