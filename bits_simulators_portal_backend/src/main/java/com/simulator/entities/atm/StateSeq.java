package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "STATE_SEQ")
public class StateSeq {

    @Id
    @Column(name = "PROFILE_CODE", length = 10)
    private String profileCode;

    @Column(name = "SEQ_NBR")
    private int seqNbr;

    @Column(name = "STATE_NUMBER")
    private int stateNumber;

    @Column(name = "WORDING", length = 100)
    private String wording;

    public StateSeq() {
    }

    public StateSeq(String profileCode, int seqNbr, int stateNumber, String wording) {
        this.profileCode = profileCode;
        this.seqNbr = seqNbr;
        this.stateNumber = stateNumber;
        this.wording = wording;
    }
}
