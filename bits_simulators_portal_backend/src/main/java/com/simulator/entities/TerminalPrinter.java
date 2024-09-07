package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "terminal_printer")
@Entity
public class TerminalPrinter {

    @EmbeddedId
    private TerminalDefinitionId id;

    @Column(name = "receipt_config", length = 1)
    private String receiptConfig;

    @Column(name = "receipt_type", length = 2)
    private String receiptType;

    @Column(name = "receipt_fitness", length = 1)
    private String receiptFitness;

    @Column(name = "receipt_supplie", length = 1)
    private String receiptSupplie;

    @Column(name = "receipt_rollwidth")
    private int receiptRollwidth;

    @Column(name = "receipt_leftcol")
    private int receiptLeftcol;

    @Column(name = "receipt_maxline")
    private int receiptMaxline;

    @Column(name = "journal_config", length = 1)
    private String journalConfig;

    @Column(name = "journal_type", length = 2)
    private String journalType;

    @Column(name = "journal_fitness", length = 1)
    private String journalFitness;

    @Column(name = "journal_supplie", length = 1)
    private String journalSupplie;

    @Column(name = "journal_traceonoff", length = 1)
    private int journalTraceonoff;

    @Column(name = "statement_config", length = 1)
    private String statementConfig;

    @Column(name = "statement_type", length = 2)
    private String statementType;

    @Column(name = "statement_fitness", length = 1)
    private String statementFitness;

    @Column(name = "statement_supplie", length = 1)
    private String statementSupplie;

    @Column(name = "statement_rollwidth")
    private int statementRollwidth;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TerminalPrinter that = (TerminalPrinter) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
