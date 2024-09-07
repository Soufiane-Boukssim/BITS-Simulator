package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SC_UNLOCK_DISPENSER")
public class NdcScUnlockDispenser {

	@EmbeddedId
	private NdcScUnlockDispenserId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "NEXT_STATE")
	private String nextState;
	@Column(name = "RESERVED_STATE")
	private String reservedState;

	public NdcScUnlockDispenserId getId() {
		return id;
	}

	public void setId(NdcScUnlockDispenserId id) {
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

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	public String getReservedState() {
		return reservedState;
	}

	public void setReservedState(String reservedState) {
		this.reservedState = reservedState;
	}
}