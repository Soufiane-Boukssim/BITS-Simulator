package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SITEROG_SET_ICC_TRX_DATA")
public class NdcSiterogSetIccTrxData {

	@EmbeddedId
	private NdcSiterogSetIccTrxDataId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "GOOD_NEXT_STATE")
	private String goodNextState;
	@Column(name = "CURRENCY_TYPE")
	private String currencyType;
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;
	@Column(name = "AMOUNT_AUTHORISED")
	private String amountAuthorised;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSiterogSetIccTrxDataId getId() {
		return id;
	}

	public void setId(NdcSiterogSetIccTrxDataId id) {
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

	public String getGoodNextState() {
		return goodNextState;
	}

	public void setGoodNextState(String goodNextState) {
		this.goodNextState = goodNextState;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmountAuthorised() {
		return amountAuthorised;
	}

	public void setAmountAuthorised(String amountAuthorised) {
		this.amountAuthorised = amountAuthorised;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}