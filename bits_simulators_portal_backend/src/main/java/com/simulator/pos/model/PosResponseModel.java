package com.simulator.pos.model;

import com.simulator.entities.pos.PosCasesDefinitionId;
import com.simulator.entities.pos.PosCasesSetInfoId;
import lombok.Data;

@Data
public class PosResponseModel {
    private String header;
    private PosCasesDefinitionId id;
    private PosCasesSetInfoId scenario;
    private String messageOut;
    private String messageIn;
    private int index;
}
