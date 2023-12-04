package com.cos.master.entities;

import lombok.Data;

@Data
public class ResponseObject {
	private String reason;
	private String status;
	private String stausCode;
	private int count;
	private Object data;
}
