package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Roles {
    @Id
    @Column(name = "ROLE_NAME", nullable = false, length = 50)
    private String id;

    @Column(name = "PARENT_MENU_NAME", length = 50)
    private String parentMenuName;

    @Column(name = "PATHE", length = 100)
    private String pathe;

    @Column(name = "TITLE", length = 50)
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public String getPathe() {
        return pathe;
    }

    public void setPathe(String pathe) {
        this.pathe = pathe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
