package com.simulator.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationAtmDto implements Serializable {
    Long id;
    String libelle;
    List<ScriptCasesDefinitionDto> scriptCasesDefinitions;
    AtmCardProfilDto atmCardProfilDto;
    String bankCode;
}
