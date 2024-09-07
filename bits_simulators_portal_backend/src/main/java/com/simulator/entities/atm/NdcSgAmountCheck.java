package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SG_AMOUNT_CHECK")
public class NdcSgAmountCheck {

	@EmbeddedId
	private NdcSgAmountCheckId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "WHOLE_CUR_UNIT_ENTRY_NEXT")
	private String wholeCurUnitEntryNext;
	@Column(name = "NO_WHOLE_CUR_UNIT_ENTRY_NEXT")
	private String noWholeCurUnitEntryNext;
	@Column(name = "BUFFER_CHECK")
	private String bufferCheck;
	@Column(name = "INT_MULTIPL_VALUE")
	private String intMultiplValue;
	@Column(name = "NBR_DECIMAL_PLACE")
	private String nbrDecimalPlace;
	@Column(name = "CURRENCY_TYPE")
	private String currencyType;
	@Column(name = "AMOUNT_CHECK_CONDITION")
	private String amountCheckCondition;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSgAmountCheckId getId() {
		return id;
	}

	public void setId(NdcSgAmountCheckId id) {
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

	public String getWholeCurUnitEntryNext() {
		return wholeCurUnitEntryNext;
	}

	public void setWholeCurUnitEntryNext(String wholeCurUnitEntryNext) {
		this.wholeCurUnitEntryNext = wholeCurUnitEntryNext;
	}

	public String getNoWholeCurUnitEntryNext() {
		return noWholeCurUnitEntryNext;
	}

	public void setNoWholeCurUnitEntryNext(String noWholeCurUnitEntryNext) {
		this.noWholeCurUnitEntryNext = noWholeCurUnitEntryNext;
	}

	public String getBufferCheck() {
		return bufferCheck;
	}

	public void setBufferCheck(String bufferCheck) {
		this.bufferCheck = bufferCheck;
	}

	public String getIntMultiplValue() {
		return intMultiplValue;
	}

	public void setIntMultiplValue(String intMultiplValue) {
		this.intMultiplValue = intMultiplValue;
	}

	public String getNbrDecimalPlace() {
		return nbrDecimalPlace;
	}

	public void setNbrDecimalPlace(String nbrDecimalPlace) {
		this.nbrDecimalPlace = nbrDecimalPlace;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getAmountCheckCondition() {
		return amountCheckCondition;
	}

	public void setAmountCheckCondition(String amountCheckCondition) {
		this.amountCheckCondition = amountCheckCondition;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}