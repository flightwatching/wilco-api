package com.fw.wilco.api;


import java.util.List;

import com.fw.wilco.api.constants.Severity;

public class EventV3IO{
	
	
	public String id;
	public String klass;
	
	public Severity severity;

	public String category;
	public List<TagV3IO> tags;
	
	public String transmissionDate;
	public String receptionDate;
	public String computedDate;
	public String station;
	public String network;
	public Boolean dateIsEstimated;
	
	public String phase;
	public String status;

	public String title;
	public String sumUp;
	public String data;

	public String reg;
	
	public Long layoutId;
	public Long lastUpdate;
	public String flightId;
	
	public String dismisser;
	public String comment;
	
	public String dataId;
	
	public List<String> dashboardIds;
	public String from;
	public String to;
	public List<Long> analysis;
	
	public String extUrl;
	@Deprecated
	public Integer flightCount;
	
	public String sharingId;
	
	public Boolean visible;
	
	public List<SampleV3IO> samples;
	public FwotV3IO fwot;
	
}
