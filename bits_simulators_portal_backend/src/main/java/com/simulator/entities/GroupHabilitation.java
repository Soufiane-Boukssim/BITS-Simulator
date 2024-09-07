package com.simulator.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GROUP_HABILITATION")
public class GroupHabilitation {
    @EmbeddedId
    GroupHabilitationId groupId;
    String groupName;


    public GroupHabilitation(String groupName,GroupHabilitationId groupId) {
        this.groupName = groupName;
        this.groupId = groupId;

    }
}
