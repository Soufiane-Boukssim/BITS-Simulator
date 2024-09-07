package com.simulator.pos.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PosIsoFields {
    private Map<String, String> fields;

    public PosIsoFields() {
        this.fields = new HashMap<>();
    }

    public void setField(String fieldId, String value) {
        fields.put(fieldId, value);
    }

    public String getField(String fieldId) {
        return fields.get(fieldId);
    }

    public Map<String, String> getAllFields() {
        return fields;
    }

    // Optional: Method to display all fields and their values
    public void displayFields() {
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            System.out.println("Field " + entry.getKey() + ": " + entry.getValue());
        }
    }

    // Method to convert fields to JSON
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this.fields);
    }

    // Method to load fields from JSON
    public void fromJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        this.fields = gson.fromJson(json, type);
    }

}
