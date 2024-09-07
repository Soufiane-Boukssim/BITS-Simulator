package com.simulator.dtos;

import com.simulator.entities.CasesDefinitionId;

import java.util.List;

public record CasesDefinitionDTO(
        CasesDefinitionId id,
        String caseDesc,
        String caseDirection,
        String caseCardRef,
        String caseMerchantPrf,
        String caseTerminalPrf,
        int caseAmount,
        String caseHeader,
        String caseMti,
        List<FieldValueDTO> fields,
        String caseAction

        ) {}