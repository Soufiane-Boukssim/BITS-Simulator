package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "comms_param")
public class CommsParam {
    @EmbeddedId
    private CommsParamId id;
    @Column(name = "COMM_TYPE", nullable = false, length = 3)
    private String commType;

    @Column(name = "INST_CODE", nullable = false, length = 6)
    private String instCode;

    @Column(name = "HEADER_COMM")
    private Integer headerComm;

    @Column(name = "HEADER_TYPE")
    private Character headerType;

    @Column(name = "COMM_IP", length = 16)
    private String commIp;

    @Column(name = "COMM_PORT")
    private Integer commPort;

    @Column(name = "MANAG_MAC")
    private Character managMac;

    @Column(name = "manageHeaderMac")
    private Character manageHeaderMac;

    @Column(name = "MANAG_HEADER")
    private Character managHeader;

    @Column(name = "MSG_HEADER", length = 32)
    private String msgHeader;


    @Column(name = "TIME_OUT_COMMS")
    private Integer timeOutComms;


    @Column(name = "CHIP_SUPPORTED")
    private Character chipSupported;


    public CommsParamId getId() {
        return id;
    }

    public void setId(CommsParamId id) {
        this.id = id;
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String commType) {
        this.commType = commType;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public Integer getHeaderComm() {
        return headerComm;
    }

    public void setHeaderComm(Integer headerComm) {
        this.headerComm = headerComm;
    }

    public Character getHeaderType() {
        return headerType;
    }

    public void setHeaderType(Character headerType) {
        this.headerType = headerType;
    }

    public String getCommIp() {
        return commIp;
    }

    public void setCommIp(String commIp) {
        this.commIp = commIp;
    }

    public Integer getCommPort() {
        return commPort;
    }

    public void setCommPort(Integer commPort) {
        this.commPort = commPort;
    }

    public Character getManagMac() {
        return managMac;
    }

    public void setManagMac(Character managMac) {
        this.managMac = managMac;
    }

    public Character getManagHeader() {
        return managHeader;
    }

    public void setManagHeader(Character managHeader) {
        this.managHeader = managHeader;
    }

    public String getMsgHeader() {
        return msgHeader;
    }

    public void setMsgHeader(String msgHeader) {
        this.msgHeader = msgHeader;
    }

}
