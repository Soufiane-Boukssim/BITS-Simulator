package com.simulator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCENARIOS_ACQ")
public class scenarios {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    private String wording;
    private String case_label;
    private String message_type;
    private String protocole;
}
