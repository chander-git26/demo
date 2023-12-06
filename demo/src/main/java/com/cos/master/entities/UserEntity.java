package com.cos.master.entities;

import java.io.Serializable;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "user_info")
public class UserEntity implements Serializable{
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;
	private String firstname;
	private String lastname;
	@Column(name = "user_id")
	private String userId;
	private String gender;
	private String dateofbirth;
	private String mobile;
	private String password;
	private String email;
	private String address;
	private String state;
	private String country;
	private String zipcode;
	
//	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private LocalDate createdDate;
	
//	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private LocalDate modifieddDate;
}
