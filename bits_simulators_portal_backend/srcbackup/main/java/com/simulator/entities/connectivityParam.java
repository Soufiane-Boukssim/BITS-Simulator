package com.simulator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Connectivity_Param")
public class connectivityParam {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    private String user_code;
    private char connection_mode;
    private String local_ip_address;
    private String remote_ip_address;
    private Boolean mac_control;
}
