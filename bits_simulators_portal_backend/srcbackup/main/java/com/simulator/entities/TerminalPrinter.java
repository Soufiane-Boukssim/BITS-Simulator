package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_printer")
@NamedQueries({
        @NamedQuery(name = "TerminalPrinter.existsByTerminalNumber", query = "select (count(t) > 0) from TerminalPrinter t where t.terminalNumber = :terminalNumber")
})
public class TerminalPrinter {
    @Id
    @Column(name = "terminal_number", length = 15)
    private String terminalNumber;

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
    public String toString() {
        return "TerminalPrinter{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", receiptConfig='" + receiptConfig + '\'' +
                ", receiptType='" + receiptType + '\'' +
                ", receiptFitness='" + receiptFitness + '\'' +
                ", receiptSupplie='" + receiptSupplie + '\'' +
                ", receiptRollwidth=" + receiptRollwidth +
                ", receiptLeftcol=" + receiptLeftcol +
                ", receiptMaxline=" + receiptMaxline +
                ", journalConfig='" + journalConfig + '\'' +
                ", journalType='" + journalType + '\'' +
                ", journalFitness='" + journalFitness + '\'' +
                ", journalSupplie='" + journalSupplie + '\'' +
                ", journalTraceonoff=" + journalTraceonoff +
                ", statementConfig='" + statementConfig + '\'' +
                ", statementType='" + statementType + '\'' +
                ", statementFitness='" + statementFitness + '\'' +
                ", statementSupplie='" + statementSupplie + '\'' +
                ", statementRollwidth=" + statementRollwidth +
                '}';
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getReceiptConfig() {
        return receiptConfig;
    }

    public void setReceiptConfig(String receiptConfig) {
        this.receiptConfig = receiptConfig;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getReceiptFitness() {
        return receiptFitness;
    }

    public void setReceiptFitness(String receiptFitness) {
        this.receiptFitness = receiptFitness;
    }

    public String getReceiptSupplie() {
        return receiptSupplie;
    }

    public void setReceiptSupplie(String receiptSupplie) {
        this.receiptSupplie = receiptSupplie;
    }

    public int getReceiptRollwidth() {
        return receiptRollwidth;
    }

    public void setReceiptRollwidth(int receiptRollwidth) {
        this.receiptRollwidth = receiptRollwidth;
    }

    public int getReceiptLeftcol() {
        return receiptLeftcol;
    }

    public void setReceiptLeftcol(int receiptLeftcol) {
        this.receiptLeftcol = receiptLeftcol;
    }

    public int getReceiptMaxline() {
        return receiptMaxline;
    }

    public void setReceiptMaxline(int receiptMaxline) {
        this.receiptMaxline = receiptMaxline;
    }

    public String getJournalConfig() {
        return journalConfig;
    }

    public void setJournalConfig(String journalConfig) {
        this.journalConfig = journalConfig;
    }

    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }

    public String getJournalFitness() {
        return journalFitness;
    }

    public void setJournalFitness(String journalFitness) {
        this.journalFitness = journalFitness;
    }

    public String getJournalSupplie() {
        return journalSupplie;
    }

    public void setJournalSupplie(String journalSupplie) {
        this.journalSupplie = journalSupplie;
    }

    public int getJournalTraceonoff() {
        return journalTraceonoff;
    }

    public void setJournalTraceonoff(int journalTraceonoff) {
        this.journalTraceonoff = journalTraceonoff;
    }

    public String getStatementConfig() {
        return statementConfig;
    }

    public void setStatementConfig(String statementConfig) {
        this.statementConfig = statementConfig;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public String getStatementFitness() {
        return statementFitness;
    }

    public void setStatementFitness(String statementFitness) {
        this.statementFitness = statementFitness;
    }

    public String getStatementSupplie() {
        return statementSupplie;
    }

    public void setStatementSupplie(String statementSupplie) {
        this.statementSupplie = statementSupplie;
    }

    public int getStatementRollwidth() {
        return statementRollwidth;
    }

    public void setStatementRollwidth(int statementRollwidth) {
        this.statementRollwidth = statementRollwidth;
    }

    public TerminalPrinter() {
    }

    public TerminalPrinter(String terminalNumber, String receiptConfig, String receiptType, String receiptFitness, String receiptSupplie, int receiptRollwidth, int receiptLeftcol, int receiptMaxline, String journalConfig, String journalType, String journalFitness, String journalSupplie, int journalTraceonoff, String statementConfig, String statementType, String statementFitness, String statementSupplie, int statementRollwidth) {
        this.terminalNumber = terminalNumber;
        this.receiptConfig = receiptConfig;
        this.receiptType = receiptType;
        this.receiptFitness = receiptFitness;
        this.receiptSupplie = receiptSupplie;
        this.receiptRollwidth = receiptRollwidth;
        this.receiptLeftcol = receiptLeftcol;
        this.receiptMaxline = receiptMaxline;
        this.journalConfig = journalConfig;
        this.journalType = journalType;
        this.journalFitness = journalFitness;
        this.journalSupplie = journalSupplie;
        this.journalTraceonoff = journalTraceonoff;
        this.statementConfig = statementConfig;
        this.statementType = statementType;
        this.statementFitness = statementFitness;
        this.statementSupplie = statementSupplie;
        this.statementRollwidth = statementRollwidth;
    }
}
