package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_param")
public class TerminalParam {

    @EmbeddedId
    private TerminalParamId id;

    @Column(name = "TERM_LOCATION",length = 50)
    private String termLocation;

    @Column(name = "Term_capabilities",length = 6)
    private String termCapabilities;

    @Column(name="term_type",length = 3)
    private String termType;

    @Column(name = "tran_count")
    private int tranCount;

    @Column(name = "term_ver_result",length = 10)
    private String termVerResult;

    @Column(name = "Application_ver_num",length = 4)
    private String applicationVerNum;

    public TerminalParamId getId() {
        return id;
    }

    public void setId(TerminalParamId id) {
        this.id = id;
    }

    public String getTermLocation() {
        return termLocation;
    }

    public void setTermLocation(String termLocation) {
        this.termLocation = termLocation;
    }

    public String getTermCapabilities() {
        return termCapabilities;
    }

    public void setTermCapabilities(String termCapabilities) {
        this.termCapabilities = termCapabilities;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public int getTranCount() {
        return tranCount;
    }

    public void setTranCount(int tranCount) {
        this.tranCount = tranCount;
    }

    public String getTermVerResult() {
        return termVerResult;
    }

    public void setTermVerResult(String termVerResult) {
        this.termVerResult = termVerResult;
    }

    public String getApplicationVerNum() {
        return applicationVerNum;
    }

    public void setApplicationVerNum(String applicationVerNum) {
        this.applicationVerNum = applicationVerNum;
    }


    public TerminalParam(TerminalParamId id, String termLocation, String termCapabilities, String termType, int tranCount, String termVerResult, String applicationVerNum) {
        this.id = id;
        this.termLocation = termLocation;
        this.termCapabilities = termCapabilities;
        this.termType = termType;
        this.tranCount = tranCount;
        this.termVerResult = termVerResult;
        this.applicationVerNum = applicationVerNum;
    }

    public TerminalParam() {
    }

    @Override
    public String toString() {
        return "TerminalParam{" +
                "id='" + id + '\'' +
                ", termLocation='" + termLocation + '\'' +
                ", termCapabilities='" + termCapabilities + '\'' +
                ", termType='" + termType + '\'' +
                ", tranCount=" + tranCount +
                ", termVerResult='" + termVerResult + '\'' +
                ", applicationVerNum='" + applicationVerNum + '\'' +
                '}';
    }
}
