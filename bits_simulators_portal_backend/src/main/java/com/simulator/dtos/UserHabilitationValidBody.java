package com.simulator.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHabilitationValidBody {
    private String userCode;
    private List<String> menuName;
    private List<String> protocols;
    private String protocol;
}
