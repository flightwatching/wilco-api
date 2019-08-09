package com.fw.wilco.api;

import java.util.List;

public class IeiV3IO {

	private String code;

	private Long idxMin;
	private Long idxMax;

	private Long length;

	private String timeOffsetFormula;
	private String timeLabel;

	@CollectionComponent(value=IeiParamV3IO.class)
	private List<IeiParamV3IO> param;

	private Boolean isHeader;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getIdxMin() {
		return idxMin;
	}

	public void setIdxMin(Long idxMin) {
		this.idxMin = idxMin;
	}

	public Long getIdxMax() {
		return idxMax;
	}

	public void setIdxMax(Long idxMax) {
		this.idxMax = idxMax;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getTimeOffsetFormula() {
		return timeOffsetFormula;
	}

	public void setTimeOffsetFormula(String timeOffsetFormula) {
		this.timeOffsetFormula = timeOffsetFormula;
	}

	public String getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(String timeLabel) {
		this.timeLabel = timeLabel;
	}

	public List<IeiParamV3IO> getParam() {
		return param;
	}

	public void setParam(List<IeiParamV3IO> param) {
		this.param = param;
	}

	public Boolean getHeader() {
		return isHeader;
	}

	public void setHeader(Boolean header) {
		isHeader = header;
	}
}
