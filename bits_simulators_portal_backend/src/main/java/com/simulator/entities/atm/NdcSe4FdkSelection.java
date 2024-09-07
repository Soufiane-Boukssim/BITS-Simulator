package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SE_4FDK_SELECTION")
public class NdcSe4FdkSelection {

	@EmbeddedId
	private NdcSe4FdkSelectionId id;
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
	private String fdkAIState;
	@Column(name = "FDK_B_H_NEXT_STATE")
	private String fdkBHState;
	@Column(name = "FDK_C_G_NEXT_STATE")
	private String fdkCGState;
	@Column(name = "FDK_D_F_NEXT_STATE")
	private String fdkDFState;
	@Column(name = "BUFFER_LOCATION")
	private String bufferLocation;

	public NdcSe4FdkSelectionId getId() {
		return id;
	}

	public void setId(NdcSe4FdkSelectionId id) {
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

	public String getFdkAIState() {
		return fdkAIState;
	}

	public void setFdkAIState(String fdkAIState) {
		this.fdkAIState = fdkAIState;
	}

	public String getFdkBHState() {
		return fdkBHState;
	}

	public void setFdkBHState(String fdkBHState) {
		this.fdkBHState = fdkBHState;
	}

	public String getFdkCGState() {
		return fdkCGState;
	}

	public void setFdkCGState(String fdkCGState) {
		this.fdkCGState = fdkCGState;
	}

	public String getFdkDFState() {
		return fdkDFState;
	}

	public void setFdkDFState(String fdkDFState) {
		this.fdkDFState = fdkDFState;
	}

	public String getBufferLocation() {
		return bufferLocation;
	}

	public void setBufferLocation(String bufferLocation) {
		this.bufferLocation = bufferLocation;
	}
}