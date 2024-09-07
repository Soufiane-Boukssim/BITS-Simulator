package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_ndc_screen")
public class LogNDCScreen {
    @EmbeddedId
    private TerminalParamLogId id;

    @Column(name = "screen_number", nullable = false, length = 3)
    private String screenNumber;

    @Column(name="screen_data1", length = 255)
    private String screenData1;
    @Column(name="screen_data2", length = 255)
    private String screenData2;
    @Column(name="screen_path", length = 255)
    private String screenPath;



    public LogNDCScreen(){}

    public LogNDCScreen(String screenNumber, String screenData1, String screenData2, String screenPath) {
        this.screenNumber = screenNumber;
        this.screenData1 = screenData1;
        this.screenData2 = screenData2;
        this.screenPath = screenPath;
    }

    public String getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(String screenNumber) {
        this.screenNumber = screenNumber;
    }

    public String getScreenData1() {
        return screenData1;
    }

    public void setScreenData1(String screenData1) {
        this.screenData1 = screenData1;
    }

    public String getScreenData2() {
        return screenData2;
    }

    public void setScreenData2(String screenData2) {
        this.screenData2 = screenData2;
    }

    public String getScreenPath() {
        return screenPath;
    }

    public void setScreenPath(String screenPath) {
        this.screenPath = screenPath;
    }

    @Override
    public String toString() {
        return "LogNDCScreen{" +
                "screenNumber='" + screenNumber + '\'' +
                ", screenData1='" + screenData1 + '\'' +
                ", screenData2='" + screenData2 + '\'' +
                ", screenPath='" + screenPath + '\'' +
                '}';
    }
}
