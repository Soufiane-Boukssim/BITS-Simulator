package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_TIMERS_PROFILE")
public class NdcTimersProfile {

	@Id
	@Column(name = "profileCode")
	private Character profileCode;
	@Column(name = "KEYBOARD_ENTRY_TIMEOUT")
	private Character keyboardEntryTimeout;
	@Column(name = "CARDHOLDER_RESPONSE_TIMEOUT")
	private Character cardholderResponseTimeout;
	@Column(name = "CLOSE_STATE_SCREEN_TIMEOUT")
	private Character closeStateScreenTimeout;
	@Column(name = "COMM_RESPONSE_TIMEOUT")
	private Character commResponseTimeout;
	@Column(name = "ENV_DOC_INSERT_TIMEOUT")
	private Character envDocInsertTimeout;
	@Column(name = "CASH_RETRACT_TIMEOUT")
	private Character cashRetractTimeout;
	@Column(name = "POLL_SELECT_TIMEOUT")
	private Character pollSelectTimeout;
	@Column(name = "PRESENT_TIMEOUT")
	private Character presentTimeout;
	@Column(name = "ADDITIONNEL_PRESENT_TIMEOUT")
	private Character additionnelPresentTimeout;
	@Column(name = "NIGHT_SAFE_DEPOSIT_TIMEOUT")
	private Character nightSafeDepositTimeout;
	@Column(name = "CARD_REMOVAL_TIMEOUT")
	private Character cardRemovalTimeout;
	@Column(name = "BARCORE_SCAN_TIMEOUT")
	private Character barcoreScanTimeout;
	@Column(name = "STATEMENT_EMI_TIMEOUT")
	private Character statementEmiTimeout;
	@Column(name = "RECEIPT_EMI_TIMEOUT")
	private Character receiptEmiTimeout;
	@Column(name = "DASH_CARD_REMOVAL_TIMEOUT")
	private Character dashCardRemovalTimeout;
	@Column(name = "BNA_CASH_ACCEPT_TIMEOUT")
	private Character bnaCashAcceptTimeout;
	@Column(name = "GBXX_CASH_RETRACT_TIMEOUT")
	private Character gbxxCashRetractTimeout;
	@Column(name = "CHEQUE_CAPTURE_TIMEOUT")
	private Character chequeCaptureTimeout;
	@Column(name = "FAULT_DISPLAY_TIMEOUT")
	private Character faultDisplayTimeout;
	@Column(name = "CHEQUE_REMOUVAL_TIMEOUT")
	private Character chequeRemovalTimeout;
	@Column(name = "STATEMENT_RETRACT_TIMEOUT")
	private Character statementRetractTimeout;
	@Column(name = "STATEMENT_PRESENT_TIMEOUT")
	private Character statementPresentTimeout;

	public Character getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(Character profileCode) {
		this.profileCode = profileCode;
	}

	public Character getKeyboardEntryTimeout() {
		return keyboardEntryTimeout;
	}

	public void setKeyboardEntryTimeout(Character keyboardEntryTimeout) {
		this.keyboardEntryTimeout = keyboardEntryTimeout;
	}

	public Character getCardholderResponseTimeout() {
		return cardholderResponseTimeout;
	}

	public void setCardholderResponseTimeout(Character cardholderResponseTimeout) {
		this.cardholderResponseTimeout = cardholderResponseTimeout;
	}

	public Character getCloseStateScreenTimeout() {
		return closeStateScreenTimeout;
	}

	public void setCloseStateScreenTimeout(Character closeStateScreenTimeout) {
		this.closeStateScreenTimeout = closeStateScreenTimeout;
	}

	public Character getCommResponseTimeout() {
		return commResponseTimeout;
	}

	public void setCommResponseTimeout(Character commResponseTimeout) {
		this.commResponseTimeout = commResponseTimeout;
	}

	public Character getEnvDocInsertTimeout() {
		return envDocInsertTimeout;
	}

	public void setEnvDocInsertTimeout(Character envDocInsertTimeout) {
		this.envDocInsertTimeout = envDocInsertTimeout;
	}

	public Character getCashRetractTimeout() {
		return cashRetractTimeout;
	}

	public void setCashRetractTimeout(Character cashRetractTimeout) {
		this.cashRetractTimeout = cashRetractTimeout;
	}

	public Character getPollSelectTimeout() {
		return pollSelectTimeout;
	}

	public void setPollSelectTimeout(Character pollSelectTimeout) {
		this.pollSelectTimeout = pollSelectTimeout;
	}

	public Character getPresentTimeout() {
		return presentTimeout;
	}

	public void setPresentTimeout(Character presentTimeout) {
		this.presentTimeout = presentTimeout;
	}

	public Character getAdditionnelPresentTimeout() {
		return additionnelPresentTimeout;
	}

	public void setAdditionnelPresentTimeout(Character additionnelPresentTimeout) {
		this.additionnelPresentTimeout = additionnelPresentTimeout;
	}

	public Character getNightSafeDepositTimeout() {
		return nightSafeDepositTimeout;
	}

	public void setNightSafeDepositTimeout(Character nightSafeDepositTimeout) {
		this.nightSafeDepositTimeout = nightSafeDepositTimeout;
	}

	public Character getCardRemovalTimeout() {
		return cardRemovalTimeout;
	}

	public void setCardRemovalTimeout(Character cardRemovalTimeout) {
		this.cardRemovalTimeout = cardRemovalTimeout;
	}

	public Character getBarcoreScanTimeout() {
		return barcoreScanTimeout;
	}

	public void setBarcoreScanTimeout(Character barcoreScanTimeout) {
		this.barcoreScanTimeout = barcoreScanTimeout;
	}

	public Character getStatementEmiTimeout() {
		return statementEmiTimeout;
	}

	public void setStatementEmiTimeout(Character statementEmiTimeout) {
		this.statementEmiTimeout = statementEmiTimeout;
	}

	public Character getReceiptEmiTimeout() {
		return receiptEmiTimeout;
	}

	public void setReceiptEmiTimeout(Character receiptEmiTimeout) {
		this.receiptEmiTimeout = receiptEmiTimeout;
	}

	public Character getDashCardRemovalTimeout() {
		return dashCardRemovalTimeout;
	}

	public void setDashCardRemovalTimeout(Character dashCardRemovalTimeout) {
		this.dashCardRemovalTimeout = dashCardRemovalTimeout;
	}

	public Character getBnaCashAcceptTimeout() {
		return bnaCashAcceptTimeout;
	}

	public void setBnaCashAcceptTimeout(Character bnaCashAcceptTimeout) {
		this.bnaCashAcceptTimeout = bnaCashAcceptTimeout;
	}

	public Character getGbxxCashRetractTimeout() {
		return gbxxCashRetractTimeout;
	}

	public void setGbxxCashRetractTimeout(Character gbxxCashRetractTimeout) {
		this.gbxxCashRetractTimeout = gbxxCashRetractTimeout;
	}

	public Character getChequeCaptureTimeout() {
		return chequeCaptureTimeout;
	}

	public void setChequeCaptureTimeout(Character chequeCaptureTimeout) {
		this.chequeCaptureTimeout = chequeCaptureTimeout;
	}

	public Character getFaultDisplayTimeout() {
		return faultDisplayTimeout;
	}

	public void setFaultDisplayTimeout(Character faultDisplayTimeout) {
		this.faultDisplayTimeout = faultDisplayTimeout;
	}

	public Character getChequeRemovalTimeout() {
		return chequeRemovalTimeout;
	}

	public void setChequeRemovalTimeout(Character chequeRemovalTimeout) {
		this.chequeRemovalTimeout = chequeRemovalTimeout;
	}

	public Character getStatementRetractTimeout() {
		return statementRetractTimeout;
	}

	public void setStatementRetractTimeout(Character statementRetractTimeout) {
		this.statementRetractTimeout = statementRetractTimeout;
	}

	public Character getStatementPresentTimeout() {
		return statementPresentTimeout;
	}

	public void setStatementPresentTimeout(Character statementPresentTimeout) {
		this.statementPresentTimeout = statementPresentTimeout;
	}
}