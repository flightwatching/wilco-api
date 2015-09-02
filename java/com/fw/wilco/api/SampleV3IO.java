package com.fw.wilco.api;



public class SampleV3IO {
	
	public Long id;
	public String name;
	public String value;
	public Long paramId;
	@Deprecated
	public Double minOK;
	@Deprecated
	public Double minScale;
	@Deprecated
	public Double maxOK;
	@Deprecated
	public Double maxScale;
	public String state;
	public String date;
	public String timelabel;
	public Long msgId;
	public EventV3IO msg;
	public FwotV3IO fwot;
}
