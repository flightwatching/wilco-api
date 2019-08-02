package models.io;

import com.google.gson.Gson;


public class WSMessageV3IO {
	
	public static final String DELETE = "DELETE";
	public static final String UPDATE = "UPDATE";
	public static final String CREATE = "CREATE";
	
	private static final String EVENT = EventV3IO.class.getSimpleName();
	

	public String reg;
	public String create;
	public String update;
	public String delete;
	public String type;
	


	public WSMessageV3IO(String type, String reg, String create, String update, String delete) {
		this.type = type;
		this.reg = reg;
		this.create=create;
		this.update = update;
		this.delete = delete;
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


}
