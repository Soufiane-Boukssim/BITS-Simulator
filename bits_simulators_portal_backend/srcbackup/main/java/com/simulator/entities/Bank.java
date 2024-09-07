package com.simulator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "BANK")
public class Bank {
    @Id
    @Column(name = "BANK_CODE", nullable = false, length = 5)
    private String id;

    @Column(name = "WORDING", length = 30)
    private String wording;

    @Column(name = "CURRENCY_CODE", length = 3)
    private String currencyCode;

    @Column(name = "TVA_RATE", length = 6)
    private String tvaRate;

    @Column(name = "DAYS_BEFORE_RENEW")
    private Integer daysBeforeRenew;

    @Column(name = "ADDRESS", length = 120)
    private String address;

    @Column(name = "COUNTRY_CODE", length = 3)
    private String countryCode;

    @Column(name = "ZIP_CODE", length = 20)
    private String zipCode;

    @Column(name = "CITY_CODE", length = 5)
    private String cityCode;

    @Column(name = "BANK_TYPE")
    private Character bankType;

    @Column(name = "BANK_API_SERVICES", length = 100)
    private String bankApiServices;

    @Column(name = "MASTER_BANK_CODE", length = 5)
    private String masterBankCode;

    @Column(name = "EXPIRATION_FLAG")
    private Character expirationFlag;

    @Column(name = "VERIFICATION_FLAG", length = 1)
    private String verificationFlag;

    @Column(name = "GH_BANK_CODE", length = 6)
    private String ghBankCode;

    public Bank() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTvaRate() {
        return tvaRate;
    }

    public void setTvaRate(String tvaRate) {
        this.tvaRate = tvaRate;
    }

    public Integer getDaysBeforeRenew() {
        return daysBeforeRenew;
    }

    public void setDaysBeforeRenew(Integer daysBeforeRenew) {
        this.daysBeforeRenew = daysBeforeRenew;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Character getBankType() {
        return bankType;
    }

    public void setBankType(Character bankType) {
        this.bankType = bankType;
    }

    public String getBankApiServices() {
        return bankApiServices;
    }

    public void setBankApiServices(String bankApiServices) {
        this.bankApiServices = bankApiServices;
    }

    public String getMasterBankCode() {
        return masterBankCode;
    }

    public void setMasterBankCode(String masterBankCode) {
        this.masterBankCode = masterBankCode;
    }

    public Character getExpirationFlag() {
        return expirationFlag;
    }

    public void setExpirationFlag(Character expirationFlag) {
        this.expirationFlag = expirationFlag;
    }

    public String getVerificationFlag() {
        return verificationFlag;
    }

    public void setVerificationFlag(String verificationFlag) {
        this.verificationFlag = verificationFlag;
    }

    public String getGhBankCode() {
        return ghBankCode;
    }

    public void setGhBankCode(String ghBankCode) {
        this.ghBankCode = ghBankCode;
    }


}
