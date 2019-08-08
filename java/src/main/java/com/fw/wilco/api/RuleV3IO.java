package com.fw.wilco.api;

public class RuleV3IO {
	
	private Long id;
	private Long db_id;
	private Boolean autoMap;
	private String svgElt;
	private Integer order;
	private String formula;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDb_id() {
		return db_id;
	}

	public void setDb_id(Long db_id) {
		this.db_id = db_id;
	}

	public Boolean getAutoMap() {
		return autoMap;
	}

	public void setAutoMap(Boolean autoMap) {
		this.autoMap = autoMap;
	}

	public String getSvgElt() {
		return svgElt;
	}

	public void setSvgElt(String svgElt) {
		this.svgElt = svgElt;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
