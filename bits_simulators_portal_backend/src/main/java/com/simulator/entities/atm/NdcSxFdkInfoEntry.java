package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SX_FDK_INFO_ENTRY")
public class NdcSxFdkInfoEntry {

	@EmbeddedId
	private NdcSxFdkInfoEntryId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "SCREEN_NUMBER")
	private Character screenNumber;
	@Column(name = "TIMEOUT_NEXT_STATE")
	private Character timeoutNextState;
	@Column(name = "CANCEL_NEXT_STATE")
	private Character cancelNextState;
	@Column(name = "FDK_NEXT_STATE")
	private Character fdkNextState;
	@Column(name = "EXTENSION_STATE")
	private Character extensionState;
	@Column(name = "BUFFER_ID")
	private Character bufferId;
	@Column(name = "FDK_ACTIVE_MASK")
	private Character fdkActiveMask;
	@Column(name = "RESERVED")
	private Character reserved;
	@Column(name = "EXTENSION_TYPE_STATE")
	private Character extensionTypeState;
	@Column(name = "VALUE_STORED_IF_FDK_A")
	private Character valueStoredIfFdkA;
	@Column(name = "VALUE_STORED_IF_FDK_B")
	private Character valueStoredIfFdkB;
	@Column(name = "VALUE_STORED_IF_FDK_C")
	private Character valueStoredIfFdkC;
	@Column(name = "VALUE_STORED_IF_FDK_D")
	private Character valueStoredIfFdkD;
	@Column(name = "VALUE_STORED_IF_FDK_F")
	private Character valueStoredIfFdkF;
	@Column(name = "VALUE_STORED_IF_FDK_G")
	private Character valueStoredIfFdkG;
	@Column(name = "VALUE_STORED_IF_FDK_H")
	private Character valueStoredIfFdkH;
	@Column(name = "VALUE_STORED_IF_FDK_I")
	private Character valueStoredIfFdkI;

	public NdcSxFdkInfoEntryId getId() {
		return id;
	}

	public void setId(NdcSxFdkInfoEntryId id) {
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

	public Character getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(Character screenNumber) {
		this.screenNumber = screenNumber;
	}

	public Character getTimeoutNextState() {
		return timeoutNextState;
	}

	public void setTimeoutNextState(Character timeoutNextState) {
		this.timeoutNextState = timeoutNextState;
	}

	public Character getCancelNextState() {
		return cancelNextState;
	}

	public void setCancelNextState(Character cancelNextState) {
		this.cancelNextState = cancelNextState;
	}

	public Character getFdkNextState() {
		return fdkNextState;
	}

	public void setFdkNextState(Character fdkNextState) {
		this.fdkNextState = fdkNextState;
	}

	public Character getExtensionState() {
		return extensionState;
	}

	public void setExtensionState(Character extensionState) {
		this.extensionState = extensionState;
	}

	public Character getBufferId() {
		return bufferId;
	}

	public void setBufferId(Character bufferId) {
		this.bufferId = bufferId;
	}

	public Character getFdkActiveMask() {
		return fdkActiveMask;
	}

	public void setFdkActiveMask(Character fdkActiveMask) {
		this.fdkActiveMask = fdkActiveMask;
	}

	public Character getReserved() {
		return reserved;
	}

	public void setReserved(Character reserved) {
		this.reserved = reserved;
	}

	public Character getExtensionTypeState() {
		return extensionTypeState;
	}

	public void setExtensionTypeState(Character extensionTypeState) {
		this.extensionTypeState = extensionTypeState;
	}

	public Character getValueStoredIfFdkA() {
		return valueStoredIfFdkA;
	}

	public void setValueStoredIfFdkA(Character valueStoredIfFdkA) {
		this.valueStoredIfFdkA = valueStoredIfFdkA;
	}

	public Character getValueStoredIfFdkB() {
		return valueStoredIfFdkB;
	}

	public void setValueStoredIfFdkB(Character valueStoredIfFdkB) {
		this.valueStoredIfFdkB = valueStoredIfFdkB;
	}

	public Character getValueStoredIfFdkC() {
		return valueStoredIfFdkC;
	}

	public void setValueStoredIfFdkC(Character valueStoredIfFdkC) {
		this.valueStoredIfFdkC = valueStoredIfFdkC;
	}

	public Character getValueStoredIfFdkD() {
		return valueStoredIfFdkD;
	}

	public void setValueStoredIfFdkD(Character valueStoredIfFdkD) {
		this.valueStoredIfFdkD = valueStoredIfFdkD;
	}

	public Character getValueStoredIfFdkF() {
		return valueStoredIfFdkF;
	}

	public void setValueStoredIfFdkF(Character valueStoredIfFdkF) {
		this.valueStoredIfFdkF = valueStoredIfFdkF;
	}

	public Character getValueStoredIfFdkG() {
		return valueStoredIfFdkG;
	}

	public void setValueStoredIfFdkG(Character valueStoredIfFdkG) {
		this.valueStoredIfFdkG = valueStoredIfFdkG;
	}

	public Character getValueStoredIfFdkH() {
		return valueStoredIfFdkH;
	}

	public void setValueStoredIfFdkH(Character valueStoredIfFdkH) {
		this.valueStoredIfFdkH = valueStoredIfFdkH;
	}

	public Character getValueStoredIfFdkI() {
		return valueStoredIfFdkI;
	}

	public void setValueStoredIfFdkI(Character valueStoredIfFdkI) {
		this.valueStoredIfFdkI = valueStoredIfFdkI;
	}
}