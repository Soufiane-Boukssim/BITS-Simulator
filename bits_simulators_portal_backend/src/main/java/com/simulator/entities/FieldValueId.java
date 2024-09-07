package com.simulator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldValueId implements Serializable {
    private CasesDefinition caseDef;
    private FieldsDefinition fieldDef;
}