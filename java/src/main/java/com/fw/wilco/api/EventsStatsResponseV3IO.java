package com.fw.wilco.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventsStatsResponseV3IO {

    public static final class Part {
        private String id;
        private Map<String, Object> result = new HashMap<>();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, Object> getResult() {
            return result;
        }

        public void setResult(Map<String, Object> result) {
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
