package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SW_CHEQUE_ACCEPT")
public class NdcSwChequeAccept {

	@EmbeddedId
	private NdcSwChequeAcceptId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "LEAVE_CAPTURE_OPTION")
	private String leaveCaptureOption;
	@Column(name = "CHEQUE_ENTRY_RETRIES")
	private String chequeEntryRetries;
	@Column(name = "IMAGE_LIFT")
	private String imageLift;
	@Column(name = "EXTENSION_STATE_1")
	private String extensionState1;
	@Column(name = "EXTENSION_STATE_2")
	private String extensionState2;
	@Column(name = "CANCEL_KEY_MASK")
	private String cancelKeyMask;
	@Column(name = "DEPOSIT_KEY_MASK")
	private String depositKeyMask;
	@Column(name = "EXTENSION_STATE_1_EXT_TYPE")
	private Character extensionState1ExtType;
	@Column(name = "ENTER_CHEQUE_SCREEN")
	private String enterChequeScreen;
	@Column(name = "PROCESSING_CHEQUE_SCREEN")
	private String processingChequeScreen;
	@Column(name = "UNACCEPTABLE_CHEQUE_SCREEN")
	private String unacceptableChequeScreen;
	@Column(name = "INCORRECT_ORIENTATION_SCREEN")
	private String incorrectOrientationScreen;
	@Column(name = "FURTHER_PROCESSING_SCREEN")
	private String furtherProcessingScreen;
	@Column(name = "CHEQUE_CAPTURE_SCREEN")
	private String chequeCaptureScreen;
	@Column(name = "DEVICE_ERROR_SCREEN")
	private String deviceErrorScreen;
	@Column(name = "REMOVE_CHEQUE_SCREEN")
	private String removeChequeScreen;
	@Column(name = "EXTENSION_STATE_2_EXT_TYPE")
	private Character extensionState2ExtType;
	@Column(name = "CHQ_ELREADY_EXIST_STATE")
	private String chqElreadyExistState;
	@Column(name = "NOEXCEPTION_NEXT_STATE")
	private String noExceptionNextState;
	@Column(name = "EXCEPTION_NEXT_STATE")
	private String exceptionNextState;
	@Column(name = "TIMEOUT_CANCEL_NEXT_STATE")
	private String timeoutCancelNextState;
	@Column(name = "CHAQUE_CAPTURE_NEXT_STATE")
	private String chqCaptureNextState;
	@Column(name = "RESERVED_2")
	private String reserved2;

	public NdcSwChequeAcceptId getId() {
		return id;
	}

	public void setId(NdcSwChequeAcceptId id) {
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

	public String getLeaveCaptureOption() {
		return leaveCaptureOption;
	}

	public void setLeaveCaptureOption(String leaveCaptureOption) {
		this.leaveCaptureOption = leaveCaptureOption;
	}

	public String getChequeEntryRetries() {
		return chequeEntryRetries;
	}

	public void setChequeEntryRetries(String chequeEntryRetries) {
		this.chequeEntryRetries = chequeEntryRetries;
	}

	public String getImageLift() {
		return imageLift;
	}

	public void setImageLift(String imageLift) {
		this.imageLift = imageLift;
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

	public Character getExtensionState1ExtType() {
		return extensionState1ExtType;
	}

	public void setExtensionState1ExtType(Character extensionState1ExtType) {
		this.extensionState1ExtType = extensionState1ExtType;
	}

	public String getEnterChequeScreen() {
		return enterChequeScreen;
	}

	public void setEnterChequeScreen(String enterChequeScreen) {
		this.enterChequeScreen = enterChequeScreen;
	}

	public String getProcessingChequeScreen() {
		return processingChequeScreen;
	}

	public void setProcessingChequeScreen(String processingChequeScreen) {
		this.processingChequeScreen = processingChequeScreen;
	}

	public String getUnacceptableChequeScreen() {
		return unacceptableChequeScreen;
	}

	public void setUnacceptableChequeScreen(String unacceptableChequeScreen) {
		this.unacceptableChequeScreen = unacceptableChequeScreen;
	}

	public String getIncorrectOrientationScreen() {
		return incorrectOrientationScreen;
	}

	public void setIncorrectOrientationScreen(String incorrectOrientationScreen) {
		this.incorrectOrientationScreen = incorrectOrientationScreen;
	}

	public String getFurtherProcessingScreen() {
		return furtherProcessingScreen;
	}

	public void setFurtherProcessingScreen(String furtherProcessingScreen) {
		this.furtherProcessingScreen = furtherProcessingScreen;
	}

	public String getChequeCaptureScreen() {
		return chequeCaptureScreen;
	}

	public void setChequeCaptureScreen(String chequeCaptureScreen) {
		this.chequeCaptureScreen = chequeCaptureScreen;
	}

	public String getDeviceErrorScreen() {
		return deviceErrorScreen;
	}

	public void setDeviceErrorScreen(String deviceErrorScreen) {
		this.deviceErrorScreen = deviceErrorScreen;
	}

	public String getRemoveChequeScreen() {
		return removeChequeScreen;
	}

	public void setRemoveChequeScreen(String removeChequeScreen) {
		this.removeChequeScreen = removeChequeScreen;
	}

	public Character getExtensionState2ExtType() {
		return extensionState2ExtType;
	}

	public void setExtensionState2ExtType(Character extensionState2ExtType) {
		this.extensionState2ExtType = extensionState2ExtType;
	}

	public String getChqElreadyExistState() {
		return chqElreadyExistState;
	}

	public void setChqElreadyExistState(String chqElreadyExistState) {
		this.chqElreadyExistState = chqElreadyExistState;
	}

	public String getNoExceptionNextState() {
		return noExceptionNextState;
	}

	public void setNoExceptionNextState(String noExceptionNextState) {
		this.noExceptionNextState = noExceptionNextState;
	}

	public String getExceptionNextState() {
		return exceptionNextState;
	}

	public void setExceptionNextState(String exceptionNextState) {
		this.exceptionNextState = exceptionNextState;
	}

	public String getTimeoutCancelNextState() {
		return timeoutCancelNextState;
	}

	public void setTimeoutCancelNextState(String timeoutCancelNextState) {
		this.timeoutCancelNextState = timeoutCancelNextState;
	}

	public String getChqCaptureNextState() {
		return chqCaptureNextState;
	}

	public void setChqCaptureNextState(String chqCaptureNextState) {
		this.chqCaptureNextState = chqCaptureNextState;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
}