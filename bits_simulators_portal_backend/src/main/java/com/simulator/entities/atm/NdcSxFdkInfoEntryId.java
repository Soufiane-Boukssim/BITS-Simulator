package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcSxFdkInfoEntryId implements Serializable {

	@Column(name = "PROFILE_CODE")
	private Character profileCode;
	@Column(name = "STATE_NUMBER")
	private Character stateNumber;

	public Character getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(Character profileCode) {
		this.profileCode = profileCode;
	}

	public Character getStateNumber() {
		return stateNumber;
	}

	public void setStateNumber(Character stateNumber) {
		this.stateNumber = stateNumber;
	}
}