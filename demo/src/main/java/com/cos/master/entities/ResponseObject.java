package com.cos.master.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseObject {
	private String reason;
	private String status;
	private String stausCode;
	private int count;
	private Object data;
}
