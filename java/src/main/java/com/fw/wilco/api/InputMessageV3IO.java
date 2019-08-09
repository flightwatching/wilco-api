package com.fw.wilco.api;


import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import com.fw.wilco.api.constants.FlightStatus;
import com.fw.wilco.api.constants.SMI;
import com.fw.wilco.api.constants.Severity;
import com.google.gson.JsonElement;

@Deprecated
/**
 * we will have to merge it with the EventV3IO
 * @author dao
 *
 */
public class InputMessageV3IO {

	/**
	 * date formatter and parser from and to pattern "yyyy-MM-dd'T'HH:mm:ss"
	 */
	public final static SimpleDateFormat ISO8601DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");


	private String reg;

	private String sharingId;

	@CollectionComponent(value=FaultCodeV3IO.class)
	private List<FaultCodeV3IO> faultcodes;

	private Long id;

	private String computedDate;
	private FlightStatus status;

	private String sumup;
	private String title;
	private String extUrl;
	private Severity severity;


	private String style;

	@CollectionComponent(value=String.class)
	private HashSet<String> tags;
	private SMI smi;
	@CollectionComponent(value=InputSampleV3IO.class)
	private HashSet<InputSampleV3IO> samples;
	private String flightId;
	private String from;
	private String to;
	private String provider;
	private Boolean visible;

	private String namedLayout;

	private Long layoutId;



	public InputSampleV3IO insertSample(String name, String value, String d) {
		if (getSamples() ==null) {
			setSamples(new HashSet<InputSampleV3IO>());
		}
		InputSampleV3IO ret = new InputSampleV3IO(name, value, d);
		if (!this.getSamples().add(ret)) {
			System.err.println(String.format("samples for %s overlaps at %s", name, d));
		}
		return ret;
	}

	public InputSampleV3IO insertSample(String name, String value) {
		return insertSample(name, value, this.getComputedDate());
	}



	public InputSampleV3IO insertSample(String name, JsonElement value, String d) {
		if (getSamples() ==null) {
			setSamples(new HashSet<InputSampleV3IO>());
		}
		InputSampleV3IO ret = new InputSampleV3IO(name, value, d);
		if (!this.getSamples().add(ret)) {
			System.err.println(String.format("samples for %s overlaps at %s", name, d));
		}
		return ret;
	}

	public InputSampleV3IO insertSample(String name, JsonElement value) {
		return insertSample(name, value, this.getComputedDate());
	}

	@Override
	public String toString() {
		String ret =  String.format("%s (%s) @ %s", this.getNamedLayout(), this.getSumup(), this.getComputedDate());
		for (InputSampleV3IO s : this.getSamples()) {
			ret+="\n  "+s.toString();
		}
		return ret;
	}


	/**
	 * the aircraft registration. it is the tail number
	 * The aircraft has to be known in the server
	 * MANDATORY
	 */
	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getSharingId() {
		return sharingId;
	}

	public void setSharingId(String sharingId) {
		this.sharingId = sharingId;
	}

	/**
	 * Where to put the analysis that are linked to the message
	 */
	public List<FaultCodeV3IO> getFaultcodes() {
		return faultcodes;
	}

	public void setFaultcodes(List<FaultCodeV3IO> faultcodes) {
		this.faultcodes = faultcodes;
	}

	/**
	 * the ID of the message. If you want to update a message, use the field, else
	 * leave it null or missing
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * the computed date of the message, in ISO8601 format, eg. 2014-05-23T13:45:23
	 * it is UTC time
	 * MANDATORY
	 */
	public String getComputedDate() {
		return computedDate;
	}

	public void setComputedDate(String computedDate) {
		this.computedDate = computedDate;
	}

	/**
	 * the status of the aircraft at the moment of the message computed date
	 */
	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	/**
	 * a text to describe the message (<1500 characters)
	 */
	public String getSumup() {
		return sumup;
	}

	public void setSumup(String sumup) {
		this.sumup = sumup;
	}

	/**
	 * a short title (40 char max)
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * an external url to the resource
	 */
	public String getExtUrl() {
		return extUrl;
	}

	public void setExtUrl(String extUrl) {
		this.extUrl = extUrl;
	}

	/**
	 * The severity of the message
	 */
	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	/**
	 * a json format to store the style of the message
	 */
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * a list of tags to tag the message
	 */
	public HashSet<String> getTags() {
		return tags;
	}

	public void setTags(HashSet<String> tags) {
		this.tags = tags;
	}

	/**
	 * a category of message. The SMI can be in the ACARS specification.
	 * If the message is not acars, then SMI.FW can be used
	 */
	public SMI getSmi() {
		return smi;
	}

	public void setSmi(SMI smi) {
		this.smi = smi;
	}

	/**
	 * the list of samples attached to the message
	 */
	public HashSet<InputSampleV3IO> getSamples() {
		return samples;
	}

	public void setSamples(HashSet<InputSampleV3IO> samples) {
		this.samples = samples;
	}

	/**
	 * the flight Id of the message, if attached to a flight
	 */
	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/**
	 * the airport departure ICAO code (4 characters)
	 */
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * the airport destination ICAO code (4 characters)
	 */
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * the application provider key
	 */
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * if set and false, the message will not be viewable by the user. The message is only here for IFT purposes
	 * In that case, WILCO will not ensure the persistency forever
	 */
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	/**
	 * the name of the layout that matches the message. The name is the name as it appears on the server.
	 * if it is not provided, no rule or dashboard can be attached, but the message is still inserted
	 * if the layoutId is provided, this is ignored
	 */
	public String getNamedLayout() {
		return namedLayout;
	}

	public void setNamedLayout(String namedLayout) {
		this.namedLayout = namedLayout;
	}

	/**
	 * the id of the layout that matches the message.
	 * if it is not provided, namedLayout is tried
	 */
	public Long getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(Long layoutId) {
		this.layoutId = layoutId;
	}
}
