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
@Table(name = "STEPS_RESULT")
public class StepsResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "profile_code")
    private String profileCode;

    @Column(name = "RESULT")
    private String RESULT;

    @Column(name = "TEST_CASE")
    private String TEST_CASE;

    @Column(name = "SUB_CASE")
    private String SUB_CASE;

    @Column(name = "STEP")
    private String STEP;

    @Column(name = "STEP_CODE")
    private String STEP_CODE;

    @Column(name = "spaceName")
    private String spaceName;

    @Column(name = "method")
    private String method;

    @Column(name = "arg1")
    private String arg1;

    @Column(name = "arg2")
    private String arg2;

    @Column(name = "arg3")
    private String arg3;

    @Column(name = "arg4")
    private String arg4;

    @Column(name = "arg5")
    private String arg5;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "status")
    private String status;

    @Column(name = "remark")
    private String remark;

}
