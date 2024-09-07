package com.simulator.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY_DEFFINITION")
public class currency {
    @EmbeddedId
    pkCurrency pkCurrency;
    private String libelle;
    private String alpha_iso;
    private String exponent;
    private String pays_labelle;

}
