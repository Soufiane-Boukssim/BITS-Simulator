package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@Data
@Entity
public class GroupHabilitation {
    @Id
    @Column(name = "GROUP_CODE", length = 50)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    String groupCode;
    String groupName;
    String menuCode;

    public GroupHabilitation(String groupName, String menuCode) {
        this.groupName = groupName;
        this.menuCode = menuCode;
    }
}
