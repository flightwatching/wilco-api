package com.fw.wilco.api;


import java.util.List;

import com.fw.wilco.api.constants.Severity;

public class EventV3IO{


	private String id;
	private String klass;

	/**
	 *	@Deprecated
	 * Use the color. The severity is too linked to the business...
	 */
	@Deprecated
	private Severity severity;

	/**
	 * a json style object
	 */
	private String style;

	private String category;

	@CollectionComponent(value=TagV3IO.class)
	private List<TagV3IO> tags;

	private String transmissionDate;
	private String receptionDate;
	private String computedDate;
	private String station;
	private String network;
	private Boolean dateIsEstimated;

	private String phase;
	private String status;

	private String title;
	private String sumUp;
	private String description;
	private String data;

	private String reg;

	private Long layoutId;
	private Long lastUpdate;
	private String flightId;

	private String dismisser;
	private String comment;

	private String dataId;

	@CollectionComponent(value=String.class)
	private List<String> dashboardIds;
	private String from;
	private String to;
	@CollectionComponent(value=Long.class)
	private List<Long> analysis;

	private String extUrl;
	@Deprecated
	private Integer flightCount;

	private String sharingId;

	private Boolean visible;

	@CollectionComponent(value=SampleV3IO.class)
	private List<SampleV3IO> samples;
	private FwotV3IO fwot;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKlass() {
		return klass;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<TagV3IO> getTags() {
		return tags;
	}

	public void setTags(List<TagV3IO> tags) {
		this.tags = tags;
	}

	public String getTransmissionDate() {
		return transmissionDate;
	}

	public void setTransmissionDate(String transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	public String getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(String receptionDate) {
		this.receptionDate = receptionDate;
	}

	public String getComputedDate() {
		return computedDate;
	}

	public void setComputedDate(String computedDate) {
		this.computedDate = computedDate;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public Boolean getDateIsEstimated() {
		return dateIsEstimated;
	}

	public void setDateIsEstimated(Boolean dateIsEstimated) {
		this.dateIsEstimated = dateIsEstimated;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSumUp() {
		return sumUp;
	}

	public void setSumUp(String sumUp) {
		this.sumUp = sumUp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public Long getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(Long layoutId) {
		this.layoutId = layoutId;
	}

	public Long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getDismisser() {
		return dismisser;
	}

	public void setDismisser(String dismisser) {
		this.dismisser = dismisser;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public List<String> getDashboardIds() {
		return dashboardIds;
	}

	public void setDashboardIds(List<String> dashboardIds) {
		this.dashboardIds = dashboardIds;
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

	public List<Long> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<Long> analysis) {
		this.analysis = analysis;
	}

	public String getExtUrl() {
		return extUrl;
	}

	public void setExtUrl(String extUrl) {
		this.extUrl = extUrl;
	}

	public Integer getFlightCount() {
		return flightCount;
	}

	public void setFlightCount(Integer flightCount) {
		this.flightCount = flightCount;
	}

	public String getSharingId() {
		return sharingId;
	}

	public void setSharingId(String sharingId) {
		this.sharingId = sharingId;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public List<SampleV3IO> getSamples() {
		return samples;
	}

	public void setSamples(List<SampleV3IO> samples) {
		this.samples = samples;
	}

	public FwotV3IO getFwot() {
		return fwot;
	}

	public void setFwot(FwotV3IO fwot) {
		this.fwot = fwot;
	}
}
