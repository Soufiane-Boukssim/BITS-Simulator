package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcEmvApplicationId implements Serializable {

	@Column(name = "EMV_PROFILE")
	private String emvProfile;
	@Column(name = "ENTRY_NUMBER")
	private String entryNumber;

	public String getEmvProfile() {
		return emvProfile;
	}

	public void setEmvProfile(String emvProfile) {
		this.emvProfile = emvProfile;
	}

	public String getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}
}