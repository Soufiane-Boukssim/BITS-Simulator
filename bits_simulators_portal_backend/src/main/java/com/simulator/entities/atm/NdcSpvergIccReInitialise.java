package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SPVERG_ICC_RE_INITIALISE")
public class NdcSpvergIccReInitialise {

	@EmbeddedId
	private NdcSpvergIccReInitialiseId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "GOOD_NEXT_STATE")
	private String goodNextState;
	@Column(name = "PROCESS_NO_PERFORMED_NEXT")
	private String processNoPerformedNext;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSpvergIccReInitialiseId getId() {
		return id;
	}

	public void setId(NdcSpvergIccReInitialiseId id) {
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

	public String getGoodNextState() {
		return goodNextState;
	}

	public void setGoodNextState(String goodNextState) {
		this.goodNextState = goodNextState;
	}

	public String getProcessNoPerformedNext() {
		return processNoPerformedNext;
	}

	public void setProcessNoPerformedNext(String processNoPerformedNext) {
		this.processNoPerformedNext = processNoPerformedNext;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}