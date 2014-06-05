package com.fw.wilco.api;


public class InputSampleV3IO {

	public InputSampleV3IO(String name, String value, String computedDate) {
		this.name = name;
		this.value = value;
		timestamp = computedDate;
		
	}
	
	public String name;
	public String value;
	public String timestamp;
	public String timelabel;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputSampleV3IO other = (InputSampleV3IO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
	
}
