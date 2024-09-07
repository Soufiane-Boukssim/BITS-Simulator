package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SY_8FDK_SEL_FUNCT")
public class NdcSy8FdkSelFunct {

	@EmbeddedId
	private NdcSy8FdkSelFunctId id;
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
	@Column(name = "BUFFER_POSITION")
	private Character bufferPosition;
	@Column(name = "FDK_ACTIVE_MASK")
	private Character fdkActiveMask;
	@Column(name = "MULTI_LANG_EXT_STATE")
	private Character multiLangExtState;
	@Column(name = "INCLUDE_EXTENSION_STATE")
	private Character includeExtensionState;
	@Column(name = "EXTENSION_STATE_TYPE")
	private Character extensionStateType;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_A")
	private Character cdOperationStoredIfFdkA;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_B")
	private Character cdOperationStoredIfFdkB;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_C")
	private Character cdOperationStoredIfFdkC;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_D")
	private Character cdOperationStoredIfFdkD;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_F")
	private Character cdOperationStoredIfFdkF;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_G")
	private Character cdOperationStoredIfFdkG;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_H")
	private Character cdOperationStoredIfFdkH;
	@Column(name = "CD_OPERATION_STORED_IF_FDK_I")
	private Character cdOperationStoredIfFdkI;
	@Column(name = "EXTENSION_STATE_TYPE_2")
	private Character extensionStateType2;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_A")
	private Character screenBaseStoredIfFdkA;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_B")
	private Character screenBaseStoredIfFdkB;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_C")
	private Character screenBaseStoredIfFdkC;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_D")
	private Character screenBaseStoredIfFdkD;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_F")
	private Character screenBaseStoredIfFdkF;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_G")
	private Character screenBaseStoredIfFdkG;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_H")
	private Character screenBaseStoredIfFdkH;
	@Column(name = "SCREEN_BASE_STORED_IF_FDK_I")
	private Character screenBaseStoredIfFdkI;

	public NdcSy8FdkSelFunctId getId() {
		return id;
	}

	public void setId(NdcSy8FdkSelFunctId id) {
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

	public Character getBufferPosition() {
		return bufferPosition;
	}

	public void setBufferPosition(Character bufferPosition) {
		this.bufferPosition = bufferPosition;
	}

	public Character getFdkActiveMask() {
		return fdkActiveMask;
	}

	public void setFdkActiveMask(Character fdkActiveMask) {
		this.fdkActiveMask = fdkActiveMask;
	}

	public Character getMultiLangExtState() {
		return multiLangExtState;
	}

	public void setMultiLangExtState(Character multiLangExtState) {
		this.multiLangExtState = multiLangExtState;
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

	public Character getCdOperationStoredIfFdkA() {
		return cdOperationStoredIfFdkA;
	}

	public void setCdOperationStoredIfFdkA(Character cdOperationStoredIfFdkA) {
		this.cdOperationStoredIfFdkA = cdOperationStoredIfFdkA;
	}

	public Character getCdOperationStoredIfFdkB() {
		return cdOperationStoredIfFdkB;
	}

	public void setCdOperationStoredIfFdkB(Character cdOperationStoredIfFdkB) {
		this.cdOperationStoredIfFdkB = cdOperationStoredIfFdkB;
	}

	public Character getCdOperationStoredIfFdkC() {
		return cdOperationStoredIfFdkC;
	}

	public void setCdOperationStoredIfFdkC(Character cdOperationStoredIfFdkC) {
		this.cdOperationStoredIfFdkC = cdOperationStoredIfFdkC;
	}

	public Character getCdOperationStoredIfFdkD() {
		return cdOperationStoredIfFdkD;
	}

	public void setCdOperationStoredIfFdkD(Character cdOperationStoredIfFdkD) {
		this.cdOperationStoredIfFdkD = cdOperationStoredIfFdkD;
	}

	public Character getCdOperationStoredIfFdkF() {
		return cdOperationStoredIfFdkF;
	}

	public void setCdOperationStoredIfFdkF(Character cdOperationStoredIfFdkF) {
		this.cdOperationStoredIfFdkF = cdOperationStoredIfFdkF;
	}

	public Character getCdOperationStoredIfFdkG() {
		return cdOperationStoredIfFdkG;
	}

	public void setCdOperationStoredIfFdkG(Character cdOperationStoredIfFdkG) {
		this.cdOperationStoredIfFdkG = cdOperationStoredIfFdkG;
	}

	public Character getCdOperationStoredIfFdkH() {
		return cdOperationStoredIfFdkH;
	}

	public void setCdOperationStoredIfFdkH(Character cdOperationStoredIfFdkH) {
		this.cdOperationStoredIfFdkH = cdOperationStoredIfFdkH;
	}

	public Character getCdOperationStoredIfFdkI() {
		return cdOperationStoredIfFdkI;
	}

	public void setCdOperationStoredIfFdkI(Character cdOperationStoredIfFdkI) {
		this.cdOperationStoredIfFdkI = cdOperationStoredIfFdkI;
	}

	public Character getExtensionStateType2() {
		return extensionStateType2;
	}

	public void setExtensionStateType2(Character extensionStateType2) {
		this.extensionStateType2 = extensionStateType2;
	}

	public Character getScreenBaseStoredIfFdkA() {
		return screenBaseStoredIfFdkA;
	}

	public void setScreenBaseStoredIfFdkA(Character screenBaseStoredIfFdkA) {
		this.screenBaseStoredIfFdkA = screenBaseStoredIfFdkA;
	}

	public Character getScreenBaseStoredIfFdkB() {
		return screenBaseStoredIfFdkB;
	}

	public void setScreenBaseStoredIfFdkB(Character screenBaseStoredIfFdkB) {
		this.screenBaseStoredIfFdkB = screenBaseStoredIfFdkB;
	}

	public Character getScreenBaseStoredIfFdkC() {
		return screenBaseStoredIfFdkC;
	}

	public void setScreenBaseStoredIfFdkC(Character screenBaseStoredIfFdkC) {
		this.screenBaseStoredIfFdkC = screenBaseStoredIfFdkC;
	}

	public Character getScreenBaseStoredIfFdkD() {
		return screenBaseStoredIfFdkD;
	}

	public void setScreenBaseStoredIfFdkD(Character screenBaseStoredIfFdkD) {
		this.screenBaseStoredIfFdkD = screenBaseStoredIfFdkD;
	}

	public Character getScreenBaseStoredIfFdkF() {
		return screenBaseStoredIfFdkF;
	}

	public void setScreenBaseStoredIfFdkF(Character screenBaseStoredIfFdkF) {
		this.screenBaseStoredIfFdkF = screenBaseStoredIfFdkF;
	}

	public Character getScreenBaseStoredIfFdkG() {
		return screenBaseStoredIfFdkG;
	}

	public void setScreenBaseStoredIfFdkG(Character screenBaseStoredIfFdkG) {
		this.screenBaseStoredIfFdkG = screenBaseStoredIfFdkG;
	}

	public Character getScreenBaseStoredIfFdkH() {
		return screenBaseStoredIfFdkH;
	}

	public void setScreenBaseStoredIfFdkH(Character screenBaseStoredIfFdkH) {
		this.screenBaseStoredIfFdkH = screenBaseStoredIfFdkH;
	}

	public Character getScreenBaseStoredIfFdkI() {
		return screenBaseStoredIfFdkI;
	}

	public void setScreenBaseStoredIfFdkI(Character screenBaseStoredIfFdkI) {
		this.screenBaseStoredIfFdkI = screenBaseStoredIfFdkI;
	}
}