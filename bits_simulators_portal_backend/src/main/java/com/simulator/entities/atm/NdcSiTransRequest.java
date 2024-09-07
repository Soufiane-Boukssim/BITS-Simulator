package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SI_TRANS_REQUEST")
public class NdcSiTransRequest {

	@EmbeddedId
	private NdcSiTransRequestId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;
	@Column(name = "CENTRAL_RESP_TIMEOUT_NEXT")
	private String centralRespTimeoutNext;
	@Column(name = "SEND_TRACK2_DATA")
	private String sendTrack2Data;
	@Column(name = "SEND_TRACK1_TRACK3_DATA")
	private String sendTrack1Track3Data;
	@Column(name = "SEND_OPERATION_CODE_BUFFER")
	private String sendOperationCodeBuffer;
	@Column(name = "SEND_AMOUNT_DATA")
	private String sendAmountData;
	@Column(name = "SEND_PIN_BUFFER_DATA_A")
	private String sendPinBufferaData;
	@Column(name = "SEND_GENERAL_PURPOSE_B_C_1")
	private String sendGeneralPurposeBC1;
	@Column(name = "INCLUDE_EXTENSION_STATE")
	private Character includeExtensionState;
	@Column(name = "EXTENSION_STATE_TYPE")
	private Character extensionStateType;
	@Column(name = "SEND_GENERAL_PURPOSE_B_C")
	private String sendGeneralPurposeBC;
	@Column(name = "SEND_OPTIONAL_FIELD_A_H")
	private String sendOptionalFieldAH;
	@Column(name = "SEND_OPTIONAL_FIELD_I_L")
	private String sendOptionalFieldIL;
	@Column(name = "SEND_OPTIONAL_FIELD_Q_A")
	private String sendOptionalFieldQA;
	@Column(name = "SEND_OPTIONAL_DATA")
	private String sendOptionalData;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "EMV_CAM_PROCESSING")
	private String emvCamProcessing;
	@Column(name = "RESERVED_2")
	private String reserved2;

	public NdcSiTransRequestId getId() {
		return id;
	}

	public void setId(NdcSiTransRequestId id) {
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

	public String getCentralRespTimeoutNext() {
		return centralRespTimeoutNext;
	}

	public void setCentralRespTimeoutNext(String centralRespTimeoutNext) {
		this.centralRespTimeoutNext = centralRespTimeoutNext;
	}

	public String getSendTrack2Data() {
		return sendTrack2Data;
	}

	public void setSendTrack2Data(String sendTrack2Data) {
		this.sendTrack2Data = sendTrack2Data;
	}

	public String getSendTrack1Track3Data() {
		return sendTrack1Track3Data;
	}

	public void setSendTrack1Track3Data(String sendTrack1Track3Data) {
		this.sendTrack1Track3Data = sendTrack1Track3Data;
	}

	public String getSendOperationCodeBuffer() {
		return sendOperationCodeBuffer;
	}

	public void setSendOperationCodeBuffer(String sendOperationCodeBuffer) {
		this.sendOperationCodeBuffer = sendOperationCodeBuffer;
	}

	public String getSendAmountData() {
		return sendAmountData;
	}

	public void setSendAmountData(String sendAmountData) {
		this.sendAmountData = sendAmountData;
	}

	public String getSendPinBufferaData() {
		return sendPinBufferaData;
	}

	public void setSendPinBufferaData(String sendPinBufferaData) {
		this.sendPinBufferaData = sendPinBufferaData;
	}

	public String getSendGeneralPurposeBC1() {
		return sendGeneralPurposeBC1;
	}

	public void setSendGeneralPurposeBC1(String sendGeneralPurposeBC1) {
		this.sendGeneralPurposeBC1 = sendGeneralPurposeBC1;
	}

	public Character getIncludeExtensionState() {
		return includeExtensionState;
	}

	public void setIncludeExtensionState(Character includeExtensionState) {
		this.includeExtensionState = includeExtensionState;
	}

	public Character getExtensionStateType() {
		return extensionStateType;
	}

	public void setExtensionStateType(Character extensionStateType) {
		this.extensionStateType = extensionStateType;
	}

	public String getSendGeneralPurposeBC() {
		return sendGeneralPurposeBC;
	}

	public void setSendGeneralPurposeBC(String sendGeneralPurposeBC) {
		this.sendGeneralPurposeBC = sendGeneralPurposeBC;
	}

	public String getSendOptionalFieldAH() {
		return sendOptionalFieldAH;
	}

	public void setSendOptionalFieldAH(String sendOptionalFieldAH) {
		this.sendOptionalFieldAH = sendOptionalFieldAH;
	}

	public String getSendOptionalFieldIL() {
		return sendOptionalFieldIL;
	}

	public void setSendOptionalFieldIL(String sendOptionalFieldIL) {
		this.sendOptionalFieldIL = sendOptionalFieldIL;
	}

	public String getSendOptionalFieldQA() {
		return sendOptionalFieldQA;
	}

	public void setSendOptionalFieldQA(String sendOptionalFieldQA) {
		this.sendOptionalFieldQA = sendOptionalFieldQA;
	}

	public String getSendOptionalData() {
		return sendOptionalData;
	}

	public void setSendOptionalData(String sendOptionalData) {
		this.sendOptionalData = sendOptionalData;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getEmvCamProcessing() {
		return emvCamProcessing;
	}

	public void setEmvCamProcessing(String emvCamProcessing) {
		this.emvCamProcessing = emvCamProcessing;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
}