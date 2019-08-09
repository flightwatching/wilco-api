package com.fw.wilco.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;


public class InputSampleV3IO {

	public InputSampleV3IO(String name, JsonElement value, String computedDate) {
		this.setName(name);
		this.setValue(value);
		setTimestamp(computedDate);
		
	}
	
	public InputSampleV3IO(String name, String value, String computedDate) {
		this(name, new Gson().toJsonTree(value), computedDate);
	}
	
	private String name;
	private JsonElement value;
	private String timestamp;
	private String timelabel;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result
				+ ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
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
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (getTimestamp() == null) {
			if (other.getTimestamp() != null)
				return false;
		} else if (!getTimestamp().equals(other.getTimestamp()))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String ret =  String.format("%s=%s", this.getName(), this.getValue());
		return ret;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JsonElement getValue() {
		return value;
	}

	public void setValue(JsonElement value) {
		this.value = value;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimelabel() {
		return timelabel;
	}

	public void setTimelabel(String timelabel) {
		this.timelabel = timelabel;
	}
}
