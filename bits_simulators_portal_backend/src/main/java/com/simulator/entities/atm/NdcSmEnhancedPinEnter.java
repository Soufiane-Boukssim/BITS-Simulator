package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SM_ENHANCED_PIN_ENTER")
public class NdcSmEnhancedPinEnter {

	@EmbeddedId
	private NdcSmEnhancedPinEnterId id;
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
	@Column(name = "LOC_PIN_GOOD_PIN_NEXT_STATE")
	private String locPinGoodPinNextState;
	@Column(name = "LOC_PIN_MAX_BAD_NEXT_STATE")
	private String locPinMaxBadNextState;
	@Column(name = "LOC_PIN_ERROR_SCREEN")
	private String locPinErrorScreen;
	@Column(name = "REM_PIN_CHECK_NEXT_STATE")
	private String remPinCheckNextState;
	@Column(name = "LOC_PIN_MAX_RETRIES")
	private String locPinMaxRetries;

	public NdcSmEnhancedPinEnterId getId() {
		return id;
	}

	public void setId(NdcSmEnhancedPinEnterId id) {
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

	public String getLocPinGoodPinNextState() {
		return locPinGoodPinNextState;
	}

	public void setLocPinGoodPinNextState(String locPinGoodPinNextState) {
		this.locPinGoodPinNextState = locPinGoodPinNextState;
	}

	public String getLocPinMaxBadNextState() {
		return locPinMaxBadNextState;
	}

	public void setLocPinMaxBadNextState(String locPinMaxBadNextState) {
		this.locPinMaxBadNextState = locPinMaxBadNextState;
	}

	public String getLocPinErrorScreen() {
		return locPinErrorScreen;
	}

	public void setLocPinErrorScreen(String locPinErrorScreen) {
		this.locPinErrorScreen = locPinErrorScreen;
	}

	public String getRemPinCheckNextState() {
		return remPinCheckNextState;
	}

	public void setRemPinCheckNextState(String remPinCheckNextState) {
		this.remPinCheckNextState = remPinCheckNextState;
	}

	public String getLocPinMaxRetries() {
		return locPinMaxRetries;
	}

	public void setLocPinMaxRetries(String locPinMaxRetries) {
		this.locPinMaxRetries = locPinMaxRetries;
	}
}