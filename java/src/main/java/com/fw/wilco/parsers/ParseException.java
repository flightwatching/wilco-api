package com.fw.wilco.parsers;

public class ParseException extends Exception {

	public String field;

	public ParseException(String field, String txt, Throwable arg1) {
		super(txt, arg1);
		this.field = field;
	}

	public ParseException(String field, String txt) {
		super(txt);
		this.field = field;
	}
	
	

}
