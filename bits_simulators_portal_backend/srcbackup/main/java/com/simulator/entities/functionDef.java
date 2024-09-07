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
@Table(name = "FUNCTION_DEFFINITION")
public class functionDef {
    @EmbeddedId
    pkFunctionDef pkFunctionDef;
    private String Libelle;
}
