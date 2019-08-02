package models.io.access;

import models.io.ErrorDao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ApiException extends Exception {

	private int httpCode;
	private ErrorDao json;

	public ApiException(int httpCode, String response) {
		this.httpCode = httpCode;
		if (response==null) {
			response = "";
		}
		try {
			json = new Gson().fromJson(response, ErrorDao.class);			
		} catch (JsonSyntaxException e) {
			;
		}
	}

	@Override
	public String toString() {
		return String.format("%d: %s", httpCode, json.msg);
	}
	
	

}
