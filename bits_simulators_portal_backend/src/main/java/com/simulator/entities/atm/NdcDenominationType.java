package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "NDC_DENOMINATION_TYPE")
public class NdcDenominationType {

	@EmbeddedId
	private NdcDenominationTypeId id;
	@Column(name = "CURRENCY_TYPE")
	private String currencyType;
	@Column(name = "CURRENCY_VALUE")
	private BigDecimal currencyValue;

	public NdcDenominationTypeId getId() {
		return id;
	}

	public void setId(NdcDenominationTypeId id) {
		this.id = id;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public BigDecimal getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(BigDecimal currencyValue) {
		this.currencyValue = currencyValue;
	}
}