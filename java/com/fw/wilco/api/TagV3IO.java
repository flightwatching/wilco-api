package com.fw.wilco.api;



public class TagV3IO {
	
	public TagV3IO(String id) {
		this.id = id;
	}
	
	
	public TagV3IO() {
	}

	public static final String MAIL = "IFT_MAIL";
	public static final String UPLINK = "IFT_UPLINK";
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
