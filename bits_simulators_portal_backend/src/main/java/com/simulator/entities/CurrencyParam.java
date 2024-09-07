package com.simulator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "currency_param")
public class CurrencyParam {
    @Id
    @Column(name = "CCY_CODE", nullable = false, length = 3)
    private String id;

    @Column(name = "CCY_DESC", length = 70)
    private String ccyDesc;

    @Column(name = "CCY_ALPHA", length = 3)
    private String ccyAlpha;

    @Column(name = "CCY_EXPONENT")
    private Integer ccyExponent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCcyDesc() {
        return ccyDesc;
    }

    public void setCcyDesc(String ccyDesc) {
        this.ccyDesc = ccyDesc;
    }

    public String getCcyAlpha() {
        return ccyAlpha;
    }

    public void setCcyAlpha(String ccyAlpha) {
        this.ccyAlpha = ccyAlpha;
    }

    public Integer getCcyExponent() {
        return ccyExponent;
    }

    public void setCcyExponent(Integer ccyExponent) {
        this.ccyExponent = ccyExponent;
    }

}
