package com.fw.wilco.api;

import com.google.gson.Gson;


public class WSMessageV3IO {
	
	public static final String DELETE = "DELETE";
	public static final String UPDATE = "UPDATE";
	public static final String CREATE = "CREATE";
	
	private static final String EVENT = EventV3IO.class.getSimpleName();
	

	public Long create;
	public Long update;
	public Long delete;
	public String type;
	


	public WSMessageV3IO(String type, Long create, Long update, Long delete) {
		this.type = type;
		this.create=create;
		this.update = update;
		this.delete = delete;
	}
	
	


	@Override
	public String toString() {
		return new Gson().toJson(this);
	}




	public static WSMessageV3IO update(String type, Long id) {
		return new WSMessageV3IO(type, null, id, null);
	}

	public static WSMessageV3IO create(String type, Long id) {
		return new WSMessageV3IO(type, id, null, null);
	}

	public static WSMessageV3IO delete(String type, Long id) {
		return new WSMessageV3IO(type, null, null, id);
	}


}
