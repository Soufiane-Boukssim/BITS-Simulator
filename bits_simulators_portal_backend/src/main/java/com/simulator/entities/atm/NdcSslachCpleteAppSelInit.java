package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SSLACH_CPLETE_APP_SEL_INIT")
public class NdcSslachCpleteAppSelInit {

	@EmbeddedId
	private NdcSslachCpleteAppSelInitId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "WAIT_SCREEN_NBR")
	private String waitScreenNbr;
	@Column(name = "ICC_APP_NAME_TEMPLATE_SCREEN")
	private String iccAppNameTemplateScreen;
	@Column(name = "ICC_APP_NAME_SCREEN")
	private String iccAppNameScreen;
	@Column(name = "EXIT_PATH_EXT_NEXT")
	private String exitPathExtNext;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "EXIT_PATH_EXT_TYPE")
	private Character exitPathExtType;
	@Column(name = "ICC_INIT_SUCCESSFUL_NEXT")
	private String iccInitSuccessfulNext;
	@Column(name = "CARD_NO_SMART")
	private String cardNoSmart;
	@Column(name = "NO_USABLE_APP_NEXT")
	private String noUsableAppNext;
	@Column(name = "NO_SUITABLE_ICC_APP_NEXT")
	private String noSuitableIccAppNext;
	@Column(name = "ICC_APP_ERROR_NEXT")
	private String iccAppErrorNext;
	@Column(name = "ICC_HARDWARE_ERROR_NEXT")
	private String iccHardwareErrorNext;
	@Column(name = "PROCESS_NO_PERFORMED_NEXT")
	private String processNoPerformedNext;
	@Column(name = "RESERVED_2")
	private String reserved2;

	public NdcSslachCpleteAppSelInitId getId() {
		return id;
	}

	public void setId(NdcSslachCpleteAppSelInitId id) {
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

	public String getWaitScreenNbr() {
		return waitScreenNbr;
	}

	public void setWaitScreenNbr(String waitScreenNbr) {
		this.waitScreenNbr = waitScreenNbr;
	}

	public String getIccAppNameTemplateScreen() {
		return iccAppNameTemplateScreen;
	}

	public void setIccAppNameTemplateScreen(String iccAppNameTemplateScreen) {
		this.iccAppNameTemplateScreen = iccAppNameTemplateScreen;
	}

	public String getIccAppNameScreen() {
		return iccAppNameScreen;
	}

	public void setIccAppNameScreen(String iccAppNameScreen) {
		this.iccAppNameScreen = iccAppNameScreen;
	}

	public String getExitPathExtNext() {
		return exitPathExtNext;
	}

	public void setExitPathExtNext(String exitPathExtNext) {
		this.exitPathExtNext = exitPathExtNext;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public Character getExitPathExtType() {
		return exitPathExtType;
	}

	public void setExitPathExtType(Character exitPathExtType) {
		this.exitPathExtType = exitPathExtType;
	}

	public String getIccInitSuccessfulNext() {
		return iccInitSuccessfulNext;
	}

	public void setIccInitSuccessfulNext(String iccInitSuccessfulNext) {
		this.iccInitSuccessfulNext = iccInitSuccessfulNext;
	}

	public String getCardNoSmart() {
		return cardNoSmart;
	}

	public void setCardNoSmart(String cardNoSmart) {
		this.cardNoSmart = cardNoSmart;
	}

	public String getNoUsableAppNext() {
		return noUsableAppNext;
	}

	public void setNoUsableAppNext(String noUsableAppNext) {
		this.noUsableAppNext = noUsableAppNext;
	}

	public String getNoSuitableIccAppNext() {
		return noSuitableIccAppNext;
	}

	public void setNoSuitableIccAppNext(String noSuitableIccAppNext) {
		this.noSuitableIccAppNext = noSuitableIccAppNext;
	}

	public String getIccAppErrorNext() {
		return iccAppErrorNext;
	}

	public void setIccAppErrorNext(String iccAppErrorNext) {
		this.iccAppErrorNext = iccAppErrorNext;
	}

	public String getIccHardwareErrorNext() {
		return iccHardwareErrorNext;
	}

	public void setIccHardwareErrorNext(String iccHardwareErrorNext) {
		this.iccHardwareErrorNext = iccHardwareErrorNext;
	}

	public String getProcessNoPerformedNext() {
		return processNoPerformedNext;
	}

	public void setProcessNoPerformedNext(String processNoPerformedNext) {
		this.processNoPerformedNext = processNoPerformedNext;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
}