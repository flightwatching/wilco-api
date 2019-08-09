package com.fw.wilco.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fw.wilco.api.constants.HttpMethod;

public class LayoutV3IO {

	//ieiLayout
	@CollectionComponent(value=IeiV3IO.class)
	private List<IeiV3IO> ieis;
	private String engineType;
	private String layoutFMTs;
	private String layoutDBs;

	//uplinks
	@CollectionComponent(value=ParameterV3IO.class)
	private List<ParameterV3IO> params;

	private Long id;
	private String actype;
	private String dtype;
	@CollectionComponent(value=String.class)
	private ArrayList<String> dashboardIds;
	private String name;
	@CollectionComponent(value=DashboardV3IO.class)
	private List<DashboardV3IO> dashboards;
	private String reportId;
	private Boolean canUseAlphaCallUps;
	@CollectionComponent(value=IftV3IO.class)
	private ArrayList<IftV3IO> ifts;

	private String uplinkTemplate;

	private ExtSource extSource;

	public List<IeiV3IO> getIeis() {
		return ieis;
	}

	public void setIeis(List<IeiV3IO> ieis) {
		this.ieis = ieis;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getLayoutFMTs() {
		return layoutFMTs;
	}

	public void setLayoutFMTs(String layoutFMTs) {
		this.layoutFMTs = layoutFMTs;
	}

	public String getLayoutDBs() {
		return layoutDBs;
	}

	public void setLayoutDBs(String layoutDBs) {
		this.layoutDBs = layoutDBs;
	}

	public List<ParameterV3IO> getParams() {
		return params;
	}

	public void setParams(List<ParameterV3IO> params) {
		this.params = params;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActype() {
		return actype;
	}

	public void setActype(String actype) {
		this.actype = actype;
	}

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public ArrayList<String> getDashboardIds() {
		return dashboardIds;
	}

	public void setDashboardIds(ArrayList<String> dashboardIds) {
		this.dashboardIds = dashboardIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DashboardV3IO> getDashboards() {
		return dashboards;
	}

	public void setDashboards(List<DashboardV3IO> dashboards) {
		this.dashboards = dashboards;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Boolean getCanUseAlphaCallUps() {
		return canUseAlphaCallUps;
	}

	public void setCanUseAlphaCallUps(Boolean canUseAlphaCallUps) {
		this.canUseAlphaCallUps = canUseAlphaCallUps;
	}

	public ArrayList<IftV3IO> getIfts() {
		return ifts;
	}

	public void setIfts(ArrayList<IftV3IO> ifts) {
		this.ifts = ifts;
	}

	public String getUplinkTemplate() {
		return uplinkTemplate;
	}

	public void setUplinkTemplate(String uplinkTemplate) {
		this.uplinkTemplate = uplinkTemplate;
	}

	public ExtSource getExtSource() {
		return extSource;
	}

	public void setExtSource(ExtSource extSource) {
		this.extSource = extSource;
	}

	public static class ExtSource {
		private String varName;
		private String urlTemplate;
		private Integer poolPeriodSecond;
		private String webhook;
		private String login;
		private String password;
		private String bodyTemplate;
		private HttpMethod method;
		private Map<String, String> headers;

		public String getVarName() {
			return varName;
		}

		public void setVarName(String varName) {
			this.varName = varName;
		}

		public String getUrlTemplate() {
			return urlTemplate;
		}

		public void setUrlTemplate(String urlTemplate) {
			this.urlTemplate = urlTemplate;
		}

		public Integer getPoolPeriodSecond() {
			return poolPeriodSecond;
		}

		public void setPoolPeriodSecond(Integer poolPeriodSecond) {
			this.poolPeriodSecond = poolPeriodSecond;
		}

		public String getWebhook() {
			return webhook;
		}

		public void setWebhook(String webhook) {
			this.webhook = webhook;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getBodyTemplate() {
			return bodyTemplate;
		}

		public void setBodyTemplate(String bodyTemplate) {
			this.bodyTemplate = bodyTemplate;
		}

		public HttpMethod getMethod() {
			return method;
		}

		public void setMethod(HttpMethod method) {
			this.method = method;
		}

		public Map<String, String> getHeaders() {
			return headers;
		}

		public void setHeaders(Map<String, String> headers) {
			this.headers = headers;
		}
	}

}
