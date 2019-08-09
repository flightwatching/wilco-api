package com.fw.wilco.api;


import java.util.List;

public class DashboardSymbolBundleV3IO {
	
	private List<DashboardV3IO> dashboards;
	private List<DashboardSymbolV3IO> symbols;

	public List<DashboardV3IO> getDashboards() {
		return dashboards;
	}

	public void setDashboards(List<DashboardV3IO> dashboards) {
		this.dashboards = dashboards;
	}

	public List<DashboardSymbolV3IO> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<DashboardSymbolV3IO> symbols) {
		this.symbols = symbols;
	}
}
