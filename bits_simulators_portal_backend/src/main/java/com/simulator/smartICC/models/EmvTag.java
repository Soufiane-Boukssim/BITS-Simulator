package com.simulator.smartICC.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMVTAG")
public class EmvTag {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "TAG")
    private String tag;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SHORTNAME")
    private String shortName;
    @Column(name = "FORMAT")
    private String format;
    @Column(name = "LENGTH")
    private String length;
    @Column(name = "SOURCE")
    private String source;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;
    @Column(name = "TEMPLATE")
    private String template;
}
