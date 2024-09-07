package com.simulator.iso.model;


import lombok.Data;

import java.util.Map;

@Data
public class ObjectReq {

    private String messageIn;
    private Map<String, String> fields;

    public String getFieldValue(String key) {
        return fields.get(key);
    }
}
