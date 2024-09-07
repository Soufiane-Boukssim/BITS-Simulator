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
@Table(name = "CHARGE_CURRENT_CASES")
public class chargeCurrCases {
    @EmbeddedId
    pkChargeCurrCases pkChargeCurrCases;
    private String case_checked;
    private String case_name;
    private String field_num;
    private String field_iso;
    private String field_value;
    private String fld39;
    private String fld54;
    private String fld55;
    private String fld72;
}
