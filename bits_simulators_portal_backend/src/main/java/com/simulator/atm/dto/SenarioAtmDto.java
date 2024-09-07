package com.simulator.atm.dto;

import com.simulator.atm.model.AtmCardProfilId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SenarioAtmDto implements Serializable {
    Long id;
    String libelle;
    List<OperationAtmDto> senarioScripts;
    AtmCardProfilId idCardProfil;
    String bankCode;
}
