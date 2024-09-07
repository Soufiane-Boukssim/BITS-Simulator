package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@Data
public class CommsDefinitionId implements Serializable {

    @Column(name = "COM_CODE", length = 30)
    private String comCode;

    @Column(name = "WORDING", length = 30)
    private String comMode;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;





}
