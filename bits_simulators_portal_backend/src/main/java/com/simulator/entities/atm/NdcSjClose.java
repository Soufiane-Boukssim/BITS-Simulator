package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SJ_CLOSE")
public class NdcSjClose {

	@EmbeddedId
	private NdcSjCloseId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "RECEIPT_DELIVRED_SCREEN")
	private String receiptDeliveredScreen;
	@Column(name = "NEXT_STATE")
	private String nextState;
	@Column(name = "NO_RECEIPT_DELIVRED_SCREEN")
	private String noReceiptDeliveredScreen;
	@Column(name = "CARD_RETAINED_SCREEN")
	private String cardRetainedScreen;
	@Column(name = "STATEMENT_DELIVRED_SCREEN")
	private String statementDeliveredScreen;
	@Column(name = "BNA_NOTE_RETURNED_SCREEN")
	private String bnaNoteReturnedScreen;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "EXT_STATE_NUMBER")
	private String extStateNumber;
	@Column(name = "INCLUDE_EXT_STATE")
	private Character includeExtState;
	@Column(name = "EXT_STATE_TYPE")
	private String extStateType;
	@Column(name = "CPM_TAKE_DOC_SCREEN")
	private String cpmTakeDocScreen;
	@Column(name = "CPM_DOC_RETURN_RETAIN")
	private String cpmDocReturnRetain;
	@Column(name = "RESERVED_2")
	private String reserved2;

	public NdcSjCloseId getId() {
		return id;
	}

	public void setId(NdcSjCloseId id) {
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

	public String getReceiptDeliveredScreen() {
		return receiptDeliveredScreen;
	}

	public void setReceiptDeliveredScreen(String receiptDeliveredScreen) {
		this.receiptDeliveredScreen = receiptDeliveredScreen;
	}

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	public String getNoReceiptDeliveredScreen() {
		return noReceiptDeliveredScreen;
	}

	public void setNoReceiptDeliveredScreen(String noReceiptDeliveredScreen) {
		this.noReceiptDeliveredScreen = noReceiptDeliveredScreen;
	}

	public String getCardRetainedScreen() {
		return cardRetainedScreen;
	}

	public void setCardRetainedScreen(String cardRetainedScreen) {
		this.cardRetainedScreen = cardRetainedScreen;
	}

	public String getStatementDeliveredScreen() {
		return statementDeliveredScreen;
	}

	public void setStatementDeliveredScreen(String statementDeliveredScreen) {
		this.statementDeliveredScreen = statementDeliveredScreen;
	}

	public String getBnaNoteReturnedScreen() {
		return bnaNoteReturnedScreen;
	}

	public void setBnaNoteReturnedScreen(String bnaNoteReturnedScreen) {
		this.bnaNoteReturnedScreen = bnaNoteReturnedScreen;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getExtStateNumber() {
		return extStateNumber;
	}

	public void setExtStateNumber(String extStateNumber) {
		this.extStateNumber = extStateNumber;
	}

	public Character getIncludeExtState() {
		return includeExtState;
	}

	public void setIncludeExtState(Character includeExtState) {
		this.includeExtState = includeExtState;
	}

	public String getExtStateType() {
		return extStateType;
	}

	public void setExtStateType(String extStateType) {
		this.extStateType = extStateType;
	}

	public String getCpmTakeDocScreen() {
		return cpmTakeDocScreen;
	}

	public void setCpmTakeDocScreen(String cpmTakeDocScreen) {
		this.cpmTakeDocScreen = cpmTakeDocScreen;
	}

	public String getCpmDocReturnRetain() {
		return cpmDocReturnRetain;
	}

	public void setCpmDocReturnRetain(String cpmDocReturnRetain) {
		this.cpmDocReturnRetain = cpmDocReturnRetain;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
}