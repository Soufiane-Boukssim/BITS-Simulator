package com.simulator.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CasesDefDTO {
    private String caseNo;
    private String caseDesc;
    private String bankCode;
    private String caseProtocole;
}
