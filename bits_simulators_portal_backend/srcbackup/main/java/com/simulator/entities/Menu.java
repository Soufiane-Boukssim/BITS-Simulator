package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "MENU_NAME", nullable = false, length = 50)
    private String id;

    @Column(name = "PARENT_MENU_NAME", length = 50)
    private String parentMenuName;

    @Column(name = "PATH", length = 100)
    private String path;

    @Column(name = "TITLE", length = 50)
    private String title;

    @Column(name = "MODEL", length = 20)
    private String model;

}
