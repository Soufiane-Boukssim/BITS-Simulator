package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SB_CUSTOMER_SELECTABLE_PIN")
public class NdcSbCustomerSelectablePin {

	@EmbeddedId
	private NdcSbCustomerSelectablePinId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "FIRST_SCREEN_NUMBER")
	private String firstScreenNumber;
	@Column(name = "TIMEOUT_NEXT_STATE")
	private String timeoutNextState;
	@Column(name = "CANCEL_NEXT_STATE")
	private String cancelNextState;
	@Column(name = "GOOD_NEXT_STATE")
	private String goodNextState;
	@Column(name = "FAIL_NEXT_STATE")
	private String failNextState;
	@Column(name = "SECOND_SCREEN_NUMBER")
	private String secondScreenNumber;
	@Column(name = "SCREEN_MISMATCH")
	private String screenMismatch;
	@Column(name = "EXTENSION_STATE")
	private String extensionState;
	@Column(name = "INCLUDE_EXTENSION_STATE")
	private Character includeExtensionState;
	@Column(name = "EXTENSION_STATE_TYPE")
	private Character extensionStateType;
	@Column(name = "NUMBER_ENTER_MATCHING_CSP")
	private String numberEnterMatchingCsp;
	@Column(name = "LOCAL_VERIF_NEW_PIN_PAIR")
	private String localVerifNewPinPair;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSbCustomerSelectablePinId getId() {
		return id;
	}

	public void setId(NdcSbCustomerSelectablePinId id) {
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

	public String getFirstScreenNumber() {
		return firstScreenNumber;
	}

	public void setFirstScreenNumber(String firstScreenNumber) {
		this.firstScreenNumber = firstScreenNumber;
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

	public String getGoodNextState() {
		return goodNextState;
	}

	public void setGoodNextState(String goodNextState) {
		this.goodNextState = goodNextState;
	}

	public String getFailNextState() {
		return failNextState;
	}

	public void setFailNextState(String failNextState) {
		this.failNextState = failNextState;
	}

	public String getSecondScreenNumber() {
		return secondScreenNumber;
	}

	public void setSecondScreenNumber(String secondScreenNumber) {
		this.secondScreenNumber = secondScreenNumber;
	}

	public String getScreenMismatch() {
		return screenMismatch;
	}

	public void setScreenMismatch(String screenMismatch) {
		this.screenMismatch = screenMismatch;
	}

	public String getExtensionState() {
		return extensionState;
	}

	public void setExtensionState(String extensionState) {
		this.extensionState = extensionState;
	}

	public Character getIncludeExtensionState() {
		return includeExtensionState;
	}

	public void setIncludeExtensionState(Character includeExtensionState) {
		this.includeExtensionState = includeExtensionState;
	}

	public Character getExtensionStateType() {
		return extensionStateType;
	}

	public void setExtensionStateType(Character extensionStateType) {
		this.extensionStateType = extensionStateType;
	}

	public String getNumberEnterMatchingCsp() {
		return numberEnterMatchingCsp;
	}

	public void setNumberEnterMatchingCsp(String numberEnterMatchingCsp) {
		this.numberEnterMatchingCsp = numberEnterMatchingCsp;
	}

	public String getLocalVerifNewPinPair() {
		return localVerifNewPinPair;
	}

	public void setLocalVerifNewPinPair(String localVerifNewPinPair) {
		this.localVerifNewPinPair = localVerifNewPinPair;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}