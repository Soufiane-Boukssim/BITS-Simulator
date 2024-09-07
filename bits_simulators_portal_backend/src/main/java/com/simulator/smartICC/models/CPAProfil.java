package com.simulator.smartICC.models;

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
@Table(name = "CPA_PROFILE")
public class CPAProfil {
    @Id
    private String profile_code;

    private String wording;
    private String bank_code;
    private String bin_id;
    private String cpa_certificate_nbr;
    private String cpa_expiry_certificate;
    private String pse_supported;
    private String application_type;
    private int num_recs_trx_log;
    private String offline_supported;
    private String cam_supported;
    private String offline_pin_supported;
    private String type_pin_verification;
    private String type_pin_rsa;
    private int nbr_pin_verification;
    private String pin_request_flag;
    private String pin_value;
    private String country_code;
    private int num_applications;
    private String pan_seq;
    private String currency_code;
    private String terminal_type;
    private String terminal_id;
    private double amount;
}
