package com.fw.wilco.api;

import com.google.gson.Gson;


public class WSMessageV3IO {
	
	public static final String DELETE = "DELETE";
	public static final String UPDATE = "UPDATE";
	public static final String CREATE = "CREATE";
	
	private static final String EVENT = EventV3IO.class.getSimpleName();
	

	private String reg;
	private String create;
	private String update;
	private String delete;
	private String type;
	


	public WSMessageV3IO(String type, String reg, String create, String update, String delete) {
		this.setType(type);
		this.setReg(reg);
		this.setCreate(create);
		this.setUpdate(update);
		this.setDelete(delete);
	}
	
	


	@Override
	public String toString() {
		return new Gson().toJson(this);
	}




	public static WSMessageV3IO update(String type, String reg, Long id) {
		return new WSMessageV3IO(type, reg, null, id.toString(), null);
	}

	public static WSMessageV3IO create(String type, String reg, Long id) {
		return new WSMessageV3IO(type, reg, id.toString(), null, null);
	}

	public static WSMessageV3IO delete(String type, String reg, Long id) {
		return new WSMessageV3IO(type, reg, null, null, id.toString());
	}


	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
