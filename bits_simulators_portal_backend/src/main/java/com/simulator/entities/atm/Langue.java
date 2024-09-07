package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "LANGUE")
public class Langue {

    @Id
    @Column(name = "LANGUE_CODE", length = 3)
    private String langueCode;

    @Column(name = "WORDING", length = 50)
    private String wording;

    @Column(name = "LANGUAGE_EMV_CODE", length = 2)
    private String languageEmvCode;

    public Langue() {
    }

    public Langue(String langueCode, String wording, String languageEmvCode) {
        this.langueCode = langueCode;
        this.wording = wording;
        this.languageEmvCode = languageEmvCode;
    }
}
