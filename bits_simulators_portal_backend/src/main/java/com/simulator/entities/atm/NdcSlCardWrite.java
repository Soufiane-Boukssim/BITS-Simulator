package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SL_CARD_WRITE")
public class NdcSlCardWrite {

	@EmbeddedId
	private NdcSlCardWriteId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;
	@Column(name = "GOOD_WRITE_NEXT_STATE")
	private String goodWriteNextState;
	@Column(name = "BAD_WRITE_NEXT_STATE")
	private String badWriteNextState;
	@Column(name = "NO_WRITE_ATTEMPT_NEXT_STATE")
	private String noWriteAttemptNextState;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSlCardWriteId getId() {
		return id;
	}

	public void setId(NdcSlCardWriteId id) {
		this.id = id;
	}

	public Character getStateType() {
		return stateType;
	}

	public void setStateType(Character stateType) {
		this.stateType = stateType;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public String getGoodWriteNextState() {
		return goodWriteNextState;
	}

	public void setGoodWriteNextState(String goodWriteNextState) {
		this.goodWriteNextState = goodWriteNextState;
	}

	public String getBadWriteNextState() {
		return badWriteNextState;
	}

	public void setBadWriteNextState(String badWriteNextState) {
		this.badWriteNextState = badWriteNextState;
	}

	public String getNoWriteAttemptNextState() {
		return noWriteAttemptNextState;
	}

	public void setNoWriteAttemptNextState(String noWriteAttemptNextState) {
		this.noWriteAttemptNextState = noWriteAttemptNextState;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}