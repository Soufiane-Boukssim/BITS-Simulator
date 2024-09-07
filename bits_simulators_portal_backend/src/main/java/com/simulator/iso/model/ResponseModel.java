package com.simulator.iso.model;

import com.simulator.entities.CasesDefinitionId;
import com.simulator.entities.CasesSetInfoId;
import lombok.Data;

@Data
public class ResponseModel {
    private String header;
    private CasesDefinitionId id;
    private CasesSetInfoId scenario;
    private String messageOut;
    private String messageIn;
    private int index;
}
