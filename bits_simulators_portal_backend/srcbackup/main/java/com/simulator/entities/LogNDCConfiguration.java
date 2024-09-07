package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_ndc_configuration")
public class LogNDCConfiguration {
    @Id
    @Column(name = "conf_type", nullable = false,length = 2)
    private String confType;
    @Column(name = "conf_data", length = 10)
    private String confData;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public LogNDCConfiguration(){}

    public LogNDCConfiguration(String confType, String confData) {
        this.confType = confType;
        this.confData = confData;
    }

    @Override
    public String toString() {
        return "TerminalNDCConfiguration{" +
                "confType='" + confType + '\'' +
                ", confData='" + confData + '\'' +
                '}';
    }

    public String getConfType() {
        return confType;
    }

    public void setConfType(String confType) {
        this.confType = confType;
    }


    public String getConfData() {
        return confData;
    }

    public void setConfData(String confData) {
        this.confData = confData;
    }
}
