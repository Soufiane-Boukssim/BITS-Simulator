package com.simulator.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class pkIcc implements Serializable {
    private Long icc_code;
    private String icc_tag;
}
