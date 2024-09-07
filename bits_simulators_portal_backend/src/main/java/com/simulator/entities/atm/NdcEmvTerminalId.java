package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcEmvTerminalId implements Serializable {

	@Column(name = "EMV_PROFILE")
	private String emvProfile;
	@Column(name = "TERM_COUNTRY_CODE")
	private String termCountryCode;

	public String getEmvProfile() {
		return emvProfile;
	}

	public void setEmvProfile(String emvProfile) {
		this.emvProfile = emvProfile;
	}

	public String getTermCountryCode() {
		return termCountryCode;
	}

	public void setTermCountryCode(String termCountryCode) {
		this.termCountryCode = termCountryCode;
	}
}