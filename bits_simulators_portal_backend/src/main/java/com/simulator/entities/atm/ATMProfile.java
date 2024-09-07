package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ATM_PROFILE")
public class ATMProfile {

    @Id
    @Column(name = "PROFILE_CODE", length = 10)
    private String profileCode;

    @Column(name = "PROFILE_WORDING", length = 100)
    private String profileWording;

    @Column(name = "PROFILE_TYPE")
    private String profileType;

    public ATMProfile() {
    }

    public ATMProfile(String profileCode, String profileWording, String profileType) {
        this.profileCode = profileCode;
        this.profileWording = profileWording;
        this.profileType = profileType;
    }
}
