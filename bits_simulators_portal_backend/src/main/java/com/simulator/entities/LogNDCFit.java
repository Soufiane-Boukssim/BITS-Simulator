package com.simulator.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "terminal_ndc_fit")
public class LogNDCFit {
    @EmbeddedId
    private TerminalParamLogId id;


    @Column(name = "fit_numher",nullable = false, length = 3)
    private String fitNumber;
    @Column(name = "fit_data",length = 255)
    private String fitData;

    public LogNDCFit(){}
    public LogNDCFit(String fitNumber, String fitData) {
        this.fitNumber = fitNumber;
        this.fitData = fitData;
    }

    public String getFitNumber() {
        return fitNumber;
    }

    public void setFitNumber(String fitNumber) {
        this.fitNumber = fitNumber;
    }

    public String getFitData() {
        return fitData;
    }

    public void setFitData(String fitData) {
        this.fitData = fitData;
    }

    @Override
    public String toString() {
        return "TerminalNDCFit{" +
                "fitNumber='" + fitNumber + '\'' +
                ", fitData='" + fitData + '\'' +
                '}';
    }
}
