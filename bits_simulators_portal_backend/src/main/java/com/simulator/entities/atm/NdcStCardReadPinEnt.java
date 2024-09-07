package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_ST_CARD_READ_PIN_ENT")
public class NdcStCardReadPinEnt {

	@EmbeddedId
	private NdcStCardReadPinEntId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;
	@Column(name = "GOOD_READ_NEXT_STATE")
	private String goodReadNextState;
	@Column(name = "ERROR_SCREEN")
	private String errorScreen;
	@Column(name = "READ_CONDITION_1")
	private String readCondition1;
	@Column(name = "READ_CONDITION_2")
	private String readCondition2;
	@Column(name = "READ_CONDITION_3")
	private String readCondition3;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "EXTENSION_STATE")
	private String extensionState;
	@Column(name = "EXTENSION_STATE_TYPE")
	private Character extensionStateType;
	@Column(name = "EXTENSION_SCREEN")
	private String extensionScreen;
	@Column(name = "NO_FIT_MATCH_NEXT_STATE")
	private String noFitMatchNextState;
	@Column(name = "CLEAR_KEY_MASK")
	private String clearKeyMask;
	@Column(name = "ACCEPT_KEY_MASK")
	private String acceptKeyMask;
	@Column(name = "RESERVED_2")
	private String reserved2;

	public NdcStCardReadPinEntId getId() {
		return id;
	}

	public void setId(NdcStCardReadPinEntId id) {
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

	public String getGoodReadNextState() {
		return goodReadNextState;
	}

	public void setGoodReadNextState(String goodReadNextState) {
		this.goodReadNextState = goodReadNextState;
	}

	public String getErrorScreen() {
		return errorScreen;
	}

	public void setErrorScreen(String errorScreen) {
		this.errorScreen = errorScreen;
	}

	public String getReadCondition1() {
		return readCondition1;
	}

	public void setReadCondition1(String readCondition1) {
		this.readCondition1 = readCondition1;
	}

	public String getReadCondition2() {
		return readCondition2;
	}

	public void setReadCondition2(String readCondition2) {
		this.readCondition2 = readCondition2;
	}

	public String getReadCondition3() {
		return readCondition3;
	}

	public void setReadCondition3(String readCondition3) {
		this.readCondition3 = readCondition3;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getExtensionState() {
		return extensionState;
	}

	public void setExtensionState(String extensionState) {
		this.extensionState = extensionState;
	}

	public Character getExtensionStateType() {
		return extensionStateType;
	}

	public void setExtensionStateType(Character extensionStateType) {
		this.extensionStateType = extensionStateType;
	}

	public String getExtensionScreen() {
		return extensionScreen;
	}

	public void setExtensionScreen(String extensionScreen) {
		this.extensionScreen = extensionScreen;
	}

	public String getNoFitMatchNextState() {
		return noFitMatchNextState;
	}

	public void setNoFitMatchNextState(String noFitMatchNextState) {
		this.noFitMatchNextState = noFitMatchNextState;
	}

	public String getClearKeyMask() {
		return clearKeyMask;
	}

	public void setClearKeyMask(String clearKeyMask) {
		this.clearKeyMask = clearKeyMask;
	}

	public String getAcceptKeyMask() {
		return acceptKeyMask;
	}

	public void setAcceptKeyMask(String acceptKeyMask) {
		this.acceptKeyMask = acceptKeyMask;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
}