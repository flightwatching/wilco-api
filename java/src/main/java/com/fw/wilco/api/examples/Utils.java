package com.fw.wilco.api.examples;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static Map<String, String> parseArgs(String[] args) {
		Map<String, String> ret = new HashMap<String, String>();
		for (String arg : args) {
			String[] split = arg.split("=");
			if (split.length==2) {
				ret.put(split[0], split[1]);
			} else if (split.length==1) {
				ret.put(split[0], null);
			}
		}
		return ret;
	}

}
