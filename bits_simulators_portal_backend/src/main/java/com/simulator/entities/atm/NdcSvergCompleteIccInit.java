package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SVERG_COMPLETE_ICC_INIT")
public class NdcSvergCompleteIccInit {

	@EmbeddedId
	private NdcSvergCompleteIccInitId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "WAIT_SCREEN_NBR")
	private Character waitScreenNumber;
	@Column(name = "ICC_INIT_SUCCESSFUL_NEXT")
	private Character iccInitSuccessfulNext;
	@Column(name = "CARD_NO_SMART")
	private Character cardNoSmart;
	@Column(name = "NO_USABLE_APP_NEXT")
	private Character noUsableAppNext;
	@Column(name = "ICC_APP_ERROR_NEXT")
	private Character iccAppErrorNext;
	@Column(name = "ICC_HARDWARE_ERROR_NEXT")
	private Character iccHardwareErrorNext;
	@Column(name = "RESERVED")
	private Character reserved;

	public NdcSvergCompleteIccInitId getId() {
		return id;
	}

	public void setId(NdcSvergCompleteIccInitId id) {
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

	public Character getWaitScreenNumber() {
		return waitScreenNumber;
	}

	public void setWaitScreenNumber(Character waitScreenNumber) {
		this.waitScreenNumber = waitScreenNumber;
	}

	public Character getIccInitSuccessfulNext() {
		return iccInitSuccessfulNext;
	}

	public void setIccInitSuccessfulNext(Character iccInitSuccessfulNext) {
		this.iccInitSuccessfulNext = iccInitSuccessfulNext;
	}

	public Character getCardNoSmart() {
		return cardNoSmart;
	}

	public void setCardNoSmart(Character cardNoSmart) {
		this.cardNoSmart = cardNoSmart;
	}

	public Character getNoUsableAppNext() {
		return noUsableAppNext;
	}

	public void setNoUsableAppNext(Character noUsableAppNext) {
		this.noUsableAppNext = noUsableAppNext;
	}

	public Character getIccAppErrorNext() {
		return iccAppErrorNext;
	}

	public void setIccAppErrorNext(Character iccAppErrorNext) {
		this.iccAppErrorNext = iccAppErrorNext;
	}

	public Character getIccHardwareErrorNext() {
		return iccHardwareErrorNext;
	}

	public void setIccHardwareErrorNext(Character iccHardwareErrorNext) {
		this.iccHardwareErrorNext = iccHardwareErrorNext;
	}

	public Character getReserved() {
		return reserved;
	}

	public void setReserved(Character reserved) {
		this.reserved = reserved;
	}
}