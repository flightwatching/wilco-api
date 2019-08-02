package models.io;


import java.util.List;

import models.io.constants.Severity;

public class EventV3IO{


	public String id;
	public String klass;

	/**
	 *	@Deprecated
	 * Use the color. The severity is too linked to the business...
	 */
	@Deprecated
	public Severity severity;

	/**
	 * a json style object
	 */
	public String style;

	public String category;

	@CollectionComponent(value=TagV3IO.class)
	public List<TagV3IO> tags;

	public String transmissionDate;
	public String receptionDate;
	public String computedDate;
	public String station;
	public String network;
	public Boolean dateIsEstimated;

	public String phase;
	public String status;

	public String title;
	public String sumUp;
	public String description;
	public String data;

	public String reg;

	public Long layoutId;
	public Long lastUpdate;
	public String flightId;

	public String dismisser;
	public String comment;

	public String dataId;

	@CollectionComponent(value=String.class)
	public List<String> dashboardIds;
	public String from;
	public String to;
	@CollectionComponent(value=Long.class)
	public List<Long> analysis;

	public String extUrl;
	@Deprecated
	public Integer flightCount;

	public String sharingId;

	public Boolean visible;

	@CollectionComponent(value=SampleV3IO.class)
	public List<SampleV3IO> samples;
	public FwotV3IO fwot;

}
