package com.cos.master.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FamilyResponse {
	private Integer id;
	private String father_name;
	private Integer age;
	private String father_occupation;
	private String father_medical_history;
	private String mother_name;
	private Integer mother_age;
	private String mother_occupation;
	private String mother_medical_history;
	private String spouse_name;
	private Integer spouse_age;
	private String spouse_occupation;
	private String spouse_medical_history;
	private String nominee1_name;
	private String nominee2_name;
	private String other_nominee_name;
	private Integer other_nominee_age;
	private String other_nominee_relationship;
}
