package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_EMV_CURRENCY")
public class NdcEmvCurrency {

	@EmbeddedId
	private NdcEmvCurrencyId id;
	@Column(name = "CURRENCY_EXPONENT")
	private int currencyExponent;

	public NdcEmvCurrencyId getId() {
		return id;
	}

	public void setId(NdcEmvCurrencyId id) {
		this.id = id;
	}

	public int getCurrencyExponent() {
		return currencyExponent;
	}

	public void setCurrencyExponent(int currencyExponent) {
		this.currencyExponent = currencyExponent;
	}
}