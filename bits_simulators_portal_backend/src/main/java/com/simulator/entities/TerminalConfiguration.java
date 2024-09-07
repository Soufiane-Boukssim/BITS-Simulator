package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "terminal_configuration")

public class TerminalConfiguration {

    @EmbeddedId
    private TerminalDefinitionId id;

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



}
