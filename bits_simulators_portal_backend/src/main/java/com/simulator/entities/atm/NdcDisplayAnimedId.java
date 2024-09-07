package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NdcDisplayAnimedId implements Serializable {

	@Column(name = "PROFILE_CODE")
	private String profileCode;
	@Column(name = "ANIMED_SCREEN_NUMBER")
	private String animedScreenNumber;

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getAnimedScreenNumber() {
		return animedScreenNumber;
	}

	public void setAnimedScreenNumber(String animedScreenNumber) {
		this.animedScreenNumber = animedScreenNumber;
	}
}