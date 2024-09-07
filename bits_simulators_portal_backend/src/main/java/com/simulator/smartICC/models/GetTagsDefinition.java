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
@Table(name = "Get_Tags_Definition")

public class GetTagsDefinition {

    @Id
    @Column(name = "tagId")
    private String tagId;

    @Column(name = "code_profile")
    private String codeProfile;

    @Column(name = "tag_name")
    private String tagName;

}
