package com.simulator.smartICC.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "TERMINAL_CONFIG")
public class TerminalConfig {
    @Id
    @Column(name = "Code_profile")
    private String codeProfile;

    @Column(name = "Terminal_config")
    private String terminalConfig;

    @Column(name = "Terminal_ControlledBy")
    private String terminalControlledBy;

    @Column(name = "Terminal_Country")
    private String terminalCountry;

    @Column(name = "Terminal_Currency")
    private String terminalCurrency;

    @Column(name = "Terminal_BaseEmv")
    private String terminalBaseEmv;

    @Column(name = "Terminal_TACD")
    private String terminalTacd;

    @Column(name = "Terminal_TACR")
    private String terminalTacr;

    @Column(name = "Terminal_TACO")
    private String terminalTaco;

    @Column(name = "Terminal_Min")
    private int terminalMin;

    @Column(name = "Terminal_Max")
    private int terminalMax;

    @Column(name = "Terminal_Value")
    private int terminalValue;

    @Column(name = "Terminal_Floor")
    private int terminalFloor;

}

