package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_ndc_timer")
public class LogNDCTimer {
    @Id
    @Column(name = "time_type", nullable = false,length = 2)
    private String timeType;
    @Column(name = "time_value")
    private int timeValue;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

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
