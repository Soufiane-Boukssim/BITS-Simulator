package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "merchant_param")
public class MerchantParam {

    @EmbeddedId
    private MerchantParamId id;
    @Column(name = "MER_NAME")
    private  String merName;

    @Column(name = "MCC")
    private String mcc;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;


    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public MerchantParam(MerchantParamId id, String merName, String mcc, String city, String countryCode, Character merProtocol) {
        this.id = id;
        this.merName = merName;
        this.mcc = mcc;
        this.city = city;
        this.countryCode = countryCode;
    }

    public MerchantParam() {
    }

    public MerchantParamId getId() {
        return id;
    }

    public void setId(MerchantParamId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MerchantParam{" +
                "id='" + id + '\'' +
                ", merName='" + merName + '\'' +
                ", mccDesc='" + mcc + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
