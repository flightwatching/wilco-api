package com.fw.wilco.api;

public class FaultCodeV3IO {
	
	private EventV3IO event = new EventV3IO();
	private IftV3IO ift = new IftV3IO();

	public EventV3IO getEvent() {
		return event;
	}

	public void setEvent(EventV3IO event) {
		this.event = event;
	}

	public IftV3IO getIft() {
		return ift;
	}

	public void setIft(IftV3IO ift) {
		this.ift = ift;
	}
}
