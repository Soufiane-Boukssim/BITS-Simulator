package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class PosExchangeRateParamId {

    private static final long serialVersionUID = -495996529353847864L;
    @Column(name = "BASE_CCY", nullable = false, length = 3)
    private String baseCcy;

    @Column(name = "RATE_START_DATE", nullable = false, length = 20)
    private String rateStartDate;

    @Column(name = "CURR_CODE", nullable = false, length = 3)
    private String currCode;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;


    @Column(name = "RATE_PROTOCOL",length = 2)
    private String rateProtocol;

   /* public String getRateProtocol() {
        return rateProtocol;
    }

    public void setRateProtocol(String rateProtocol) {
        this.rateProtocol = rateProtocol;
    }*/

    public PosExchangeRateParamId() {

    }

    public String getRateProtocol() {
        return rateProtocol;
    }

    public void setRateProtocol(String rateProtocol) {
        this.rateProtocol = rateProtocol;
    }




    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }


    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public String getRateStartDate() {
        return rateStartDate;
    }

    public void setRateStartDate(String rateStartDate) {
        this.rateStartDate = rateStartDate;
    }

    public String getCurrCode() {
        return currCode;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public PosExchangeRateParamId(String baseCcy, String rateProtocol ,String rateStartDate, String currCode) {
        this.baseCcy = baseCcy;
        this.rateStartDate = rateStartDate;
        this.currCode = currCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PosExchangeRateParamId entity = (PosExchangeRateParamId) o;
        return Objects.equals(this.rateStartDate, entity.rateStartDate) &&
                Objects.equals(this.currCode, entity.currCode) &&
                Objects.equals(this.baseCcy, entity.baseCcy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rateStartDate, currCode, baseCcy);
    }
}
