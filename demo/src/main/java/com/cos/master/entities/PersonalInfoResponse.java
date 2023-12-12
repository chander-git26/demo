package com.cos.master.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonalInfoResponse {
	private Integer id;
	private String address;
	private String gender;
	private String dateofbirth;
	private String state;
	private String country;
	private String marital_status;
	private String blood_Group;
	private int height;
	private int weight;
	private String smoking;
	private String alcohol;

}
