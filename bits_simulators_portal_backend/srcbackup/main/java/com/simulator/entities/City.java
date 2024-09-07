package com.simulator.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CITY")
public class City {
    @EmbeddedId
    private CityId id;

    public CityId getId() {
        return id;
    }

    public void setId(CityId id) {
        this.id = id;
    }

//TODO [JPA Buddy] generate columns from DB
}
