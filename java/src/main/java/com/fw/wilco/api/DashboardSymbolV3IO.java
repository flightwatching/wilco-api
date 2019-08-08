package com.fw.wilco.api;


import java.util.List;

public class DashboardSymbolV3IO {

	private String id;
	@CollectionComponent(value=DashboardSymbolFunctionV3IO.class)
	private List<DashboardSymbolFunctionV3IO> functions;
	private String name;
	private Long autoFctId;
	private Boolean isTag;
	private String svg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DashboardSymbolFunctionV3IO> getFunctions() {
		return functions;
	}

	public void setFunctions(List<DashboardSymbolFunctionV3IO> functions) {
		this.functions = functions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAutoFctId() {
		return autoFctId;
	}

	public void setAutoFctId(Long autoFctId) {
		this.autoFctId = autoFctId;
	}

	public Boolean getTag() {
		return isTag;
	}

	public void setTag(Boolean tag) {
		isTag = tag;
	}

	public String getSvg() {
		return svg;
	}

	public void setSvg(String svg) {
		this.svg = svg;
	}
}
