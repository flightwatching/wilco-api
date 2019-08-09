package com.fw.wilco.api;



public class SampleV3IO {

	private Long id;
	private String name;
	private String value;
	private Long paramId;
	private String type;
	@Deprecated
	private Double minOK;
	@Deprecated
	private Double minScale;
	@Deprecated
	private Double maxOK;
	@Deprecated
	private Double maxScale;
	private String state;
	private String date;
	private String timelabel;
	private Long msgId;
	private EventV3IO msg;
	private FwotV3IO fwot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getParamId() {
		return paramId;
	}

	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getMinOK() {
		return minOK;
	}

	public void setMinOK(Double minOK) {
		this.minOK = minOK;
	}

	public Double getMinScale() {
		return minScale;
	}

	public void setMinScale(Double minScale) {
		this.minScale = minScale;
	}

	public Double getMaxOK() {
		return maxOK;
	}

	public void setMaxOK(Double maxOK) {
		this.maxOK = maxOK;
	}

	public Double getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(Double maxScale) {
		this.maxScale = maxScale;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimelabel() {
		return timelabel;
	}

	public void setTimelabel(String timelabel) {
		this.timelabel = timelabel;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public EventV3IO getMsg() {
		return msg;
	}

	public void setMsg(EventV3IO msg) {
		this.msg = msg;
	}

	public FwotV3IO getFwot() {
		return fwot;
	}

	public void setFwot(FwotV3IO fwot) {
		this.fwot = fwot;
	}
}
