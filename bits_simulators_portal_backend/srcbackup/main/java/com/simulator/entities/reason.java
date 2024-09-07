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
@Table(name = "REASON_DEFFINITION")
public class reason {
    @EmbeddedId
    pkReason pkReason;
    private String libelle;
}
