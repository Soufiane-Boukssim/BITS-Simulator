package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcDisplayObjectId implements Serializable {

	@Column(name = "PROFILE_CODE")
	private String profileCode;
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}
}