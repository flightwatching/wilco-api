package com.fw.wilco.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsStatsResponseV3IO {

    public static final class Result extends HashMap<String, Object> {

        public Result withAggregate(String aggregate) {
            if(containsKey(aggregate)) {
                return (Result) get(aggregate);
            } else {
                Result obj = new Result();
                put(aggregate, obj);
                return obj;
            }
        }

        public void setValue(BigDecimal value) {
            put("value", value);
        }

        public Double getValue() {
            return (Double)get("value");
        }
    }

    public static final class Part {
        private String id;
        private Result result;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }

    private List<Part> parts = new ArrayList<>();

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public Part getPartById(String id) {
        for(Part part : parts) {
            if (id.equals(part.id)) {
                return part;
            }
        }
        return null;
    }
}
