//package com.cos.master.security;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//
//@Configuration
//@EnableConfigurationProperties
//@Component
//public class AuthenticationTokenFilter implements Filter {
//	private static final String authTokenHeaderName = "cos_access_token";
//	
//	private static String cosAccessUrl;
//	
//	@Value("$(cos_access_token)")
//	public void setCosAccessUrl(String value) {
//		this.cosAccessUrl = value;
//	}
//	
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
//		System.out.println("starting do filer method");
//	}
//	
//}
