package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Setter
@Data
@Getter
public class PosProcessingDefId implements Serializable {

    @Column(name = "PROC_CODE",nullable = false,length = 3)
    private String procCode;

    @Column(name = "PROC_PROTO",length = 2, nullable = false)
    private String procProtocol;


    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public PosProcessingDefId(String procCode, String procProtocol) {
        this.procCode = procCode;
        this.procProtocol = procProtocol;
    }


    @Override
    public int hashCode() {
        return Objects.hash(procCode, procProtocol);
    }
}
