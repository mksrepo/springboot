package com.sakx.developer.demo.exception;

public class ArticleNotFoundException extends Exception {

	private static final long serialVersionUID = 100L;

	private String code;

	public ArticleNotFoundException(String code, String reason) {
		super(String.format("%s: %s",  code, reason));
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
