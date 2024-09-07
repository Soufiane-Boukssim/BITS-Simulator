package com.simulator.smartICC.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "TRANSACTION_CONFIG")
public class TransactionConfig {
    @Id
    @Column(name = "code_profile")
    private String codeProfile;

    @Column(name = "Trx_Type")
    private String trxType;

    @Column(name = "Trx_number")
    private int trxNumber;

    @Column(name = "Trx_Pin")
    private int trxPin;

    @Column(name = "Trx_Amount")
    private int trxAmt;

    @Column(name = "Trx_succ_connect")
    private int trxSuccConnect;

    @Column(name = "Trx_Iss_approved")
    private int trxIssApproved;

    @Column(name = "Trx_Iss_Authent")
    private int trxIssAuthent;
}

