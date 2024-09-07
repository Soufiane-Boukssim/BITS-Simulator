package com.simulator.smartICC.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "EMV_PROFILE")
public class EMVProfile {



    @Id
    private String codeProfile;

    private String wording;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateCreate;

    private String activeProfile;



    // Getters and Setters
}

