package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_definition")
@NamedQueries({
        @NamedQuery(name = "TerminalDefinition.existsByTerminalNumber", query = "select (count(t) > 0) from TerminalDefinition t where t.terminalNumber = :terminalNumber")
})
public class TerminalDefinition {
    @Id
    @Column(name = "terminal_number", nullable = false,length = 15)
    private String terminalNumber;

    @Column(name = "terminal_label", nullable = true,length = 30)
    private String terminalLabel;

    @Column(name = "nom_protocol", nullable = true,length = 15)
    private String nomProtocol;

    @Column(name = "communication_index", nullable = true,length = 3)
    private String communicationIndex;

    @Column(name = "machine_number", nullable = true,length = 6)
    private String machineNumber;

    @Column(name = "luno", nullable = true,length = 3)
    private String luno;

    @Column(name = "tdate", nullable = true,length = 30)
    private String tdate;

    @Column(name = "ttime", nullable = true,length = 10)
    private String ttime;

    @Column(name = "terminal_release", nullable = true,length = 6)
    private String terminalRelease;

    @Column(name = "software_id", nullable = true,length = 9)
    private String softwareId;

    @Column(name = "config_id", nullable = true,length = 4)
    private String configId;

    @Override
    public String toString() {
        return "TerminalDefinition{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", terminalLabel='" + terminalLabel + '\'' +
                ", nomProtocol='" + nomProtocol + '\'' +
                ", communicationIndex='" + communicationIndex + '\'' +
                ", machineNumber='" + machineNumber + '\'' +
                ", luno='" + luno + '\'' +
                ", tdate='" + tdate + '\'' +
                ", ttime='" + ttime + '\'' +
                ", terminalRelease='" + terminalRelease + '\'' +
                ", softwareId='" + softwareId + '\'' +
                ", configId='" + configId + '\'' +
                '}';
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getTerminalLabel() {
        return terminalLabel;
    }

    public void setTerminalLabel(String terminalLabel) {
        this.terminalLabel = terminalLabel;
    }

    public String getNomProtocol() {
        return nomProtocol;
    }

    public void setNomProtocol(String nomProtocol) {
        this.nomProtocol = nomProtocol;
    }

    public String getCommunicationIndex() {
        return communicationIndex;
    }

    public void setCommunicationIndex(String communicationIndex) {
        this.communicationIndex = communicationIndex;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getLuno() {
        return luno;
    }

    public void setLuno(String luno) {
        this.luno = luno;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    public String getTerminalRelease() {
        return terminalRelease;
    }

    public void setTerminalRelease(String terminalRelease) {
        this.terminalRelease = terminalRelease;
    }

    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public TerminalDefinition() {
    }

    public TerminalDefinition(String terminalNumber, String terminalLabel, String nomProtocol, String communicationIndex, String machineNumber, String luno, String tdate, String ttime, String terminalRelease, String softwareId, String configId) {
        this.terminalNumber = terminalNumber;
        this.terminalLabel = terminalLabel;
        this.nomProtocol = nomProtocol;
        this.communicationIndex = communicationIndex;
        this.machineNumber = machineNumber;
        this.luno = luno;
        this.tdate = tdate;
        this.ttime = ttime;
        this.terminalRelease = terminalRelease;
        this.softwareId = softwareId;
        this.configId = configId;
    }
}
