package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Protocole {

    @Id
    @Column (name = "idProtocole")
    private String idProtocole;

    @Column(name = "wordingProtocole")
    private String protoWording;
}
