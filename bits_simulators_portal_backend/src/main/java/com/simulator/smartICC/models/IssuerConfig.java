package com.simulator.smartICC.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "ISSUER_CONFIG")
public class IssuerConfig {
    @Id
    @Column(name = "code_profile")
    private String codeProfile;

    @Column(name = "Cryptogram")
    private String cryptogram;

    @Column(name = "Key_Type")
    private String keyType;

    @Column(name = "Key_Set")
    private Integer keySet;

    @Column(name = "Payment_Key")
    private String paymentKey;

    @Column(name = "Mac_Key")
    private String macKey;

    @Column(name = "Encryption_Key")
    private String encryptionKey;

    @Column(name = "CV_KP")
    private String cvKp;

    @Column(name = "CV_MAC")
    private String cvMac;

    @Column(name = "CV_KE")
    private String cvKe;
}

