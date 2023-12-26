package com.cos.master.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.cos.master.utils.ProjectProperties;
import com.google.gson.Gson;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
@ComponentScan(basePackages = "com.callhealth.sms")
public class TokenAuthenticationFilter implements Filter {
	private final String authTokenHeaderName = "cos_app_access_token";
	
	@Autowired
	ProjectProperties projectProperties;
	
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("testing filter");
        try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
			String authToken = httpServletRequest.getHeader(authTokenHeaderName);
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			/*response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Expose-Headers", "Authorization");
			response.setHeader("Access-Control-Allow-Headers",
					"x-auth-token, Content-Type, Accept, X-Requested-With, remember-me, Authorization,"
							+ authTokenHeaderName);
			System.out.println(httpServletRequest.getHeaderNames());*/

			if (authToken != null) {

				String passedToken = getTokenFromAuthToken(authToken);
//				if (!isAllowedRequest(passedToken)) { /*enable this condition when the isValidSession method is implemented inside isAllowedRequest method*/
				String accessToken = projectProperties.getProperty("cos_app_access_token");
				if (!passedToken.equals(accessToken)) { /*remove this condition  when the isValidSession method is implemented inside isAllowedRequest method*/
					HttpServletResponse resp = (HttpServletResponse) servletResponse;
					resp.reset();
					resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				} else {
					filterChain.doFilter(servletRequest, response);
				}
			} else {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.reset();
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
		}
    }
    
    @SuppressWarnings("unchecked")
	private boolean isAllowedRequest(String authToken) {

		try {
			
			//String ch_app_access_token ="eThWmZq4t6w9z$C&F)J@NcRfUjXn2r5u8x!A%D*G-KaPdSgVkYp3s6v9y$B?E(H+MbQeThWmZq4t7w!z%C*F)J@NcRfUjXn2r5u8x/A?D(G+KbPdSgVkYp3s6v9y$B&E"; //ProjectProperties.getProperty("ch_app_access_token");
			String cos_app_access_url = projectProperties.getProperty("cos_app_access_url");

			HttpURLConnection httpClient = (HttpURLConnection) (new URL(cos_app_access_url+"api/auth/isValidSession").openConnection());

			byte[] out = "{}".getBytes(StandardCharsets.UTF_8);

			int length = out.length;

			httpClient.setFixedLengthStreamingMode(length);

			httpClient.setDoOutput(true);

			httpClient.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			httpClient.setRequestProperty("cos_app_access_token", authToken);

			httpClient.setRequestMethod("POST");

			httpClient.connect();

			OutputStream os = httpClient.getOutputStream();

			os.write(out);

			BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));

			String inputLine;

			StringBuffer sb = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {

				sb.append(inputLine);

			}

			in.close();

			Map<String, Boolean> validity = new Gson().fromJson(sb.toString(), Map.class);

			return validity.get("status");

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}


	private String getTokenFromAuthToken(String authToken) {
		if (authToken == null) {
			return null;
		}
		String parts = authToken.replaceAll(authTokenHeaderName, "");
		return parts;
	}
}
