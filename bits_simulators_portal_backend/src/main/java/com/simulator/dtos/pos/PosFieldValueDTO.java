package com.simulator.dtos.pos;

import com.simulator.dtos.Detail;
import com.simulator.entities.pos.PosFieldsDefinition;

import java.util.List;

public record PosFieldValueDTO(PosFieldsDefinition fieldDef, String value, List<Detail> details) { }
