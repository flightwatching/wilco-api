package com.fw.wilco.api;


public class UpdateEventV3IO {
	
	private String dismisser;
	private String comment;
	private Integer surveillence;
	private String[] checklists;

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

	public Integer getSurveillence() {
		return surveillence;
	}

	public void setSurveillence(Integer surveillence) {
		this.surveillence = surveillence;
	}

	public String[] getChecklists() {
		return checklists;
	}

	public void setChecklists(String[] checklists) {
		this.checklists = checklists;
	}
}
