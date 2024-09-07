package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "OBJECT_TYPE")
public class ObjectType {

    @Id
    @Column(name = "CODE")
    private int code;

    @Column(name = "LIBELLE", length = 50)
    private String libelle;

    public ObjectType() {
    }

    public ObjectType(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }
}
