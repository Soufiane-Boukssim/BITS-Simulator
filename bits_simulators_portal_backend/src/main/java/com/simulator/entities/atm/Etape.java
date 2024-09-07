package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ETAPE")
public class Etape {

    @Id
    @Column(name = "Type", length = 10)
    private String type;

    @Column(name = "Libelle", length = 100)
    private String libelle;

    public Etape() {
    }

    public Etape(String type, String libelle) {
        this.type = type;
        this.libelle = libelle;
    }
}
