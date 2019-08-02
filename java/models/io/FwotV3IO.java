package models.io;

import java.util.Map;



public class FwotV3IO {

	public String reg;
	public String coolName;
	public String category;
	public String type;
	public String from;
	public String to;
	public String status;
	public String eta;
	public String etd; //TODO
	public EventV3IO alert; //TODO
	public String photoUrl;
	public String extUrl;
	public Long statusDashboard;

	public Map<String, String> properties;

	public Float lat;
	public Float lon;
	public Float alt;
	public String posDate;
}
