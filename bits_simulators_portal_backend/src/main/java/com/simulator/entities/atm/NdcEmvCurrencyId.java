package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcEmvCurrencyId implements Serializable {

	@Column(name = "EMV_PROFILE")
	private String emvProfile;
	@Column(name = "CURRENCY_CODE")
	private String currencyCode;

	public String getEmvProfile() {
		return emvProfile;
	}

	public void setEmvProfile(String emvProfile) {
		this.emvProfile = emvProfile;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}