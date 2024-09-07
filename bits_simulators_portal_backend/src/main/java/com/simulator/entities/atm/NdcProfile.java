package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_PROFILE")
public class NdcProfile {

	@Id
	@Column(name = "profileCode")
	private String profileCode;
	@Column(name = "READY_STATUS")
	private String readyStatus;
	@Column(name = "SUPPLY_MODE")
	private String supplyMode;
	@Column(name = "AMOUNT_BUFFER_LENGTH")
	private String amountBufferLength;
	@Column(name = "AUTO_VOICE")
	private String autoVoice;
	@Column(name = "DATE_FORMAT")
	private String dateFormat;
	@Column(name = "ROLL_WIDTH")
	private String rollWidth;
	@Column(name = "LEFT_COLUMN")
	private String leftColumn;
	@Column(name = "TRACK1_FORMAT")
	private String track1Format;
	@Column(name = "SPECIFIC_COMMAND_REJECT")
	private String specificCommandReject;
	@Column(name = "TRANSACTION_STATUS_INFO")
	private String transactionStatusInfo;
	@Column(name = "JRL_PRT_BACKUP_TIME")
	private String jrlPrtBackupTime;
	@Column(name = "JRL_PRT_PRINT_OP")
	private String jrlPrtPrintOp;
	@Column(name = "ENV_DISPENSER_STATUS")
	private String envDispenserStatus;
	@Column(name = "SENSOR_STATUS_UNSOLICITED")
	private String sensorStatusUnsolicited;
	@Column(name = "FLASH_RATE_INDICATOR")
	private String flashRateIndicator;
	@Column(name = "REMOTE_RELAY")
	private String remoteRelay;
	@Column(name = "SIMULATE_SUPERVISE")
	private String simulateSupervise;
	@Column(name = "MCN_RANGE")
	private String mcnRange;
	@Column(name = "ENH_EJ_BACKUP")
	private String enhEjBackup;
	@Column(name = "PRINT_T2_JOURNAL")
	private String printT2Journal;
	@Column(name = "BNA_JOURNAL_VAULTED")
	private String bnaJournalVaulted;
	@Column(name = "BNA_MESSAGE_SETTING")
	private String bnaMessageSetting;
	@Column(name = "BARCODE_READER")
	private String barcodeReader;
	@Column(name = "EMV_EXT_STATUS_OPT")
	private String emvExtStatusOpt;
	@Column(name = "EMV_ICC_MANIPULATE_FLAG")
	private String emvIccManipulateFlag;
	@Column(name = "CASH_HANDLER")
	private String cashHandler;
	@Column(name = "NEXT_STATE_NUMBER")
	private String nextStateNumber;
	@Column(name = "M_STATUS_REPORTING")
	private String mStatusReporting;
	@Column(name = "COIN_DISPENCER")
	private String coinDispencer;
	@Column(name = "ALPHA_NUM_ENTRY")
	private String alphaNumEntry;
	@Column(name = "CHEQUE_MEDULE_PROC")
	private String chequeMeduleProc;
	@Column(name = "NUMBER_OF_SECOND")
	private String numberOfSecond;
	@Column(name = "EMV_PROFILE")
	private String emvProfile;

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getReadyStatus() {
		return readyStatus;
	}

	public void setReadyStatus(String readyStatus) {
		this.readyStatus = readyStatus;
	}

	public String getSupplyMode() {
		return supplyMode;
	}

	public void setSupplyMode(String supplyMode) {
		this.supplyMode = supplyMode;
	}

	public String getAmountBufferLength() {
		return amountBufferLength;
	}

	public void setAmountBufferLength(String amountBufferLength) {
		this.amountBufferLength = amountBufferLength;
	}

	public String getAutoVoice() {
		return autoVoice;
	}

	public void setAutoVoice(String autoVoice) {
		this.autoVoice = autoVoice;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getRollWidth() {
		return rollWidth;
	}

	public void setRollWidth(String rollWidth) {
		this.rollWidth = rollWidth;
	}

	public String getLeftColumn() {
		return leftColumn;
	}

	public void setLeftColumn(String leftColumn) {
		this.leftColumn = leftColumn;
	}

	public String getTrack1Format() {
		return track1Format;
	}

	public void setTrack1Format(String track1Format) {
		this.track1Format = track1Format;
	}

	public String getSpecificCommandReject() {
		return specificCommandReject;
	}

	public void setSpecificCommandReject(String specificCommandReject) {
		this.specificCommandReject = specificCommandReject;
	}

	public String getTransactionStatusInfo() {
		return transactionStatusInfo;
	}

	public void setTransactionStatusInfo(String transactionStatusInfo) {
		this.transactionStatusInfo = transactionStatusInfo;
	}

	public String getJrlPrtBackupTime() {
		return jrlPrtBackupTime;
	}

	public void setJrlPrtBackupTime(String jrlPrtBackupTime) {
		this.jrlPrtBackupTime = jrlPrtBackupTime;
	}

	public String getJrlPrtPrintOp() {
		return jrlPrtPrintOp;
	}

	public void setJrlPrtPrintOp(String jrlPrtPrintOp) {
		this.jrlPrtPrintOp = jrlPrtPrintOp;
	}

	public String getEnvDispenserStatus() {
		return envDispenserStatus;
	}

	public void setEnvDispenserStatus(String envDispenserStatus) {
		this.envDispenserStatus = envDispenserStatus;
	}

	public String getSensorStatusUnsolicited() {
		return sensorStatusUnsolicited;
	}

	public void setSensorStatusUnsolicited(String sensorStatusUnsolicited) {
		this.sensorStatusUnsolicited = sensorStatusUnsolicited;
	}

	public String getFlashRateIndicator() {
		return flashRateIndicator;
	}

	public void setFlashRateIndicator(String flashRateIndicator) {
		this.flashRateIndicator = flashRateIndicator;
	}

	public String getRemoteRelay() {
		return remoteRelay;
	}

	public void setRemoteRelay(String remoteRelay) {
		this.remoteRelay = remoteRelay;
	}

	public String getSimulateSupervise() {
		return simulateSupervise;
	}

	public void setSimulateSupervise(String simulateSupervise) {
		this.simulateSupervise = simulateSupervise;
	}

	public String getMcnRange() {
		return mcnRange;
	}

	public void setMcnRange(String mcnRange) {
		this.mcnRange = mcnRange;
	}

	public String getEnhEjBackup() {
		return enhEjBackup;
	}

	public void setEnhEjBackup(String enhEjBackup) {
		this.enhEjBackup = enhEjBackup;
	}

	public String getPrintT2Journal() {
		return printT2Journal;
	}

	public void setPrintT2Journal(String printT2Journal) {
		this.printT2Journal = printT2Journal;
	}

	public String getBnaJournalVaulted() {
		return bnaJournalVaulted;
	}

	public void setBnaJournalVaulted(String bnaJournalVaulted) {
		this.bnaJournalVaulted = bnaJournalVaulted;
	}

	public String getBnaMessageSetting() {
		return bnaMessageSetting;
	}

	public void setBnaMessageSetting(String bnaMessageSetting) {
		this.bnaMessageSetting = bnaMessageSetting;
	}

	public String getBarcodeReader() {
		return barcodeReader;
	}

	public void setBarcodeReader(String barcodeReader) {
		this.barcodeReader = barcodeReader;
	}

	public String getEmvExtStatusOpt() {
		return emvExtStatusOpt;
	}

	public void setEmvExtStatusOpt(String emvExtStatusOpt) {
		this.emvExtStatusOpt = emvExtStatusOpt;
	}

	public String getEmvIccManipulateFlag() {
		return emvIccManipulateFlag;
	}

	public void setEmvIccManipulateFlag(String emvIccManipulateFlag) {
		this.emvIccManipulateFlag = emvIccManipulateFlag;
	}

	public String getCashHandler() {
		return cashHandler;
	}

	public void setCashHandler(String cashHandler) {
		this.cashHandler = cashHandler;
	}

	public String getNextStateNumber() {
		return nextStateNumber;
	}

	public void setNextStateNumber(String nextStateNumber) {
		this.nextStateNumber = nextStateNumber;
	}

	public String getMStatusReporting() {
		return mStatusReporting;
	}

	public void setMStatusReporting(String mStatusReporting) {
		this.mStatusReporting = mStatusReporting;
	}

	public String getCoinDispencer() {
		return coinDispencer;
	}

	public void setCoinDispencer(String coinDispencer) {
		this.coinDispencer = coinDispencer;
	}

	public String getAlphaNumEntry() {
		return alphaNumEntry;
	}

	public void setAlphaNumEntry(String alphaNumEntry) {
		this.alphaNumEntry = alphaNumEntry;
	}

	public String getChequeMeduleProc() {
		return chequeMeduleProc;
	}

	public void setChequeMeduleProc(String chequeMeduleProc) {
		this.chequeMeduleProc = chequeMeduleProc;
	}

	public String getNumberOfSecond() {
		return numberOfSecond;
	}

	public void setNumberOfSecond(String numberOfSecond) {
		this.numberOfSecond = numberOfSecond;
	}

	public String getEmvProfile() {
		return emvProfile;
	}

	public void setEmvProfile(String emvProfile) {
		this.emvProfile = emvProfile;
	}
}