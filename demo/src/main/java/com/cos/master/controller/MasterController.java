package com.cos.master.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(value ="/master")
public class MasterController {
	@Value("${ENVIRONMENT}")
	private String environement_key;
	
//	@ApiResponses (value = { @ApiResponse (code = 200, message = "Application is running successfully"),
//			@ApiResponse(code = 400, message = "The resource you were trying to reach is not found")})
	@GetMapping(value = "/ping")
	public String ping(@RequestParam(name = "name", required = false, defaultValue = "world") String name, Model model) {
		model.addAttribute("name",name);
		String activeEnvironement = "Pong -> "+ environement_key +" Environment" ;
		return activeEnvironement;
	}
	
}
