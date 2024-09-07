package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "terminal_mess_nonsolicite")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TerminalMessNonsolicite {

    @EmbeddedId
    private TerminalMessNonSoliciteId id;

    @Column(name = "terminal_number", nullable = false, length = 15)
    private String terminalNumber;

    @Column(name = "trx_device_status", length = 155)
    private String trxDeviceStatus;

    @Column(name = "error_severity", length = 10)
    private String errorSeverity;

    @Column(name = "diagnostique", length = 20)
    private String diagnostique;

    @Column(name = "supply_status", length = 10)
    private String supplyStatus;

    @Column(name = "MessageUnsolicited", length = 200)
    private String MessageUnsolicited;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TerminalMessNonsolicite that = (TerminalMessNonsolicite) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
