package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalDefinitionId implements Serializable {

    @Column(name = "terminal_number", nullable = false,length = 15)
    private String terminalNumber;

    @Column(name = "bank_code", nullable = false,length = 5)
    private String bankCode;
}
