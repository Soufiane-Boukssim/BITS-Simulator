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
@Table(name = "terminal_cash")

public class TerminalCash {

    @EmbeddedId
    private TerminalDefinitionId id;

    @Column(name = "hcash_type",length = 2)
    private String hcashType;

    @Column(name = "hcash_config",length = 1)
    private String hcashConfig;

    @Column(name = "hcash_fitness",length = 1)
    private String hcashFitness;

    @Column(name = "hcash_supplies",length = 1)
    private String hcashSupplies;

    @Column(name = "hcash_reject",length = 1)
    private String hcashReject;

    @Column(name = "cassette1_config",length = 1)
    private String cassette1Config;

    @Column(name = "cassette1_fitness",length = 1)
    private String cassette1Fitness;

    @Column(name = "cassette1_supplies",length = 1)
    private String cassette1Supplies;

    @Column(name = "cassette2_config",length = 1)
    private String cassette2Config;

    @Column(name = "cassette2_fitness",length = 1)
    private String cassette2Fitness;

    @Column(name = "cassette2_supplies",length = 1)
    private String cassette2Supplies;

    @Column(name = "cassette3_config",length = 1)
    private String cassette3Config;

    @Column(name = "cassette3_fitness",length = 1)
    private String cassette3Fitness;

    @Column(name = "cassette3_supplies",length = 1)
    private String cassette3Supplies;

    @Column(name = "cassette4_config",length = 1)
    private String cassette4Config;

    @Column(name = "cassette4_fitness",length = 1)
    private String cassette4Fitness;

    @Column(name = "cassette4_supplies",length = 1)
    private String cassette4Supplies;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TerminalCash that = (TerminalCash) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
