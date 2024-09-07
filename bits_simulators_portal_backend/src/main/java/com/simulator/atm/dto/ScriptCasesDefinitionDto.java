package com.simulator.atm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.simulator.atm.model.AtmCardProfilId;
import com.simulator.atm.model.enm.TypeDefinitionScript;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScriptCasesDefinitionDto implements Serializable {
    String id;
    String libelle;
    @Enumerated(EnumType.STRING)
    TypeDefinitionScript typeScript;
    AtmCardProfilId idCardProfil;
    List<FieldCasesDto> fieldCases;
    String bankCode;
}
