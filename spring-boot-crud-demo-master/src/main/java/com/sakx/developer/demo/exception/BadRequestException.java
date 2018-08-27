package com.sakx.developer.demo.exception;



public class BadRequestException extends Exception {

	private static final long serialVersionUID = 100L;
	
	private String code;
	
	public BadRequestException(String code, String reason){
		super(String.format("%s: %s",  code, reason));
		this.code=code;
	}
	
	public String getCode() {
		return code;
	}

}
