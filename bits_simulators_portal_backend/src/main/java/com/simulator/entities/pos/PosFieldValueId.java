package com.simulator.entities.pos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosFieldValueId implements Serializable {
    private PosCasesDefinition caseDef;
    private PosFieldsDefinition fieldDef;
}
