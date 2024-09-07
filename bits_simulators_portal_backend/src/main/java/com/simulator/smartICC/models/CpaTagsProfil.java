package com.simulator.smartICC.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CPA_TAGS_PROFIL")
public class CpaTagsProfil {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @Column(name = "profile_code")
    private String profileCode;

    @Column(name = "tag_code")
    private String tagCode;

    @Column(name = "tag_value")
    private String tagValue;
    @Column(name = "wording")
    private String wording;

    @Column(name = "offline_transaction")
    private String offlineTransaction;

}
