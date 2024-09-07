package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SK_SMART_FIT_CHECK")
public class NdcSkSmartFitCheck {

	@EmbeddedId
	private NdcSkSmartFitCheckId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "GOOD_READ_NEXT_STATE")
	private String goodReadNextState;
	@Column(name = "RESERVED_2")
	private String reserved2;
	@Column(name = "CARD_RETURN_FLAG")
	private String cardReturnFlag;
	@Column(name = "NO_FIT_MATCH_NEXT_STATE")
	private String noFitMatchNextState;

	public NdcSkSmartFitCheckId getId() {
		return id;
	}

	public void setId(NdcSkSmartFitCheckId id) {
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

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getGoodReadNextState() {
		return goodReadNextState;
	}

	public void setGoodReadNextState(String goodReadNextState) {
		this.goodReadNextState = goodReadNextState;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
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