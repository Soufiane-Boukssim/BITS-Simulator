// TagProfil.java

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
@Table(name = "TagProfil")
public class TagProfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeProfil;
    private String tags;
    private String codeTags;

    // getters and setters
}
