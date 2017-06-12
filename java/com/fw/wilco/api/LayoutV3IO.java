package com.fw.wilco.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fw.wilco.api.constants.HttpMethod;

public class LayoutV3IO {

	public static class ExtSource {
		public String varName;
		public String urlTemplate;
		public Integer poolPeriodSecond;
		public String webhook;
		public String login;
		public String password;
		public HttpMethod method;
		public Map<String, String> headers;
	}

	//ieiLayout
	@CollectionComponent(value=IeiV3IO.class)
	public List<IeiV3IO> ieis;
	public String engineType;
	public String layoutFMTs;
	public String layoutDBs;

	//uplinks
	@CollectionComponent(value=ParameterV3IO.class)
	public List<ParameterV3IO> params;

	public Long id;
	public String actype;
	public String dtype;
	@CollectionComponent(value=String.class)
	public ArrayList<String> dashboardIds;
	public String name;
	@CollectionComponent(value=DashboardV3IO.class)
	public List<DashboardV3IO> dashboards;
	public String reportId;
	public Boolean canUseAlphaCallUps;
	@CollectionComponent(value=IftV3IO.class)
	public ArrayList<IftV3IO> ifts;

	public String uplinkTemplate;

	public ExtSource extSource;

}
