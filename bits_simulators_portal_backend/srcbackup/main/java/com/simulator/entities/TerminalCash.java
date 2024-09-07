package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_cash")
@NamedQueries({
        @NamedQuery(name = "TerminalCash.existsByTerminalNumber", query = "select (count(t) > 0) from TerminalCash t where t.terminalNumber = :terminalNumber")
})
public class TerminalCash {
    @Id
    @Column(name = "terminal_number", nullable = false,length = 15)
    private String terminalNumber;

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
    public String toString() {
        return "TerminalCash{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", hcashType='" + hcashType + '\'' +
                ", hcashConfig='" + hcashConfig + '\'' +
                ", hcashFitness='" + hcashFitness + '\'' +
                ", hcashSupplies='" + hcashSupplies + '\'' +
                ", hcashReject='" + hcashReject + '\'' +
                ", cassette1Config='" + cassette1Config + '\'' +
                ", cassette1Fitness='" + cassette1Fitness + '\'' +
                ", cassette1Supplies='" + cassette1Supplies + '\'' +
                ", cassette2Config='" + cassette2Config + '\'' +
                ", cassette2Fitness='" + cassette2Fitness + '\'' +
                ", cassette2Supplies='" + cassette2Supplies + '\'' +
                ", cassette3Config='" + cassette3Config + '\'' +
                ", cassette3Fitness='" + cassette3Fitness + '\'' +
                ", cassette3Supplies='" + cassette3Supplies + '\'' +
                ", cassette4Config='" + cassette4Config + '\'' +
                ", cassette4Fitness='" + cassette4Fitness + '\'' +
                ", cassette4Supplies='" + cassette4Supplies + '\'' +
                '}';
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getHcashType() {
        return hcashType;
    }

    public void setHcashType(String hcashType) {
        this.hcashType = hcashType;
    }

    public String getHcashConfig() {
        return hcashConfig;
    }

    public void setHcashConfig(String hcashConfig) {
        this.hcashConfig = hcashConfig;
    }

    public String getHcashFitness() {
        return hcashFitness;
    }

    public void setHcashFitness(String hcashFitness) {
        this.hcashFitness = hcashFitness;
    }

    public String getHcashSupplies() {
        return hcashSupplies;
    }

    public void setHcashSupplies(String hcashSupplies) {
        this.hcashSupplies = hcashSupplies;
    }

    public String getHcashReject() {
        return hcashReject;
    }

    public void setHcashReject(String hcashReject) {
        this.hcashReject = hcashReject;
    }

    public String getCassette1Config() {
        return cassette1Config;
    }

    public void setCassette1Config(String cassette1Config) {
        this.cassette1Config = cassette1Config;
    }

    public String getCassette1Fitness() {
        return cassette1Fitness;
    }

    public void setCassette1Fitness(String cassette1Fitness) {
        this.cassette1Fitness = cassette1Fitness;
    }

    public String getCassette1Supplies() {
        return cassette1Supplies;
    }

    public void setCassette1Supplies(String cassette1Supplies) {
        this.cassette1Supplies = cassette1Supplies;
    }

    public String getCassette2Config() {
        return cassette2Config;
    }

    public void setCassette2Config(String cassette2Config) {
        this.cassette2Config = cassette2Config;
    }

    public String getCassette2Fitness() {
        return cassette2Fitness;
    }

    public void setCassette2Fitness(String cassette2Fitness) {
        this.cassette2Fitness = cassette2Fitness;
    }

    public String getCassette2Supplies() {
        return cassette2Supplies;
    }

    public void setCassette2Supplies(String cassette2Supplies) {
        this.cassette2Supplies = cassette2Supplies;
    }

    public String getCassette3Config() {
        return cassette3Config;
    }

    public void setCassette3Config(String cassette3Config) {
        this.cassette3Config = cassette3Config;
    }

    public String getCassette3Fitness() {
        return cassette3Fitness;
    }

    public void setCassette3Fitness(String cassette3Fitness) {
        this.cassette3Fitness = cassette3Fitness;
    }

    public String getCassette3Supplies() {
        return cassette3Supplies;
    }

    public void setCassette3Supplies(String cassette3Supplies) {
        this.cassette3Supplies = cassette3Supplies;
    }

    public String getCassette4Config() {
        return cassette4Config;
    }

    public void setCassette4Config(String cassette4Config) {
        this.cassette4Config = cassette4Config;
    }

    public String getCassette4Fitness() {
        return cassette4Fitness;
    }

    public void setCassette4Fitness(String cassette4Fitness) {
        this.cassette4Fitness = cassette4Fitness;
    }

    public String getCassette4Supplies() {
        return cassette4Supplies;
    }

    public void setCassette4Supplies(String cassette4Supplies) {
        this.cassette4Supplies = cassette4Supplies;
    }

    public TerminalCash() {
    }

    public TerminalCash(String terminalNumber, String hcashType, String hcashConfig, String hcashFitness, String hcashSupplies, String hcashReject, String cassette1Config, String cassette1Fitness, String cassette1Supplies, String cassette2Config, String cassette2Fitness, String cassette2Supplies, String cassette3Config, String cassette3Fitness, String cassette3Supplies, String cassette4Config, String cassette4Fitness, String cassette4Supplies) {
        this.terminalNumber = terminalNumber;
        this.hcashType = hcashType;
        this.hcashConfig = hcashConfig;
        this.hcashFitness = hcashFitness;
        this.hcashSupplies = hcashSupplies;
        this.hcashReject = hcashReject;
        this.cassette1Config = cassette1Config;
        this.cassette1Fitness = cassette1Fitness;
        this.cassette1Supplies = cassette1Supplies;
        this.cassette2Config = cassette2Config;
        this.cassette2Fitness = cassette2Fitness;
        this.cassette2Supplies = cassette2Supplies;
        this.cassette3Config = cassette3Config;
        this.cassette3Fitness = cassette3Fitness;
        this.cassette3Supplies = cassette3Supplies;
        this.cassette4Config = cassette4Config;
        this.cassette4Fitness = cassette4Fitness;
        this.cassette4Supplies = cassette4Supplies;
    }
}
