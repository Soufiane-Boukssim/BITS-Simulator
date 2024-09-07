package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SPLUS_BEGIN_ICC_INIT")
public class NdcSplusBeginIccInit {

	@EmbeddedId
	private NdcSplusBeginIccInitId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "ICC_INIT_STARTED_NEXT")
	private String iccInitStartedNext;
	@Column(name = "NO_ICC_INIT_STARTED_NEXT")
	private String noIccInitStartedNext;
	@Column(name = "ICC_INIT_REQUIREMENT")
	private String iccInitRequirement;
	@Column(name = "ICC_APP_SELECT_FLAG")
	private String iccAppSelectFlag;
	@Column(name = "ICC_APP_VALIDATE_FLAG")
	private String iccAppValidateFlag;
	@Column(name = "ICC_CARDHOLDE_CONF_FLAG")
	private String iccCardholderConfFlag;
	@Column(name = "ICC_CLEAR_SCREEN_NBR")
	private String iccClearScreenNbr;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSplusBeginIccInitId getId() {
		return id;
	}

	public void setId(NdcSplusBeginIccInitId id) {
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

	public String getIccInitStartedNext() {
		return iccInitStartedNext;
	}

	public void setIccInitStartedNext(String iccInitStartedNext) {
		this.iccInitStartedNext = iccInitStartedNext;
	}

	public String getNoIccInitStartedNext() {
		return noIccInitStartedNext;
	}

	public void setNoIccInitStartedNext(String noIccInitStartedNext) {
		this.noIccInitStartedNext = noIccInitStartedNext;
	}

	public String getIccInitRequirement() {
		return iccInitRequirement;
	}

	public void setIccInitRequirement(String iccInitRequirement) {
		this.iccInitRequirement = iccInitRequirement;
	}

	public String getIccAppSelectFlag() {
		return iccAppSelectFlag;
	}

	public void setIccAppSelectFlag(String iccAppSelectFlag) {
		this.iccAppSelectFlag = iccAppSelectFlag;
	}

	public String getIccAppValidateFlag() {
		return iccAppValidateFlag;
	}

	public void setIccAppValidateFlag(String iccAppValidateFlag) {
		this.iccAppValidateFlag = iccAppValidateFlag;
	}

	public String getIccCardholderConfFlag() {
		return iccCardholderConfFlag;
	}

	public void setIccCardholderConfFlag(String iccCardholderConfFlag) {
		this.iccCardholderConfFlag = iccCardholderConfFlag;
	}

	public String getIccClearScreenNbr() {
		return iccClearScreenNbr;
	}

	public void setIccClearScreenNbr(String iccClearScreenNbr) {
		this.iccClearScreenNbr = iccClearScreenNbr;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}