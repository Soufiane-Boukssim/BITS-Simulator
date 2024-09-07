package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcDenominationTypeId implements Serializable {

	@Column(name = "NDC_CASSETTE_PROFILE")
	private String ndcCassetteProfile;
	@Column(name = "DENOMINATION_TYPE")
	private String denominationType;

	public String getNdcCassetteProfile() {
		return ndcCassetteProfile;
	}

	public void setNdcCassetteProfile(String ndcCassetteProfile) {
		this.ndcCassetteProfile = ndcCassetteProfile;
	}

	public String getDenominationType() {
		return denominationType;
	}

	public void setDenominationType(String denominationType) {
		this.denominationType = denominationType;
	}
}