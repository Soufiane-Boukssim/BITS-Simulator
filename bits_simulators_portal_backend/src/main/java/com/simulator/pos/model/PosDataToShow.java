package com.simulator.pos.model;

import com.simulator.entities.CasesSetInfoId;
import lombok.Data;

@Data
public class PosDataToShow {

    private PosDataToShowId id;
    private String reqFormater;
    private PosIsoFields reqNoFormater;
    private String resFormater;
    private PosIsoFields resNoFormater;
    private CasesSetInfoId scenario;
    private String bankCode;

}
