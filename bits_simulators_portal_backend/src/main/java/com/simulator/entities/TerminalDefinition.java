package com.simulator.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "terminal_definition")

public class TerminalDefinition {

    @EmbeddedId
    private TerminalDefinitionId id;

    @Column(name = "terminal_label", nullable = true,length = 30)
    private String terminalLabel;

    @Column(name = "nom_protocol", nullable = true,length = 15)
    private String nomProtocol;

    @Column(name = "communication_index", nullable = true,length = 3)
    private String communicationIndex;

    @Column(name = "machine_number", nullable = true,length = 6)
    private String machineNumber;

    @Column(name = "luno", nullable = true,length = 3)
    private String luno;

    @Column(name = "tdate", nullable = true,length = 30)
    private String tdate;

    @Column(name = "ttime", nullable = true,length = 10)
    private String ttime;

    @Column(name = "terminal_release", nullable = true,length = 6)
    private String terminalRelease;

    @Column(name = "software_id", nullable = true,length = 9)
    private String softwareId;

    @Column(name = "config_id", nullable = true,length = 4)
    private String configId;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TerminalDefinition that = (TerminalDefinition) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
