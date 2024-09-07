package com.simulator.smartICC.models;

import jakarta.persistence.Column;
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
@Table(name = "STEPS_PARAMS")
public class StepsParams {
    @Id
    private String STEP_CODE;



    @Column(name = "STEP_TYPE")
    private String STEP_TYPE;

    @Column(name = "WORDING")
    private String WORDING;



    @Column(name = "NUM_ARGS")
    private String NUM_ARGS;



    @Column(name = "ARG1_LABEL")
    private String ARG1_LABEL;

    @Column(name = "ARG2_LABEL")
    private String ARG2_LABEL;

    @Column(name = "ARG3_LABEL")
    private String ARG3_LABEL;

    @Column(name = "ARG4_LABEL")
    private String ARG4_LABEL;

    @Column(name = "ARG5_LABEL")
    private String ARG5_LABEL;

    @Column(name = "SPACE_NAME")
    private String SPACE_NAME;

}
