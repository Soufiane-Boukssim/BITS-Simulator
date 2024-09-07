package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;


@Getter
@Setter
@Data
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "protocol_def")
public class ProtocolDef {

    @EmbeddedId
    private ProtocolDefId id;

    @Column(name = "prot_desc",length = 50)
    private String protDesc;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProtocolDef that = (ProtocolDef) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
