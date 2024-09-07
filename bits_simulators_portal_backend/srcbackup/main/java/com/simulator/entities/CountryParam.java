package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "country_param")
public class CountryParam {
    @Id
    @Column(name = "COUNTRY_CODE", nullable = false, length = 3)
    private String id;

    @Column(name = "COUNTRY_DESC", length = 70)
    private String countryDesc;

    @Column(name = "COUNTRY_ALPHA2", length = 2)
    private String countryAlpha2;

    @Column(name = "COUNTRY_ALPHA3", length = 3)
    private String countryAlpha3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryDesc() {
        return countryDesc;
    }

    public void setCountryDesc(String countryDesc) {
        this.countryDesc = countryDesc;
    }

    public String getCountryAlpha2() {
        return countryAlpha2;
    }

    public void setCountryAlpha2(String countryAlpha2) {
        this.countryAlpha2 = countryAlpha2;
    }

    public String getCountryAlpha3() {
        return countryAlpha3;
    }

    public void setCountryAlpha3(String countryAlpha3) {
        this.countryAlpha3 = countryAlpha3;
    }

}
