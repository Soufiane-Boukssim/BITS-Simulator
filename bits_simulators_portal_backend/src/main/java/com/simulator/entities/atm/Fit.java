package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "FIT")
public class Fit {

    @Id
    @Column(name = "Hex", length = 1)
    private String hex;

    @Column(name = "TpEnc", length = 10)
    private String tpEnc;

    @Column(name = "Cle1", length = 10)
    private String cle1;

    @Column(name = "Cle2", length = 10)
    private String cle2;

    @Column(name = "Coord", length = 3)
    private String coord;

    public Fit() {
    }

    public Fit(String hex, String tpEnc, String cle1, String cle2, String coord) {
        this.hex = hex;
        this.tpEnc = tpEnc;
        this.cle1 = cle1;
        this.cle2 = cle2;
        this.coord = coord;
    }
}
