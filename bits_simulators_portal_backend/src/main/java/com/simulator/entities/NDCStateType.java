package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ndc_state_type")
public class NDCStateType {
//    @Id
@EmbeddedId
private TerminalParamLogId id;

    @Column(name = "state_id")
    private int stateId;

    @Column(name = "state_type", length = 1, nullable = false)
    private String stateType;
    @Column(name = "state_name", length = 50)
    private  String stateName;
    @Column(name = "state_entry_2", length = 50)
    private  String stateEntry2;
    @Column(name = "state_entry_3", length = 50)
    private  String stateEntry3;
    @Column(name = "state_entry_4", length = 50)
    private  String stateEntry4;
    @Column(name = "state_entry_5", length = 50)
    private  String stateEntry5;
    @Column(name = "state_entry_6", length = 50)
    private  String stateEntry6;
    @Column(name = "state_entry_7", length = 50)
    private  String stateEntry7;
    @Column(name = "state_entry_8", length = 50)
    private  String stateEntry8;
    @Column(name = "state_entry_9", length = 50)
    private  String stateEntry9;
    public NDCStateType(){}
    public NDCStateType(String stateType, String stateName, String stateEntry2, String stateEntry3, String stateEntry4, String stateEntry5, String stateEntry6, String stateEntry7, String stateEntry8, String stateEntry9) {
        this.stateType = stateType;
        this.stateName = stateName;
        this.stateEntry2 = stateEntry2;
        this.stateEntry3 = stateEntry3;
        this.stateEntry4 = stateEntry4;
        this.stateEntry5 = stateEntry5;
        this.stateEntry6 = stateEntry6;
        this.stateEntry7 = stateEntry7;
        this.stateEntry8 = stateEntry8;
        this.stateEntry9 = stateEntry9;
    }

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateEntry2() {
        return stateEntry2;
    }

    public void setStateEntry2(String stateEntry2) {
        this.stateEntry2 = stateEntry2;
    }

    public String getStateEntry3() {
        return stateEntry3;
    }

    public void setStateEntry3(String stateEntry3) {
        this.stateEntry3 = stateEntry3;
    }

    public String getStateEntry4() {
        return stateEntry4;
    }

    public void setStateEntry4(String stateEntry4) {
        this.stateEntry4 = stateEntry4;
    }

    public String getStateEntry5() {
        return stateEntry5;
    }

    public void setStateEntry5(String stateEntry5) {
        this.stateEntry5 = stateEntry5;
    }

    public String getStateEntry6() {
        return stateEntry6;
    }

    public void setStateEntry6(String stateEntry6) {
        this.stateEntry6 = stateEntry6;
    }

    public String getStateEntry7() {
        return stateEntry7;
    }

    public void setStateEntry7(String stateEntry7) {
        this.stateEntry7 = stateEntry7;
    }

    public String getStateEntry8() {
        return stateEntry8;
    }

    public void setStateEntry8(String stateEntry8) {
        this.stateEntry8 = stateEntry8;
    }

    public String getStateEntry9() {
        return stateEntry9;
    }

    public void setStateEntry9(String stateEntry9) {
        this.stateEntry9 = stateEntry9;
    }

    @Override
    public String toString() {
        return "NDCStateType{" +
                "stateType='" + stateType + '\'' +
                ", stateName='" + stateName + '\'' +
                ", stateEntry2='" + stateEntry2 + '\'' +
                ", stateEntry3='" + stateEntry3 + '\'' +
                ", stateEntry4='" + stateEntry4 + '\'' +
                ", stateEntry5='" + stateEntry5 + '\'' +
                ", stateEntry6='" + stateEntry6 + '\'' +
                ", stateEntry7='" + stateEntry7 + '\'' +
                ", stateEntry8='" + stateEntry8 + '\'' +
                ", stateEntry9='" + stateEntry9 + '\'' +
                '}';
    }
}
