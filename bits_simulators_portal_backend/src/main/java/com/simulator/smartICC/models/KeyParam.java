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
@Table(name = "KEY_PARAM")
public class KeyParam {
    @Id
    private String profile_code;

    private String cryptograme;
    private String payment_Key;
    private String mac_Key;
    private String encryption_Key;
    private String CV_KP;
    private String CV_MAC;
    private String CV_KE;

}
