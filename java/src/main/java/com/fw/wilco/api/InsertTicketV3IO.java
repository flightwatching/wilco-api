package com.fw.wilco.api;

import java.util.List;

/**
 * Created by dao on 31/07/2018.
 */
public class InsertTicketV3IO {
    private Long id;
    private List<Long> events;
    private boolean isFinished;
    private String begin;
    private String end;
    private Long eventCount;
    private boolean notifyExternal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getEvents() {
        return events;
    }

    public void setEvents(List<Long> events) {
        this.events = events;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Long getEventCount() {
        return eventCount;
    }

    public void setEventCount(Long eventCount) {
        this.eventCount = eventCount;
    }

    public boolean isNotifyExternal() {
        return notifyExternal;
    }

    public void setNotifyExternal(boolean notifyExternal) {
        this.notifyExternal = notifyExternal;
    }
}
