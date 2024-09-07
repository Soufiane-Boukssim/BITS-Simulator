package com.simulator.dtos.pos;

import com.simulator.entities.pos.PosCasesDefinitionId;
import com.simulator.entities.pos.PosCasesSetInfoId;

import java.util.List;

public record PosCaseSetInfoDTO(PosCasesSetInfoId id, String caseSetDesc, List<PosCasesDefinitionId> cases) { }
