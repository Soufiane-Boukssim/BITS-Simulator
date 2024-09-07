package com.simulator.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "terminal_ndc_configuration")
public class LogNDCConfiguration {
    @EmbeddedId
    private TerminalParamLogId id;
    @Column(name = "conf_type", nullable = false,length = 2)
    private String confType;
    @Column(name = "conf_data", length = 10)
    private String confData;

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
