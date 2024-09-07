package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "terminal_mess_nonsolicite")
@Data
@NamedQueries({
        @NamedQuery(name = "TerminalMessNonsolicite.existsByTerminalNumber", query = "select (count(t) > 0) from TerminalMessNonsolicite t where t.terminalNumber = :terminalNumber")
})
public class TerminalMessNonsolicite {
    @Id
    @Column(name = "deviced_id", length = 1)
    private String devicedId;
    @Column(name = "terminal_number", nullable = false, length = 15)
    private String terminalNumber;

    @Column(name = "trx_device_status", length = 155)
    private String trxDeviceStatus;

    @Column(name = "error_severity", length = 10)
    private String errorSeverity;

    @Column(name = "diagnostique", length = 20)
    private String diagnostique;

    @Column(name = "supply_status", length = 10)
    private String supplyStatus;

    @Column(name = "MessageUnsolicited", length = 200)
    private String MessageUnsolicited;

    @Override
    public String toString() {
        return "TerminalMessNonsolicite{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", devicedId='" + devicedId + '\'' +
                ", trxDeviceStatus='" + trxDeviceStatus + '\'' +
                ", errorSeverity='" + errorSeverity + '\'' +
                ", diagnostique='" + diagnostique + '\'' +
                ", supplyStatus='" + supplyStatus + '\'' +
                ", MessageUnsolicited='" + MessageUnsolicited + '\'' +
                '}';
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getDevicedId() {
        return devicedId;
    }

    public void setDevicedId(String devicedId) {
        this.devicedId = devicedId;
    }

    public String getTrxDeviceStatus() {
        return trxDeviceStatus;
    }

    public void setTrxDeviceStatus(String trxDeviceStatus) {
        this.trxDeviceStatus = trxDeviceStatus;
    }

    public String getErrorSeverity() {
        return errorSeverity;
    }

    public void setErrorSeverity(String errorSeverity) {
        this.errorSeverity = errorSeverity;
    }

    public String getDiagnostique() {
        return diagnostique;
    }

    public void setDiagnostique(String diagnostique) {
        this.diagnostique = diagnostique;
    }

    public String getSupplyStatus() {
        return supplyStatus;
    }

    public void setSupplyStatus(String supplyStatus) {
        this.supplyStatus = supplyStatus;
    }

    public String getMessageUnsolicited() {
        return MessageUnsolicited;
    }

    public void setMessageUnsolicited(String messageUnsolicited) {
        MessageUnsolicited = messageUnsolicited;
    }

    public TerminalMessNonsolicite() {
    }

    public TerminalMessNonsolicite(String terminalNumber, String devicedId, String trxDeviceStatus, String errorSeverity, String diagnostique, String supplyStatus, String messageUnsolicited) {
        this.terminalNumber = terminalNumber;
        this.devicedId = devicedId;
        this.trxDeviceStatus = trxDeviceStatus;
        this.errorSeverity = errorSeverity;
        this.diagnostique = diagnostique;
        this.supplyStatus = supplyStatus;
        MessageUnsolicited = messageUnsolicited;
    }
}
