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
@ToString
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "processing_def")
public class ProcessingDef {

    @EmbeddedId
    private ProcessingDefId id;

    @Column(name = "PROC_DESC",length = 50)
    private String procDesc;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProcessingDef that = (ProcessingDef) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

