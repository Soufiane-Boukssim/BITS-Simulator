package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Data
@Embeddable
public class CityId implements Serializable {
    private static final long serialVersionUID = 7278825441143269490L;
    @Column(name = "CITY_CODE", length = 5)
    private String cityCode;

    @Column(name = "COUNTRY_CODE", length = 3)
    private String countryCode;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CityId entity = (CityId) o;
        return Objects.equals(this.cityCode, entity.cityCode) &&
                Objects.equals(this.countryCode, entity.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityCode, countryCode);
    }

}
