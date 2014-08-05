package com.fw.wilco.api;


import java.text.SimpleDateFormat;
import java.util.HashSet;

import com.fw.wilco.api.constants.FlightStatus;
import com.fw.wilco.api.constants.SMI;
import com.fw.wilco.api.constants.Severity;
import com.google.gson.JsonElement;

public class InputMessageV3IO {
	
	/**
	 * date formatter and parser from and to pattern "yyyy-MM-dd'T'HH:mm:ss"
	 */
	public final static SimpleDateFormat ISO8601DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	
	/**
	 * the aircraft registration. it is the tail number
	 * The aircraft has to be known in the server
	 * MANDATORY
	 */
	public String reg;
	
	/**
	 * the computed date of the message, in ISO8601 format, eg. 2014-05-23T13:45:23
	 * it is UTC time
	 * MANDATORY
	 */
	public String computedDate;
	/**
	 * the status of the aircraft at the moment of the message computed date
	 */
	public FlightStatus status;

	/**
	 * a text to describe the message (<1500 characters) 
	 */
	public String sumup;
	/**
	 * a short title (40 char max) 
	 */
	public String title;
	/**
	 * an external url to the resource
	 */
	public String extUrl;
	/**
	 * The severity of the message
	 */
	public Severity severity;
	/**
	 * a list of tags to tag the message
	 */
	public HashSet<String> tags;
	/**
	 * a category of message. The SMI can be in the ACARS specification.
	 * If the message is not acars, then SMI.FW can be used
	 */
	public SMI smi;
	/**
	 * the list of samples attached to the message
	 */
	public HashSet<InputSampleV3IO> samples;
	/**
	 * the flight Id of the message, if attached to a flight
	 */
	public String flightId;
	/**
	 * the airport departure ICAO code (4 characters)
	 */
	public String from;
	/**
	 * the airport destination ICAO code (4 characters)
	 */
	public String to;
	/**
	 * the application provider key
	 */
	public String provider;
	
	/**
	 * the name of the layout that matches the message. The name is the name as it appears on the server.
	 * if it is not provided, no rule or dashboard can be attached, but the message is still inserted
	 * if the layoutId is provided, this is ignored
	 */
	public String namedLayout;
	
	/**
	 * the id of the layout that matches the message. 
	 * if it is not provided, namedLayout is tried
	 */
	public Long layoutId;
	
	
	public InputSampleV3IO insertSample(String name, String value) {
		if (samples==null) {
			samples = new HashSet<InputSampleV3IO>();
		}
		InputSampleV3IO ret = new InputSampleV3IO(name, value, this.computedDate);
		this.samples.add(ret);
		return ret;
	}
	
	public InputSampleV3IO insertSample(String name, JsonElement value) {
		if (samples==null) {
			samples = new HashSet<InputSampleV3IO>();
		}
		InputSampleV3IO ret = new InputSampleV3IO(name, value, this.computedDate);
		this.samples.add(ret);
		return ret;
	}

	@Override
	public String toString() {
		String ret =  String.format("%s (%s) @ %s", this.namedLayout, this.sumup, this.computedDate);
		for (InputSampleV3IO s : this.samples) {
			ret+="\n  "+s.toString();
		}
		return ret;
	}

	

}
