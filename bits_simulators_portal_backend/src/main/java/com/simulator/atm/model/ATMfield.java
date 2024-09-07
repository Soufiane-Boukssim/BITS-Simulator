package com.simulator.atm.model;

import com.simulator.atm.model.enm.MessageTypeField;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ATMfield {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idATMfield;
    @Enumerated(EnumType.STRING)
    private MessageTypeField messageTypeField;
    private String description;
}
