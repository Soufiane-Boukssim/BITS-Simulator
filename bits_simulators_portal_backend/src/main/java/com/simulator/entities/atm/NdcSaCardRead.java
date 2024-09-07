package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SA_CARD_READ")
public class NdcSaCardRead {

	@EmbeddedId
	private NdcSaCardReadId id;
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
	@Column(name = "READ_CONDITION1")
	private String readCondition1;
	@Column(name = "READ_CONDITION2")
	private String readCondition2;
	@Column(name = "READ_CONDITION3")
	private String readCondition3;
	@Column(name = "CARD_RETURN_FLAG")
	private String cardReturnFlag;
	@Column(name = "NO_FIT_MATCH_NEXT_STATE")
	private String noFitMatchNextState;

	public NdcSaCardReadId getId() {
		return id;
	}

	public void setId(NdcSaCardReadId id) {
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

	public String getCardReturnFlag() {
		return cardReturnFlag;
	}

	public void setCardReturnFlag(String cardReturnFlag) {
		this.cardReturnFlag = cardReturnFlag;
	}

	public String getNoFitMatchNextState() {
		return noFitMatchNextState;
	}

	public void setNoFitMatchNextState(String noFitMatchNextState) {
		this.noFitMatchNextState = noFitMatchNextState;
	}
}