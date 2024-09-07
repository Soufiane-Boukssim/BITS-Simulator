package com.simulator.atm.dto;

import com.simulator.atm.model.AtmCardProfilId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AtmCardProfilDto implements Serializable {
    AtmCardProfilId id;
    String cardDesc;
    String accountCurr;
    String track1;
    String track2;
    String track3;
}
