package com.fw.wilco.api;


import java.util.List;

public class DashboardSymbolV3IO {

	public String id;
	@CollectionComponent(value=DashboardSymbolFunctionV3IO.class)
	public List<DashboardSymbolFunctionV3IO> functions;
	public String name;
	public Long autoFctId;
	public Boolean isTag;
	public String svg;
}
