package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SSUP_CASH_ACCEPT")
public class NdcSsupCashAccept {

	@EmbeddedId
	private NdcSsupCashAcceptId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "CANCEL_KEY_MASK")
	private String cancelKeyMask;
	@Column(name = "DEPOSIT_KEY_MASK")
	private String depositKeyMask;
	@Column(name = "ADDED_MORE_KEY_MASK")
	private String addedMoreKeyMask;
	@Column(name = "REFUND_KEY_MASK")
	private String refundKeyMask;
	@Column(name = "EXTENSION_STATE_1")
	private String extensionState1;
	@Column(name = "EXTENSION_STATE_2")
	private String extensionState2;
	@Column(name = "EXTENSION_STATE_3")
	private String extensionState3;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "EXTENSION_STATE_1_EXT_TYPE")
	private Character extensionState1ExtType;
	@Column(name = "ENTER_NOTE_SCREEN")
	private String enterNoteScreen;
	@Column(name = "REMOVE_NOTE_SCREEN")
	private String removeNoteScreen;
	@Column(name = "CONFIRMATION_SCREEN")
	private String confirmationScreen;
	@Column(name = "HARDWARE_ERROR_SCREEN")
	private String hardwareErrorScreen;
	@Column(name = "ESCROW_FULL_SCREEN")
	private String escrowFullScreen;
	@Column(name = "PROCESSING_NOTE_SCREEN")
	private String processingNoteScreen;
	@Column(name = "RESERVED_2")
	private String reserved2;
	@Column(name = "EXTENSION_STATE_2_EXT_TYPE")
	private Character extensionState2ExtType;
	@Column(name = "GOOD_NEXT_STATE")
	private String goodNextState;
	@Column(name = "CANCEL_NEXT_STATE")
	private String cancelNextState;
	@Column(name = "DEVICE_ERROR_NEXT_STATE")
	private String deviceErrorNextState;
	@Column(name = "TIME_OUT_NEXT_STATE")
	private String timeOutNextState;
	@Column(name = "REFUND_SLOT_NEXT_STATE")
	private String refundSlotNextState;
	@Column(name = "RESERVED_3")
	private String reserved3;
	@Column(name = "EXTENSION_STATE_3_EXT_TYPE")
	private Character extensionState3ExtType;
	@Column(name = "SET_DONOMINATION_1_11")
	private String setDonomination1_11;
	@Column(name = "SET_DONOMINATION_12_23")
	private String setDonomination12_23;
	@Column(name = "SET_DONOMINATION_24_35")
	private String setDonomination24_35;
	@Column(name = "SET_DONOMINATION_36_47")
	private String setDonomination36_47;
	@Column(name = "SET_DONOMINATION_48_50")
	private String setDonomination48_50;
	@Column(name = "RESERVED_4")
	private String reserved4;

	public NdcSsupCashAcceptId getId() {
		return id;
	}

	public void setId(NdcSsupCashAcceptId id) {
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

	public String getCancelKeyMask() {
		return cancelKeyMask;
	}

	public void setCancelKeyMask(String cancelKeyMask) {
		this.cancelKeyMask = cancelKeyMask;
	}

	public String getDepositKeyMask() {
		return depositKeyMask;
	}

	public void setDepositKeyMask(String depositKeyMask) {
		this.depositKeyMask = depositKeyMask;
	}

	public String getAddedMoreKeyMask() {
		return addedMoreKeyMask;
	}

	public void setAddedMoreKeyMask(String addedMoreKeyMask) {
		this.addedMoreKeyMask = addedMoreKeyMask;
	}

	public String getRefundKeyMask() {
		return refundKeyMask;
	}

	public void setRefundKeyMask(String refundKeyMask) {
		this.refundKeyMask = refundKeyMask;
	}

	public String getExtensionState1() {
		return extensionState1;
	}

	public void setExtensionState1(String extensionState1) {
		this.extensionState1 = extensionState1;
	}

	public String getExtensionState2() {
		return extensionState2;
	}

	public void setExtensionState2(String extensionState2) {
		this.extensionState2 = extensionState2;
	}

	public String getExtensionState3() {
		return extensionState3;
	}

	public void setExtensionState3(String extensionState3) {
		this.extensionState3 = extensionState3;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public Character getExtensionState1ExtType() {
		return extensionState1ExtType;
	}

	public void setExtensionState1ExtType(Character extensionState1ExtType) {
		this.extensionState1ExtType = extensionState1ExtType;
	}

	public String getEnterNoteScreen() {
		return enterNoteScreen;
	}

	public void setEnterNoteScreen(String enterNoteScreen) {
		this.enterNoteScreen = enterNoteScreen;
	}

	public String getRemoveNoteScreen() {
		return removeNoteScreen;
	}

	public void setRemoveNoteScreen(String removeNoteScreen) {
		this.removeNoteScreen = removeNoteScreen;
	}

	public String getConfirmationScreen() {
		return confirmationScreen;
	}

	public void setConfirmationScreen(String confirmationScreen) {
		this.confirmationScreen = confirmationScreen;
	}

	public String getHardwareErrorScreen() {
		return hardwareErrorScreen;
	}

	public void setHardwareErrorScreen(String hardwareErrorScreen) {
		this.hardwareErrorScreen = hardwareErrorScreen;
	}

	public String getEscrowFullScreen() {
		return escrowFullScreen;
	}

	public void setEscrowFullScreen(String escrowFullScreen) {
		this.escrowFullScreen = escrowFullScreen;
	}

	public String getProcessingNoteScreen() {
		return processingNoteScreen;
	}

	public void setProcessingNoteScreen(String processingNoteScreen) {
		this.processingNoteScreen = processingNoteScreen;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public Character getExtensionState2ExtType() {
		return extensionState2ExtType;
	}

	public void setExtensionState2ExtType(Character extensionState2ExtType) {
		this.extensionState2ExtType = extensionState2ExtType;
	}

	public String getGoodNextState() {
		return goodNextState;
	}

	public void setGoodNextState(String goodNextState) {
		this.goodNextState = goodNextState;
	}

	public String getCancelNextState() {
		return cancelNextState;
	}

	public void setCancelNextState(String cancelNextState) {
		this.cancelNextState = cancelNextState;
	}

	public String getDeviceErrorNextState() {
		return deviceErrorNextState;
	}

	public void setDeviceErrorNextState(String deviceErrorNextState) {
		this.deviceErrorNextState = deviceErrorNextState;
	}

	public String getTimeOutNextState() {
		return timeOutNextState;
	}

	public void setTimeOutNextState(String timeOutNextState) {
		this.timeOutNextState = timeOutNextState;
	}

	public String getRefundSlotNextState() {
		return refundSlotNextState;
	}

	public void setRefundSlotNextState(String refundSlotNextState) {
		this.refundSlotNextState = refundSlotNextState;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public Character getExtensionState3ExtType() {
		return extensionState3ExtType;
	}

	public void setExtensionState3ExtType(Character extensionState3ExtType) {
		this.extensionState3ExtType = extensionState3ExtType;
	}

	public String getSetDonomination1_11() {
		return setDonomination1_11;
	}

	public void setSetDonomination1_11(String setDonomination1_11) {
		this.setDonomination1_11 = setDonomination1_11;
	}

	public String getSetDonomination12_23() {
		return setDonomination12_23;
	}

	public void setSetDonomination12_23(String setDonomination12_23) {
		this.setDonomination12_23 = setDonomination12_23;
	}

	public String getSetDonomination24_35() {
		return setDonomination24_35;
	}

	public void setSetDonomination24_35(String setDonomination24_35) {
		this.setDonomination24_35 = setDonomination24_35;
	}

	public String getSetDonomination36_47() {
		return setDonomination36_47;
	}

	public void setSetDonomination36_47(String setDonomination36_47) {
		this.setDonomination36_47 = setDonomination36_47;
	}

	public String getSetDonomination48_50() {
		return setDonomination48_50;
	}

	public void setSetDonomination48_50(String setDonomination48_50) {
		this.setDonomination48_50 = setDonomination48_50;
	}

	public String getReserved4() {
		return reserved4;
	}

	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
}