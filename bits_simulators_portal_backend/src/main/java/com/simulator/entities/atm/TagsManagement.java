package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TAGS_MANAGEMENT")
public class TagsManagement {

    @Id
    @Column(name = "TAG_CODE", length = 10)
    private String tagCode;

    @Column(name = "TAG_NAME", length = 100)
    private String tagName;

    public TagsManagement() {
    }

    public TagsManagement(String tagCode, String tagName) {
        this.tagCode = tagCode;
        this.tagName = tagName;
    }
}
