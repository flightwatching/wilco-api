package com.fw.wilco.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LayoutV3IO {
	
	public static class ExtSource {
		public String varName;
		public String urlTemplate;
		public Integer poolPeriodSecond;
	}

	public Long id;
	public String type;
	public ArrayList<String> dashboardIds;
	public String name;
	public List dashboards;
	public String reportId;
	public Boolean canUseAlphaCallUps;
	public ArrayList<RuleV3IO> ifts;
	
	public ExtSource extSource;

}
