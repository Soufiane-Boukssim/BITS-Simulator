package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcEmvTransactionId implements Serializable {

	@Column(name = "EMV_PROFILE")
	private String emvProfile;
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;

	public String getEmvProfile() {
		return emvProfile;
	}

	public void setEmvProfile(String emvProfile) {
		this.emvProfile = emvProfile;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}