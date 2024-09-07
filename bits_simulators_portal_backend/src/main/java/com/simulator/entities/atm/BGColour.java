package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BG_COLOURS")
public class BGColour {

    @Id
    @Column(name = "CODE", length = 2)
    private String code;

    @Column(name = "LIBELLE", length = 50)
    private String libelle;

    public BGColour() {
    }

    public BGColour(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }
}
