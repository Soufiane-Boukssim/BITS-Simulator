package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CITY")
public class City {
    /*@EmbeddedId
    private CityId id;*/

    @Id
    @Column(name = "CITY_CODE", length = 5)
    private String cityCode;

    @Column(name = "COUNTRY_CODE", length = 3)
    private String countryCode;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;


    @Column(name = "WORDING", length = 30)
    private String wording;


    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    //TODO [JPA Buddy] generate columns from DB
}
