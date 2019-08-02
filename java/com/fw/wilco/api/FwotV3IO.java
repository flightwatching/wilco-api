package com.fw.wilco.api;

import java.util.Map;



public class FwotV3IO {
	
	private String reg;
	private String coolName;
	private String category;
	private String type;
	private String from;
	private String to;
	private String status;
	private String eta;
	private String etd; //TODO
	private EventV3IO alert; //TODO
	private String photoUrl;
	private String extUrl;
	private Long statusDashboard;

	private Map<String, String> properties;

	private Float lat;
	private Float lon;
	private Float alt;
	private String posDate;

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getCoolName() {
		return coolName;
	}

	public void setCoolName(String coolName) {
		this.coolName = coolName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getEtd() {
		return etd;
	}

	public void setEtd(String etd) {
		this.etd = etd;
	}

	public EventV3IO getAlert() {
		return alert;
	}

	public void setAlert(EventV3IO alert) {
		this.alert = alert;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getExtUrl() {
		return extUrl;
	}

	public void setExtUrl(String extUrl) {
		this.extUrl = extUrl;
	}

	public Long getStatusDashboard() {
		return statusDashboard;
	}

	public void setStatusDashboard(Long statusDashboard) {
		this.statusDashboard = statusDashboard;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public Float getAlt() {
		return alt;
	}

	public void setAlt(Float alt) {
		this.alt = alt;
	}

	public String getPosDate() {
		return posDate;
	}

	public void setPosDate(String posDate) {
		this.posDate = posDate;
	}
}
