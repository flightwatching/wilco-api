package com.fw.wilco.api;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EventsStatsResponsePartV3IO {

    public static final Type GSON_LIST_TYPE = new TypeToken<ArrayList<EventsStatsResponsePartV3IO>>() {}.getType();

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

    public static EventsStatsResponsePartV3IO getPartById(List<EventsStatsResponsePartV3IO> parts, String id) {
        for(EventsStatsResponsePartV3IO part : parts) {
            if (id.equals(part.id)) {
                return part;
            }
        }
        return null;
    }
}
