package com.fw.wilco.api.access;

import java.util.logging.Level;


public class Logger {

	public static void info(String msg, Object... args) {
		java.util.logging.Logger.getLogger("API").log(Level.INFO, String.format(msg, args));
	}
	
	public static void warn(String msg, Object... args) {
		java.util.logging.Logger.getLogger("API").log(Level.WARNING, String.format(msg, args));
	}
	
	

}
