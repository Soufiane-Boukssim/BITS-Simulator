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
@Table(name = "TEST_CASES")
public class TestCases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_code")
    private String profileCode;


    @Column(name = "test_case")
    private String test_case;

    @Column(name = "sub_case")
    private String sub_case;

    @Column(name = "wording")
    private String wording;

    @Column(name = "description")
    private String description;

    @Column(name = "exec_seq")
    private int exec_seq;
}
