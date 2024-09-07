package com.simulator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ISO_PROFILE")
public class iso {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    private String type_connexion;
    private String type_protocole;
    private int server_port;
    private String server_lip_adr;
    private String reponse_timeout;
    private String echo_test_time;
    private String check_mac;
    private String client_rip_adr;
    private String client_lip_adr;
    private int client_port;
    private String header;
    private String acquirer_bank;
    private int pin_block_format;
    private int key_key;
    private int key_kcv;
    private int pek_key;
    private int pek_kcv;
    private int tak_key;
    private int tak_kcv;
    private int imk_ac_key;
    private int imk_ac_kcv;
    private int imk_smc_key;
    private int imk_smc_kcv;
    private int imk_smi_key;
    private int imk_smi_kcv;
}
