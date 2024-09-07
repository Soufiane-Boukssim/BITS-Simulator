package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SR_DOCUMENT_AMOUNT_E")
public class NdcSrDocumentAmountE {

	@EmbeddedId
	private NdcSrDocumentAmountEId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;
	@Column(name = "TIMEOUT_NEXT_STATE")
	private String timeoutNextState;
	@Column(name = "CANCEL_NEXT_STATE")
	private String cancelNextState;
	@Column(name = "FDK_A_I_NEXT_STATE")
	private String fdkAINextState;
	@Column(name = "FDK_B_H_NEXT_STATE")
	private String fdkBHNextState;
	@Column(name = "FDK_C_G_NEXT_STATE")
	private String fdkCGNextState;
	@Column(name = "FDK_D_F_NEXT_STATE")
	private String fdkDFNextState;
	@Column(name = "EXTENSION_STATE_NUMBER")
	private String extensionStateNumber;
	@Column(name = "EXTENSION_STATE_TYPE")
	private Character extensionStateType;
	@Column(name = "DOCUMENT_AMOUNT_BUFFER")
	private String documentAmountBuffer;
	@Column(name = "AMOUNT_DISPLAY_SCREEN_NUMBER")
	private String amountDisplayScreenNumber;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSrDocumentAmountEId getId() {
		return id;
	}

	public void setId(NdcSrDocumentAmountEId id) {
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

	public String getTimeoutNextState() {
		return timeoutNextState;
	}

	public void setTimeoutNextState(String timeoutNextState) {
		this.timeoutNextState = timeoutNextState;
	}

	public String getCancelNextState() {
		return cancelNextState;
	}

	public void setCancelNextState(String cancelNextState) {
		this.cancelNextState = cancelNextState;
	}

	public String getFdkAINextState() {
		return fdkAINextState;
	}

	public void setFdkAINextState(String fdkAINextState) {
		this.fdkAINextState = fdkAINextState;
	}

	public String getFdkBHNextState() {
		return fdkBHNextState;
	}

	public void setFdkBHNextState(String fdkBHNextState) {
		this.fdkBHNextState = fdkBHNextState;
	}

	public String getFdkCGNextState() {
		return fdkCGNextState;
	}

	public void setFdkCGNextState(String fdkCGNextState) {
		this.fdkCGNextState = fdkCGNextState;
	}

	public String getFdkDFNextState() {
		return fdkDFNextState;
	}

	public void setFdkDFNextState(String fdkDFNextState) {
		this.fdkDFNextState = fdkDFNextState;
	}

	public String getExtensionStateNumber() {
		return extensionStateNumber;
	}

	public void setExtensionStateNumber(String extensionStateNumber) {
		this.extensionStateNumber = extensionStateNumber;
	}

	public Character getExtensionStateType() {
		return extensionStateType;
	}

	public void setExtensionStateType(Character extensionStateType) {
		this.extensionStateType = extensionStateType;
	}

	public String getDocumentAmountBuffer() {
		return documentAmountBuffer;
	}

	public void setDocumentAmountBuffer(String documentAmountBuffer) {
		this.documentAmountBuffer = documentAmountBuffer;
	}

	public String getAmountDisplayScreenNumber() {
		return amountDisplayScreenNumber;
	}

	public void setAmountDisplayScreenNumber(String amountDisplayScreenNumber) {
		this.amountDisplayScreenNumber = amountDisplayScreenNumber;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}