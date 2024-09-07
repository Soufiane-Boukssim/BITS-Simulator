package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@RequiredArgsConstructor
public class GroupHabilitationId {

    @Column (name = "groupCode")
    private String groupCode;

    @Column (name = "menuCode")
    private String menuCode;

}
