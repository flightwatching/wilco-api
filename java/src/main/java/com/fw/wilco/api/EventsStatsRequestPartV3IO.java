package com.fw.wilco.api;

import com.fw.wilco.api.constants.Severity;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EventsStatsRequestPartV3IO {

    public static final Type GSON_LIST_TYPE = new TypeToken<ArrayList<EventsStatsRequestPartV3IO>>() {}.getType();

    public static enum AggregateKind {
        REG,
        TAG,
        SEVERITY
    }

    public static class Aggregate {
        private AggregateKind kind;

        public static Aggregate byReg() {
            return new Aggregate(AggregateKind.REG);
        }

        public static Aggregate byTag() {
            return new Aggregate(AggregateKind.TAG);
        }

        public static Aggregate bySeverity() {
            return new Aggregate(AggregateKind.SEVERITY);
        }

        public Aggregate(AggregateKind kind) {
            this.kind = kind;
        }

        public AggregateKind getKind() {
            return kind;
        }

        public void setKind(AggregateKind kind) {
            this.kind = kind;
        }
    }

    private String id;
    private String from;
    private String to;
    private List<String> regs;
    private List<String> tags;
    private List<Severity> severities;
    private List<Aggregate> aggregates;
    private Boolean visible;
    private Boolean dismissed;

    public EventsStatsRequestPartV3IO withId(String id) {
        this.id = id;
        return this;
    }

    public EventsStatsRequestPartV3IO notBefore(String minDate) {
        this.from = minDate;
        return this;
    }

    public EventsStatsRequestPartV3IO notAfter(String maxDate) {
        this.to = maxDate;
        return this;
    }

    public EventsStatsRequestPartV3IO withReg(String reg) {
        if (regs == null) {
            regs = new ArrayList<>();
        }
        regs.add(reg);
        return this;
    }

    public EventsStatsRequestPartV3IO withTag(String tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        tags.add(tag);
        return this;
    }

    public EventsStatsRequestPartV3IO withSeverity(Severity severity) {
        if (severities == null) {
            severities = new ArrayList<>();
        }
        severities.add(severity);
        return this;
    }

    public EventsStatsRequestPartV3IO withAggregate(Aggregate aggregate) {
        if (aggregates == null) {
            aggregates = new ArrayList<>();
        }
        aggregates.add(aggregate);
        return this;
    }

    public EventsStatsRequestPartV3IO withVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public EventsStatsRequestPartV3IO withDismissed(Boolean dismissed) {
        this.dismissed = dismissed;
        return this;
    }

    public boolean hasRegs() {
        return regs != null && !regs.isEmpty();
    }

    public boolean hasTags() {
        return tags != null && !tags.isEmpty();
    }

    public boolean hasSeverities() {
        return severities != null && !severities.isEmpty();
    }

    public boolean hasAggregates() {
        return aggregates != null && !aggregates.isEmpty();
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

    public List<String> getRegs() {
        return regs;
    }

    public void setRegs(List<String> regs) {
        this.regs = regs;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Severity> getSeverities() {
        return severities;
    }

    public void setSeverities(List<Severity> severities) {
        this.severities = severities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Aggregate> getAggregates() {
        return aggregates;
    }

    public void setAggregates(List<Aggregate> aggregates) {
        this.aggregates = aggregates;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Boolean getDismissed() {
        return dismissed;
    }
}
