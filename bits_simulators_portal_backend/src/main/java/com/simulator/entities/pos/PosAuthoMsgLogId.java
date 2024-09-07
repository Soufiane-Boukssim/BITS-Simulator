package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class PosAuthoMsgLogId implements Serializable {


    private static final long serialVersionUID = -1447624640954370952L;
    @Column(name = "AUT_DATETIME")
    private Date autDateTime;

    @Column(name = "AUT_PROTOCOL",length = 2)
    private String autProtocol;

    @Column(name = "INST_CODE",length = 6)
    private String instCode;

    public Date getAutDateTime() {
        return autDateTime;
    }

    public void setAutDateTime(Date autDateTime) {
        this.autDateTime = autDateTime;
    }

    public String getAutProtocol() {
        return autProtocol;
    }

    public void setAutProtocol(String autProtocol) {
        this.autProtocol = autProtocol;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public PosAuthoMsgLogId(Date autDateTime, String autProtocol, String instCode) {
        this.autDateTime = autDateTime;
        this.autProtocol = autProtocol;
        this.instCode = instCode;
    }

    public PosAuthoMsgLogId() {
    }




    @Override
    public String toString() {
        return "PosAuthoMsgLogId{" +
                "autDateTime=" + autDateTime +
                ", autProtocol=" + autProtocol +
                ", instCode='" + instCode + '\'' +
                '}';
    }
}
