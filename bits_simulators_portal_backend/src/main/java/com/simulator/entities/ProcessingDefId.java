package com.simulator.entities;

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
public class ProcessingDefId implements Serializable {

    @Column(name = "PROC_CODE",nullable = false,length = 3)
    private String procCode;

    @Column(name = "PROC_PROTO",length = 2, nullable = false)
    private String procProtocol;


    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public ProcessingDefId(String procCode, String procProtocol) {
        this.procCode = procCode;
        this.procProtocol = procProtocol;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessingDefId that)) return false;
        return Objects.equals(procCode, that.procCode) && Objects.equals(procProtocol, that.procProtocol);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(procCode, procProtocol);
    }
}
