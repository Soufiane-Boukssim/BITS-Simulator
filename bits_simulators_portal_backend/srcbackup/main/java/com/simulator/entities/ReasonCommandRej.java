package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "reason_command_rej")
public class ReasonCommandRej {
    @Id
    @Column(name = "terminal_number",nullable = false, length = 15)
    private String terminalNumber;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    @Column(name = "error_type",length = 1)
    private String errorType;
    @Column(name = "error_code",length = 5)
    private String errorCode;
    @Column(name = "message_solicited",length = 200)
    private String messageSolicited;

    public ReasonCommandRej(){}
    public ReasonCommandRej(String terminalNumber, String errorType, String errorCode, String messageSolicited) {
        this.terminalNumber = terminalNumber;
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.messageSolicited = messageSolicited;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageSolicited() {
        return messageSolicited;
    }

    public void setMessageSolicited(String messageSolicited) {
        this.messageSolicited = messageSolicited;
    }

    @Override
    public String toString() {
        return "{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", errorType='" + errorType + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", messageSolicited='" + messageSolicited + '\'' +
                '}';
    }
}
