package com.fw.wilco.api;


import java.util.List;
import java.util.Map;
import java.util.Set;

public class DashboardV3IO  {

	private String id;
	@CollectionComponent(value=RuleV3IO.class)
	private List<RuleV3IO> rules;
	private String svg;
	private Map<String, String> eltMap;
	private String name;

	@CollectionComponent(value=DashboardSymbolV3IO.class)
	private Set<DashboardSymbolV3IO> symbols;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<RuleV3IO> getRules() {
		return rules;
	}

	public void setRules(List<RuleV3IO> rules) {
		this.rules = rules;
	}

	public String getSvg() {
		return svg;
	}

	public void setSvg(String svg) {
		this.svg = svg;
	}

	public Map<String, String> getEltMap() {
		return eltMap;
	}

	public void setEltMap(Map<String, String> eltMap) {
		this.eltMap = eltMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<DashboardSymbolV3IO> getSymbols() {
		return symbols;
	}

	public void setSymbols(Set<DashboardSymbolV3IO> symbols) {
		this.symbols = symbols;
	}
}
