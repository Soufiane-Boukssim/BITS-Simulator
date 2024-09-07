package com.simulator.iso.model;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Field11Storage {
    private final Map<String, String> userCodeField11Map = new ConcurrentHashMap<>();

    public void saveField11(String userCode, String field11) {
        userCodeField11Map.put(userCode, field11);
    }

    public String getField11(String userCode) {
        return userCodeField11Map.get(userCode);
    }

    public void removeField11(String userCode) {
        userCodeField11Map.remove(userCode);
    }
}
