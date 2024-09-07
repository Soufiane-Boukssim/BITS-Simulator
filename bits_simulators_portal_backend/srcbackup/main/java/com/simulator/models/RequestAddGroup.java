package com.simulator.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddGroup {
    String code;
    List<String> listOfMenus;
    String typeRole;

    public RequestAddGroup(String code, List<String> listOfMenus) {
        this.code = code;
        this.listOfMenus = listOfMenus;
    }
}
