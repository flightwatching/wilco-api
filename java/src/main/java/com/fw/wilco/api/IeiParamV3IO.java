package com.fw.wilco.api;

public class IeiParamV3IO {
	
	private boolean isNumber;
	private Long offset;
	private Long length;
	private Integer idx;
	private String sampleValue;
	private Double conversion;
	private ParameterV3IO param;
	private String dateParam;
	private String datePattern;
	private String formula;

	public boolean isNumber() {
		return isNumber;
	}

	public void setNumber(boolean number) {
		isNumber = number;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getSampleValue() {
		return sampleValue;
	}

	public void setSampleValue(String sampleValue) {
		this.sampleValue = sampleValue;
	}

	public Double getConversion() {
		return conversion;
	}

	public void setConversion(Double conversion) {
		this.conversion = conversion;
	}

	public ParameterV3IO getParam() {
		return param;
	}

	public void setParam(ParameterV3IO param) {
		this.param = param;
	}

	public String getDateParam() {
		return dateParam;
	}

	public void setDateParam(String dateParam) {
		this.dateParam = dateParam;
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
}
