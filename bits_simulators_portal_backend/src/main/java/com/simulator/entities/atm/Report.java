package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "REPORT")
public class Report {

    @Id
    @Column(name = "PROFILE", length = 10)
    private String profile;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "ETAPE")
    private int etape;

    @Column(name = "LIBELLE", length = 100)
    private String libelle;

    @Column(name = "TYPE", length = 1)
    private String type;

    @Column(name = "E2")
    private int e2;

    @Column(name = "E3")
    private int e3;

    @Column(name = "E4")
    private int e4;

    @Column(name = "E5")
    private int e5;

    @Column(name = "E6")
    private int e6;

    @Column(name = "E7")
    private int e7;

    @Column(name = "E8")
    private int e8;

    @Column(name = "E9")
    private int e9;

    public Report() {
    }

    public Report(String profile, String description, int etape, String libelle, String type, int e2, int e3, int e4, int e5, int e6, int e7, int e8, int e9) {
        this.profile = profile;
        this.description = description;
        this.etape = etape;
        this.libelle = libelle;
        this.type = type;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.e5 = e5;
        this.e6 = e6;
        this.e7 = e7;
        this.e8 = e8;
        this.e9 = e9;
    }
}
