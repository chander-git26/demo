package com.cos.master.entities;

import java.io.Serializable;
import java.sql.Blob;
import jakarta.persistence.Lob;
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

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String firstname;
	private String lastname;
	@Column(name = "user_id")
	private String userId;

	private String mobile;
	private String password;

	private String email;
	private String otp;
	@Column(name = "created_date")
	private LocalDate createdDate;
	@Column(name = "modified_date")
	private LocalDate modifieddDate;
	
	@Lob
    private Blob profile;
}
