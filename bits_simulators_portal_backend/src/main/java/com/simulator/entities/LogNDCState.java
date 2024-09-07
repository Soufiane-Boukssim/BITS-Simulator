package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_ndc_state")
public class LogNDCState {
    @EmbeddedId
    private TerminalParamLogId id;

    @Column(name = "state_number", nullable = false, length = 3)
    private String stateNumber;

    @Column(name="state_type", length = 255)
    private String stateType;
    @Column(name="state_data", length = 255)
    private String stateData;

    public LogNDCState(){}

    public LogNDCState(String stateNumber, String stateType, String stateData) {
        this.stateNumber = stateNumber;
        this.stateType = stateType;
        this.stateData = stateData;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getStateData() {
        return stateData;
    }

    public void setStateData(String stateData) {
        this.stateData = stateData;
    }

    @Override
    public String toString() {
        return "TerminalNDCState{" +
                "stateNumber='" + stateNumber + '\'' +
                ", stateType='" + stateType + '\'' +
                ", stateData='" + stateData + '\'' +
                '}';
    }
}
