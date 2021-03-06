package com.fw.wilco.api;


public class ErrorDao {
	public final int code;
	public final String msg;
	public final String url;	
	
	public ErrorDao(int code, String message, String url) {
		this.code = code;
		msg = message;
		this.url = url; 
	}
	
	public ErrorDao(int code, String message) {
		this(code, message, null);
	}
	
	public ErrorDao(int code) {
		this(code, null, null);
	}

	public String getMsg() {
		return msg;
	}

	public String getUrl() {
		return url;
	}

	public int getCode() {
		return code;
	}
}