package com.simulator.dtos.pos;

import com.simulator.entities.pos.PosCasesDefinitionId;

import java.util.List;

public record PosCasesDefinitionDTO(
        PosCasesDefinitionId id,
        String caseDesc,
        String caseDirection,
        String caseCardRef,
        String caseMerchantPrf,
        String caseTerminalPrf,
        int caseAmount,
        String caseHeader,
        String caseMti,
        List<PosFieldValueDTO> fields
) {}
