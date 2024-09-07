package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SH_INFO_ENTRY")
public class NdcShInfoEntry {

	@EmbeddedId
	private NdcShInfoEntryId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;
	@Column(name = "TIMEOUT_NEXT_STATE")
	private String timeoutNextState;
	@Column(name = "CANCEL_NEXT_STATE")
	private String cancelNextState;
	@Column(name = "FDK_A_I_NEXT_STATE")
	private String fdkAInextState;
	@Column(name = "FDK_B_H_NEXT_STATE")
	private String fdkBHnextState;
	@Column(name = "FDK_C_G_NEXT_STATE")
	private String fdkCGnextState;
	@Column(name = "FDK_D_F_NEXT_STATE")
	private String fdkDFnextState;
	@Column(name = "BUFFER_AND_DISPLAY_PARAM")
	private String bufferAndDisplayParam;

	public NdcShInfoEntryId getId() {
		return id;
	}

	public void setId(NdcShInfoEntryId id) {
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

	public String getTimeoutNextState() {
		return timeoutNextState;
	}

	public void setTimeoutNextState(String timeoutNextState) {
		this.timeoutNextState = timeoutNextState;
	}

	public String getCancelNextState() {
		return cancelNextState;
	}

	public void setCancelNextState(String cancelNextState) {
		this.cancelNextState = cancelNextState;
	}

	public String getFdkAInextState() {
		return fdkAInextState;
	}

	public void setFdkAInextState(String fdkAInextState) {
		this.fdkAInextState = fdkAInextState;
	}

	public String getFdkBHnextState() {
		return fdkBHnextState;
	}

	public void setFdkBHnextState(String fdkBHnextState) {
		this.fdkBHnextState = fdkBHnextState;
	}

	public String getFdkCGnextState() {
		return fdkCGnextState;
	}

	public void setFdkCGnextState(String fdkCGnextState) {
		this.fdkCGnextState = fdkCGnextState;
	}

	public String getFdkDFnextState() {
		return fdkDFnextState;
	}

	public void setFdkDFnextState(String fdkDFnextState) {
		this.fdkDFnextState = fdkDFnextState;
	}

	public String getBufferAndDisplayParam() {
		return bufferAndDisplayParam;
	}

	public void setBufferAndDisplayParam(String bufferAndDisplayParam) {
		this.bufferAndDisplayParam = bufferAndDisplayParam;
	}
}