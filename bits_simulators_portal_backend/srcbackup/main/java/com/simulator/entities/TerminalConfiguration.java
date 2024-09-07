package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_configuration")
@NamedQueries({
        @NamedQuery(name = "TerminalConfiguration.existsByTerminalNumber", query = "select (count(t) > 0) from TerminalConfiguration t where t.terminalNumber = :terminalNumber")
})
public class TerminalConfiguration {
    @Id
    @Column(name = "terminal_number", nullable = false,length = 15)
    private String terminalNumber;

    @Column(name = "terminal_model",length = 5)
    private String terminalModel;

    @Column(name = "terminal_system_disk",length = 5)
    private String terminalSystemDisk;

    @Column(name = "terminal_mcrw",length = 5)
    private String terminalMCRW;

    @Column(name = "terminal_depository",length = 5)
    private String terminalDepository;

    @Column(name = "terminal_nightsafe",length = 5)
    private String terminalNightsafe;

    @Column(name = "terminal_encryptor",length = 5)
    private String terminalEncryptor;

    @Column(name = "terminal_security_camera",length = 5)
    private String terminalSecurityCamera;

    @Column(name = "terminal_door_access",length = 5)
    private String terminalDoorAccess;

    @Column(name = "terminal_flex_disk",length = 5)
    private String terminalFlexDisk;

    @Column(name = "terminal_ti_bin",length = 5)
    private String terminalTiBin;

    @Column(name = "terminal_keyboard",length = 5)
    private String terminalKeyboard;

    @Column(name = "terminal_display_voice",length = 6)
    private String terminalDisplayVoice;

    @Column(name = "terminal_signage_display",length = 5)
    private String terminalSignageDisplay;

    @Column(name = "terminal_media",length = 5)
    private String terminalMedia;

    @Column(name = "terminal_env_disp",length = 5)
    private String terminalEnvDisp;

    @Column(name = "terminal_dpm",length = 5)
    private String terminalDPM;

    @Column(name = "terminal_dgital_audio",length = 5)
    private String terminalDigitalAudio;

    @Column(name = "terminal_bna",length = 5)
    private String terminalBNA;

    @Column(name = "terminal_cheq_processing",length = 4)
    private String terminalCheqProcessing;



    public TerminalConfiguration() {
    }

    @Override
    public String toString() {
        return "TerminalConfiguration{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", terminalModel='" + terminalModel + '\'' +
                ", terminalSystemDisk='" + terminalSystemDisk + '\'' +
                ", terminalMcrw='" + terminalMCRW + '\'' +
                ", terminalDepository='" + terminalDepository + '\'' +
                ", terminalNightsafe='" + terminalNightsafe + '\'' +
                ", terminalEncryptor='" + terminalEncryptor + '\'' +
                ", terminalSecurityCamera='" + terminalSecurityCamera + '\'' +
                ", terminalDoorAccess='" + terminalDoorAccess + '\'' +
                ", terminalFlexDisk='" + terminalFlexDisk + '\'' +
                ", terminalTiBin='" + terminalTiBin + '\'' +
                ", terminalKeyboard='" + terminalKeyboard + '\'' +
                ", terminalDisplayVoice='" + terminalDisplayVoice + '\'' +
                ", terminalSignageDisplay='" + terminalSignageDisplay + '\'' +
                ", terminalMedia='" + terminalMedia + '\'' +
                ", terminalEnvDisp='" + terminalEnvDisp + '\'' +
                ", terminalDpm='" + terminalDPM + '\'' +
                ", terminalDgitalAudio='" + terminalDigitalAudio + '\'' +
                ", terminalBna='" + terminalBNA + '\'' +
                ", terminalCheqProcessing='" + terminalCheqProcessing + '\'' +
                '}';
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public void setTerminalModel(String terminalModel) {
        this.terminalModel = terminalModel;
    }

    public String getTerminalSystemDisk() {
        return terminalSystemDisk;
    }

    public void setTerminalSystemDisk(String terminalSystemDisk) {
        this.terminalSystemDisk = terminalSystemDisk;
    }

    public String getTerminalMCRW() {
        return terminalMCRW;
    }

    public void setTerminalMCRW(String terminalMcrw) {
        this.terminalMCRW = terminalMcrw;
    }

    public String getTerminalDepository() {
        return terminalDepository;
    }

    public void setTerminalDepository(String terminalDepository) {
        this.terminalDepository = terminalDepository;
    }

    public String getTerminalNightsafe() {
        return terminalNightsafe;
    }

    public void setTerminalNightsafe(String terminalNightsafe) {
        this.terminalNightsafe = terminalNightsafe;
    }

    public String getTerminalEncryptor() {
        return terminalEncryptor;
    }

    public void setTerminalEncryptor(String terminalEncryptor) {
        this.terminalEncryptor = terminalEncryptor;
    }

    public String getTerminalSecurityCamera() {
        return terminalSecurityCamera;
    }

    public void setTerminalSecurityCamera(String terminalSecurityCamera) {
        this.terminalSecurityCamera = terminalSecurityCamera;
    }

    public String getTerminalDoorAccess() {
        return terminalDoorAccess;
    }

    public void setTerminalDoorAccess(String terminalDoorAccess) {
        this.terminalDoorAccess = terminalDoorAccess;
    }

    public String getTerminalFlexDisk() {
        return terminalFlexDisk;
    }

    public void setTerminalFlexDisk(String terminalFlexDisk) {
        this.terminalFlexDisk = terminalFlexDisk;
    }

    public String getTerminalTiBin() {
        return terminalTiBin;
    }

    public void setTerminalTiBin(String terminalTiBin) {
        this.terminalTiBin = terminalTiBin;
    }

    public String getTerminalKeyboard() {
        return terminalKeyboard;
    }

    public void setTerminalKeyboard(String terminalKeyboard) {
        this.terminalKeyboard = terminalKeyboard;
    }

    public String getTerminalDisplayVoice() {
        return terminalDisplayVoice;
    }

    public void setTerminalDisplayVoice(String terminalDisplayVoice) {
        this.terminalDisplayVoice = terminalDisplayVoice;
    }

    public String getTerminalSignageDisplay() {
        return terminalSignageDisplay;
    }

    public void setTerminalSignageDisplay(String terminalSignageDisplay) {
        this.terminalSignageDisplay = terminalSignageDisplay;
    }

    public String getTerminalMedia() {
        return terminalMedia;
    }

    public void setTerminalMedia(String terminalMedia) {
        this.terminalMedia = terminalMedia;
    }

    public String getTerminalEnvDisp() {
        return terminalEnvDisp;
    }

    public void setTerminalEnvDisp(String terminalEnvDisp) {
        this.terminalEnvDisp = terminalEnvDisp;
    }

    public String getTerminalCheqProcessing() {
        return terminalCheqProcessing;
    }

    public void setTerminalCheqProcessing(String terminalCheqProcessing) {
        this.terminalCheqProcessing = terminalCheqProcessing;
    }

    public TerminalConfiguration(String terminalNumber, String terminalModel, String terminalSystemDisk, String terminalMcrw, String terminalDepository, String terminalNightsafe, String terminalEncryptor, String terminalSecurityCamera, String terminalDoorAccess, String terminalFlexDisk, String terminalTiBin, String terminalKeyboard, String terminalDisplayVoice, String terminalSignageDisplay, String terminalMedia, String terminalEnvDisp, String terminalDPM, String terminalDigitalAudio, String terminalBNA, String terminalCheqProcessing) {
        this.terminalNumber = terminalNumber;
        this.terminalModel = terminalModel;
        this.terminalSystemDisk = terminalSystemDisk;
        this.terminalMCRW = terminalMcrw;
        this.terminalDepository = terminalDepository;
        this.terminalNightsafe = terminalNightsafe;
        this.terminalEncryptor = terminalEncryptor;
        this.terminalSecurityCamera = terminalSecurityCamera;
        this.terminalDoorAccess = terminalDoorAccess;
        this.terminalFlexDisk = terminalFlexDisk;
        this.terminalTiBin = terminalTiBin;
        this.terminalKeyboard = terminalKeyboard;
        this.terminalDisplayVoice = terminalDisplayVoice;
        this.terminalSignageDisplay = terminalSignageDisplay;
        this.terminalMedia = terminalMedia;
        this.terminalEnvDisp = terminalEnvDisp;
        this.terminalDPM = terminalDPM;
        this.terminalDigitalAudio = terminalDigitalAudio;
        this.terminalBNA = terminalBNA;
        this.terminalCheqProcessing = terminalCheqProcessing;
    }

    public String getTerminalDPM() {
        return terminalDPM;
    }

    public void setTerminalDPM(String terminalDPM) {
        this.terminalDPM = terminalDPM;
    }

    public String getTerminalDigitalAudio() {
        return terminalDigitalAudio;
    }

    public void setTerminalDigitalAudio(String terminalDigitalAudio) {
        this.terminalDigitalAudio = terminalDigitalAudio;
    }

    public String getTerminalBNA() {
        return terminalBNA;
    }

    public void setTerminalBNA(String terminalBNA) {
        this.terminalBNA = terminalBNA;
    }
}
