package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COUNTRY")
public class Country {
    @Id
    @Column(name = "CURRENCY_CODE", nullable = false, length = 3)
    private String id;

    @Column(name = "WORDING", length = 30)
    private String wording;

    @Column(name = "EXPONENT")
    private Integer exponent;

    @Column(name = "ISO_CURRENCY_ALPHA", length = 3)
    private String isoCurrencyAlpha;

    @Column(name = "COUNTRY_CODE", length = 3)
    private String countryCode;

    @Column(name = "WORDING_COUNTRY", length = 30)
    private String wordingCountry;

    @Column(name = "COUNTRY_LABEL_VISA", length = 2)
    private String countryLabelVisa;

    @Column(name = "ISO_COUNTRY_ALPHA", length = 3)
    private String isoCountryAlpha;


    public void setId(String id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public Integer getExponent() {
        return exponent;
    }

    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

    public String getIsoCurrencyAlpha() {
        return isoCurrencyAlpha;
    }

    public void setIsoCurrencyAlpha(String isoCurrencyAlpha) {
        this.isoCurrencyAlpha = isoCurrencyAlpha;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getWordingCountry() {
        return wordingCountry;
    }

    public void setWordingCountry(String wordingCountry) {
        this.wordingCountry = wordingCountry;
    }

    public String getCountryLabelVisa() {
        return countryLabelVisa;
    }

    public void setCountryLabelVisa(String countryLabelVisa) {
        this.countryLabelVisa = countryLabelVisa;
    }

    public String getIsoCountryAlpha() {
        return isoCountryAlpha;
    }

    public void setIsoCountryAlpha(String isoCountryAlpha) {
        this.isoCountryAlpha = isoCountryAlpha;
    }


    public String getId() {
        return id;
    }
}
