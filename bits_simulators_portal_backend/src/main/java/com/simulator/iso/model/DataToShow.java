package com.simulator.iso.model;

import com.simulator.entities.CasesSetInfoId;
import lombok.Data;

@Data
public class DataToShow {

    private DataToShowId id;
    private String reqFormater;
    private IsoFields reqNoFormater;
    private String resFormater;
    private IsoFields resNoFormater;
    private CasesSetInfoId scenario;
    private String bankCode;


    public DataToShow() {
        this.id = new DataToShowId();
        this.scenario = new CasesSetInfoId();
    }

    public DataToShow(DataToShowId id, String reqFormater, IsoFields reqNoFormater, String resFormater, IsoFields resNoFormater, CasesSetInfoId scenario, String bankCode) {
        this.id = id;
        this.reqFormater = reqFormater;
        this.reqNoFormater = reqNoFormater;
        this.resFormater = resFormater;
        this.resNoFormater = resNoFormater;
        this.scenario = scenario;
        this.bankCode = bankCode;
    }
}


