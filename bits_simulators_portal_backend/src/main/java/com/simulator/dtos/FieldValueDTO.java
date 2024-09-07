package com.simulator.dtos;

import com.simulator.entities.FieldsDefinition;

import java.util.List;

public record FieldValueDTO(FieldsDefinition fieldDef, String value, List<Detail> details) { }