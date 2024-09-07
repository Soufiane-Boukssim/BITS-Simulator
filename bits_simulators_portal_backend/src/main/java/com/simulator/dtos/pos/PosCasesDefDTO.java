package com.simulator.dtos.pos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PosCasesDefDTO {
    private String caseNo;
    private String caseDesc;
    private String bankCode;
    private String caseProtocole;
}
