package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "PINDX")
public class Pindx {

    @Id
    @Column(name = "Hex", length = 2)
    private String hex;

    @Column(name = "track")
    private int track;

    @Column(name = "Deliminer", length = 50)
    private String delimiter;

    public Pindx() {
    }

    public Pindx(String hex, int track, String delimiter) {
        this.hex = hex;
        this.track = track;
        this.delimiter = delimiter;
    }
}
