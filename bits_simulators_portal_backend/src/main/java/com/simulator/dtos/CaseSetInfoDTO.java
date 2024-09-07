package com.simulator.dtos;

import com.simulator.entities.CasesDefinitionId;
import com.simulator.entities.CasesSetInfoId;

import java.util.List;

public record CaseSetInfoDTO(CasesSetInfoId id, String caseSetDesc, List<CasesDefinitionId> cases) { }
