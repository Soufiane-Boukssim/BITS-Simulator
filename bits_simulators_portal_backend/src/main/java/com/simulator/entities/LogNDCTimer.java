package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_ndc_timer")
public class LogNDCTimer {
    @EmbeddedId
    private TerminalParamLogId id;

    @Column(name = "time_type", nullable = false,length = 2)
    private String timeType;
    @Column(name = "time_value")
    private int timeValue;



    public LogNDCTimer(){}

    public LogNDCTimer(String timeType, int timeValue) {
        this.timeType = timeType;
        this.timeValue = timeValue;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public int getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(int timeValue) {
        this.timeValue = timeValue;
    }

    @Override
    public String toString() {
        return "TerminalNDCTimer{" +
                "timeType='" + timeType + '\'' +
                ", timeValue=" + timeValue +
                '}';
    }
}
