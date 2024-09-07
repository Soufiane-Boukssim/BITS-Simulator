package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@Data
public class CommsDefinitionId implements Serializable {

    @Column(name = "COMM_ID", nullable = false)
    private Integer commId;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }



}
