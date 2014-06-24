package com.fw.wilco.api.examples;

import java.util.HashMap;

import com.fw.wilco.api.FwotV3IO;
import com.google.gson.Gson;

public class EncodeDecodeFwotV3IO {

	public static void main(String[] args) {
		FwotV3IO f = new FwotV3IO();
		f.reg = "TOTO";
		f.properties = new HashMap<String, String>();
		f.properties.put("p1", "val1");
		f.properties.put("p2", "val2");
		System.out.println(new Gson().toJson(f));		
		
		
		//decode
		String raw = "{\"reg\":\"TOTO\",\"hasPhoto\":false,\"properties\":{p2:\"val2\",p1:\"val1\"}}";
		FwotV3IO decode = new Gson().fromJson(raw, FwotV3IO.class);
		System.out.println(decode.properties);
	}

}
