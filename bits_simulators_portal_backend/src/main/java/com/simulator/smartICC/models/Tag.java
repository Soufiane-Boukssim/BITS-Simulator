package com.simulator.smartICC.models;

// Tag.java


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
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tags;
    private String codeTags;

    // getters and setters
}
