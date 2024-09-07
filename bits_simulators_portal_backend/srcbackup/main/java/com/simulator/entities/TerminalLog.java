package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "terminal_log")
@Data
public class TerminalLog {
    @Id
    @Column(name = "log_sequence", length = 5,nullable = false)
    private String logSequence;
    @Column(name = "log_trxCode", length = 16)
    private String logTrxCode;
    @Column(name = "log_EDate", length = 30)
    private String logEDate;
    @Column(name = "log_EData", length = 1000)
    private String logEData;
    @Column(name = "log_RDate", length = 30)
    private String logRDate;
    @Column(name = "log_RData", length = 1000)
    private String logRData;
    @Column(name = "log_ECptRnd", length = 1000)
    private String logECptRnd;
    @Column(name = "log_privative", length = 1000)
    private String logPrivative;

    public TerminalLog() {
    }

    @Override
    public String toString() {
        return "TerminalLog{" +
                "logSequence='" + logSequence + '\'' +
                ", logTrxCode='" + logTrxCode + '\'' +
                ", logEDate='" + logEDate + '\'' +
                ", logEData='" + logEData + '\'' +
                ", logRDate='" + logRDate + '\'' +
                ", logRData='" + logRData + '\'' +
                ", logECptRnd='" + logECptRnd + '\'' +
                ", logPrivative='" + logPrivative + '\'' +
                '}';
    }

    public String getLogSequence() {
        return logSequence;
    }

    public void setLogSequence(String logSequence) {
        this.logSequence = logSequence;
    }

    public String getLogTrxCode() {
        return logTrxCode;
    }

    public void setLogTrxCode(String logTrxCode) {
        this.logTrxCode = logTrxCode;
    }

    public String getLogEDate() {
        return logEDate;
    }

    public void setLogEDate(String logEDate) {
        this.logEDate = logEDate;
    }

    public String getLogEData() {
        return logEData;
    }

    public void setLogEData(String logEData) {
        this.logEData = logEData;
    }

    public String getLogRDate() {
        return logRDate;
    }

    public void setLogRDate(String logRDate) {
        this.logRDate = logRDate;
    }

    public String getLogRData() {
        return logRData;
    }

    public void setLogRData(String logRData) {
        this.logRData = logRData;
    }

    public String getLogECptRnd() {
        return logECptRnd;
    }

    public void setLogECptRnd(String logECptRnd) {
        this.logECptRnd = logECptRnd;
    }

    public String getLogPrivative() {
        return logPrivative;
    }

    public void setLogPrivative(String logPrivative) {
        this.logPrivative = logPrivative;
    }

    public TerminalLog(String logSequence, String logTrxCode, String logEDate, String logEData, String logRDate, String logRData, String logECptRnd, String logPrivative) {
        this.logSequence = logSequence;
        this.logTrxCode = logTrxCode;
        this.logEDate = logEDate;
        this.logEData = logEData;
        this.logRDate = logRDate;
        this.logRData = logRData;
        this.logECptRnd = logECptRnd;
        this.logPrivative = logPrivative;
    }
}

















