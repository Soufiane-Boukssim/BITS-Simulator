package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Data
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "modules")
public class Modules {
    @Id
    @Column(name = "code_modules", nullable = false)
    private String id;

    @Column(name = "desc_modules")
    private String descModules;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "wording")
    private String wording;

    @Column(name = "path")
    private String path;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Modules modules = (Modules) o;
        return id != null && Objects.equals(id, modules.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
