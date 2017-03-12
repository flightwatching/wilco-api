package com.fw.wilco.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LayoutV3IO {
	
	public static class ExtSource {
		public String varName;
		public String urlTemplate;
		public Integer poolPeriodSecond;
		public String webhook;
		public String user;
		public String password;
		public Map<String, String> headers;
	}
	
	//ieiLayout
	public List<IeiV3IO> ieis;
	public String engineType;
	public String layoutFMTs;
	public String layoutDBs;
	
	//uplinks
	public List<ParameterV3IO> params;
	
	public Long id;
	public String actype;
	public String dtype;
	public ArrayList<String> dashboardIds;
	public String name;
	public List dashboards;
	public String reportId;
	public Boolean canUseAlphaCallUps;
	public ArrayList<IftV3IO> ifts;
		
	public String uplinkTemplate;
	
	public ExtSource extSource;

}
