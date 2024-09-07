package com.simulator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity

@Table(name = "account_def")
public class AccountDef {

    @EmbeddedId
    private AccountDefId id;
    @Column(name = "ACC_DESC", length = 50)
    private String accDesc;


    public String getAccDesc() {
        return accDesc;
    }

    public void setAccDesc(String accDesc) {
        this.accDesc = accDesc;
    }

    public AccountDefId getId() {
        return id;
    }

    public void setId(AccountDefId id) {
        this.id = id;
    }

    public AccountDef() {
    }

    public AccountDef(AccountDefId id, String accDesc) {
        this.id = id;
        this.accDesc = accDesc;
    }


}