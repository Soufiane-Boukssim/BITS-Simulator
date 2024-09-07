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
public class TerminalMessNonSoliciteId implements Serializable {

    @Column(name = "deviced_id", nullable = false,length = 15)
    private String devicedId;

    @Column(name = "bank_code", nullable = false,length = 5)
    private String bankCode;
}
