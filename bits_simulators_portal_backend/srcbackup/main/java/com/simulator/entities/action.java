package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACTION_DEFINITION")
public class action {
    @Id
    private Long code;
    private String libelle;
    private String type;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
}
