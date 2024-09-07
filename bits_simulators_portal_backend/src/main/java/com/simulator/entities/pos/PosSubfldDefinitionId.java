package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class PosSubfldDefinitionId implements Serializable {
    private static final long serialVersionUID = 8821049565197578460L;



    @Column(name = "SUBFLD_ID", nullable = false,length = 10)
    private String  subfldId;
    @Column(name = "SUBFLD_PROTOCOLE",length = 2)
    private String subfldProtocole;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public PosSubfldDefinitionId(String subfldId, String subfldProtocole) {
        this.subfldId = subfldId;
        this.subfldProtocole = subfldProtocole;
    }

    public PosSubfldDefinitionId() {

    }

    public String getSubfldId() {
        return subfldId;
    }

    public void setSubfldId(String subfldId) {
        this.subfldId = subfldId;
    }

    public String getSubfldProtocole() {
        return subfldProtocole;
    }

    public void setSubfldProtocole(String subfldProtocole) {
        this.subfldProtocole = subfldProtocole;
    }

    @Override
    public String toString() {
        return "SubfldDefinitionId{" +
                "subfldId=" + subfldId +
                ", subfldProtocole=" + subfldProtocole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PosSubfldDefinitionId entity = (PosSubfldDefinitionId) o;
        return Objects.equals(this.subfldId, entity.subfldId) &&
                Objects.equals(this.subfldProtocole, entity.subfldProtocole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subfldId, subfldProtocole);
    }
}