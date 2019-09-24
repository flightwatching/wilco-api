package com.fw.wilco.api;

import com.fw.wilco.api.constants.Severity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsStatsRequestV3IO {

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

    public static class Part {
        private String id;
        private Date from;
        private Date to;
        private List<String> regs;
        private List<String> tags;
        private List<Severity> severities;
        private List<Aggregate> aggregates;

        public Part notBefore(Date minDate) {
            this.from = minDate;
            return this;
        }

        public Part notAfter(Date maxDate) {
            this.to = maxDate;
            return this;
        }

        public Part withReg(String reg) {
            if (regs == null) {
                regs = new ArrayList<>();
            }
            regs.add(reg);
            return this;
        }

        public Part withTag(String tag) {
            if (tags == null) {
                tags = new ArrayList<>();
            }
            tags.add(tag);
            return this;
        }

        public Part withSeverity(Severity severity) {
            if (severities == null) {
                severities = new ArrayList<>();
            }
            severities.add(severity);
            return this;
        }

        public Part withAggregate(Aggregate aggregate) {
            if (aggregates == null) {
                aggregates = new ArrayList<>();
            }
            aggregates.add(aggregate);
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

        public Date getFrom() {
            return from;
        }

        public void setFrom(Date from) {
            this.from = from;
        }

        public Date getTo() {
            return to;
        }

        public void setTo(Date to) {
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
    }

    private List<Part> parts = new ArrayList<>();

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public void addPart(Part part) {
        parts.add(part);
    }
}
