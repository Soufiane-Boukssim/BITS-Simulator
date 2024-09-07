package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_ndc_fit")
public class LogNDCFit {
    @Id
    @Column(name = "fit_numher",nullable = false, length = 3)
    private String fitNumber;
    @Column(name = "fit_data",length = 255)
    private String fitData;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

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
