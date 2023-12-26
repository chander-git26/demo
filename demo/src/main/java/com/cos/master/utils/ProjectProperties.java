package com.cos.master.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
public class ProjectProperties {
	@Autowired
	private Environment env;

	public  String getProperty(String propertyKey){
		System.out.println("Environment key :"+env.getProperty(propertyKey));
		return env.getRequiredProperty(propertyKey);
	}
}
