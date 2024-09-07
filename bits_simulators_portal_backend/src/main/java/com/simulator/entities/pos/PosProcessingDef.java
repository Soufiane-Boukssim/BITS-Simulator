package com.simulator.entities.pos;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "pos_processing_def")
public class PosProcessingDef {

    @EmbeddedId
    private PosProcessingDefId id;

    @Column(name = "PROC_DESC",length = 50)
    private String procDesc;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PosProcessingDef that = (PosProcessingDef) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

