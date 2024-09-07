package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "terminal_keys")
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class TerminalKeys {
    @EmbeddedId
    private TerminalDefinitionId id;

    @Column(name = "key_A_old", length = 50)
    private String keyAOld;

    @Column(name = "key_A", length = 50)
    private String keyA;

    @Column(name = "key_A_cv", length = 50)
    private String keyACv;

    @Column(name = "key_B_old", length = 50)
    private String keyBOld;

    @Column(name = "key_B", length = 50)
    private String keyB;

    @Column(name = "key_B_cv", length = 50)
    private String keyBCv;

    @Column(name = "key_MAC_old", length = 50)
    private String keyMacOld;

    @Column(name = "key_MAC", length = 50)
    private String keyMac;

    @Column(name = "key_MAC_cv", length = 50)
    private String keyMacCv;

    @Column(name = "key_visa_old", length = 50)
    private String keyVisaOld;

    @Column(name = "key_visa", length = 50)
    private String keyVisa;

    @Column(name = "key_visa_cv", length = 50)
    private String keyVisaCv;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TerminalKeys that = (TerminalKeys) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
