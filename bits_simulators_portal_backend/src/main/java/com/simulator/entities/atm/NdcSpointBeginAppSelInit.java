package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SPOINT_BEGIN_APP_SEL_INIT")
public class NdcSpointBeginAppSelInit {

	@EmbeddedId
	private NdcSpointBeginAppSelInitId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "CARDHOLDER_SELECT_SCREEN")
	private String cardholderSelectScreen;
	@Column(name = "FDK_TEMPLATE_EXT_NEXT")
	private String fdkTemplateExtNext;
	@Column(name = "ACTION_KEY_EXT_NEXT")
	private String actionKeyExtNext;
	@Column(name = "EXIT_PATH_EXT_NEXT")
	private String exitPathExtNext;
	@Column(name = "RESERVED_1")
	private String reserved1;
	@Column(name = "FDK_TEMPLATE_EXT_TYPE")
	private Character fdkTemplateExtType;
	@Column(name = "FDK_A_ICC_APP_NAME_TPL_SCREEN")
	private String fdkAIccAppNameTplScreen;
	@Column(name = "FDK_B_ICC_APP_NAME_TPL_SCREEN")
	private String fdkBIccAppNameTplScreen;
	@Column(name = "FDK_C_ICC_APP_NAME_TPL_SCREEN")
	private String fdkCIccAppNameTplScreen;
	@Column(name = "FDK_D_ICC_APP_NAME_TPL_SCREEN")
	private String fdkDIccAppNameTplScreen;
	@Column(name = "FDK_F_ICC_APP_NAME_TPL_SCREEN")
	private String fdkFIccAppNameTplScreen;
	@Column(name = "FDK_G_ICC_APP_NAME_TPL_SCREEN")
	private String fdkGIccAppNameTplScreen;
	@Column(name = "FDK_H_ICC_APP_NAME_TPL_SCREEN")
	private String fdkHIccAppNameTplScreen;
	@Column(name = "FDK_I_ICC_APP_NAME_TPL_SCREEN")
	private String fdkIIccAppNameTplScreen;
	@Column(name = "ACTION_KEY_EXT_TYPE")
	private Character actionKeyExtType;
	@Column(name = "MORE_APP_SCREEN")
	private String moreAppScreen;
	@Column(name = "FDK_MORE_APP")
	private String fdkMoreApp;
	@Column(name = "BACK_TO_START_SCREEN")
	private String backToStartScreen;
	@Column(name = "FDK_BACK_TO_START")
	private String fdkBackToStart;
	@Column(name = "RESERVED_2")
	private String reserved2;
	@Column(name = "EXIT_PATH_EXT_TYPE")
	private Character exitPathExtType;
	@Column(name = "TIMEOUT_NEXT_STATE")
	private String timeoutNextState;
	@Column(name = "CANCEL_NEXT_STATE")
	private String cancelNextState;
	@Column(name = "CARDHOLDER_SELECT_APP_NEXT")
	private String cardholderSelectAppNext;
	@Column(name = "AUTOMATIC_SELECT_APP_NEXT")
	private String automaticSelectAppNext;
	@Column(name = "NO_USABLE_APP_NEXT")
	private String noUsableAppNext;
	@Column(name = "RESERVED_3")
	private String reserved3;

	public NdcSpointBeginAppSelInitId getId() {
		return id;
	}

	public void setId(NdcSpointBeginAppSelInitId id) {
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

	public String getCardholderSelectScreen() {
		return cardholderSelectScreen;
	}

	public void setCardholderSelectScreen(String cardholderSelectScreen) {
		this.cardholderSelectScreen = cardholderSelectScreen;
	}

	public String getFdkTemplateExtNext() {
		return fdkTemplateExtNext;
	}

	public void setFdkTemplateExtNext(String fdkTemplateExtNext) {
		this.fdkTemplateExtNext = fdkTemplateExtNext;
	}

	public String getActionKeyExtNext() {
		return actionKeyExtNext;
	}

	public void setActionKeyExtNext(String actionKeyExtNext) {
		this.actionKeyExtNext = actionKeyExtNext;
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

	public Character getFdkTemplateExtType() {
		return fdkTemplateExtType;
	}

	public void setFdkTemplateExtType(Character fdkTemplateExtType) {
		this.fdkTemplateExtType = fdkTemplateExtType;
	}

	public String getFdkAIccAppNameTplScreen() {
		return fdkAIccAppNameTplScreen;
	}

	public void setFdkAIccAppNameTplScreen(String fdkAIccAppNameTplScreen) {
		this.fdkAIccAppNameTplScreen = fdkAIccAppNameTplScreen;
	}

	public String getFdkBIccAppNameTplScreen() {
		return fdkBIccAppNameTplScreen;
	}

	public void setFdkBIccAppNameTplScreen(String fdkBIccAppNameTplScreen) {
		this.fdkBIccAppNameTplScreen = fdkBIccAppNameTplScreen;
	}

	public String getFdkCIccAppNameTplScreen() {
		return fdkCIccAppNameTplScreen;
	}

	public void setFdkCIccAppNameTplScreen(String fdkCIccAppNameTplScreen) {
		this.fdkCIccAppNameTplScreen = fdkCIccAppNameTplScreen;
	}

	public String getFdkDIccAppNameTplScreen() {
		return fdkDIccAppNameTplScreen;
	}

	public void setFdkDIccAppNameTplScreen(String fdkDIccAppNameTplScreen) {
		this.fdkDIccAppNameTplScreen = fdkDIccAppNameTplScreen;
	}

	public String getFdkFIccAppNameTplScreen() {
		return fdkFIccAppNameTplScreen;
	}

	public void setFdkFIccAppNameTplScreen(String fdkFIccAppNameTplScreen) {
		this.fdkFIccAppNameTplScreen = fdkFIccAppNameTplScreen;
	}

	public String getFdkGIccAppNameTplScreen() {
		return fdkGIccAppNameTplScreen;
	}

	public void setFdkGIccAppNameTplScreen(String fdkGIccAppNameTplScreen) {
		this.fdkGIccAppNameTplScreen = fdkGIccAppNameTplScreen;
	}

	public String getFdkHIccAppNameTplScreen() {
		return fdkHIccAppNameTplScreen;
	}

	public void setFdkHIccAppNameTplScreen(String fdkHIccAppNameTplScreen) {
		this.fdkHIccAppNameTplScreen = fdkHIccAppNameTplScreen;
	}

	public String getFdkIIccAppNameTplScreen() {
		return fdkIIccAppNameTplScreen;
	}

	public void setFdkIIccAppNameTplScreen(String fdkIIccAppNameTplScreen) {
		this.fdkIIccAppNameTplScreen = fdkIIccAppNameTplScreen;
	}

	public Character getActionKeyExtType() {
		return actionKeyExtType;
	}

	public void setActionKeyExtType(Character actionKeyExtType) {
		this.actionKeyExtType = actionKeyExtType;
	}

	public String getMoreAppScreen() {
		return moreAppScreen;
	}

	public void setMoreAppScreen(String moreAppScreen) {
		this.moreAppScreen = moreAppScreen;
	}

	public String getFdkMoreApp() {
		return fdkMoreApp;
	}

	public void setFdkMoreApp(String fdkMoreApp) {
		this.fdkMoreApp = fdkMoreApp;
	}

	public String getBackToStartScreen() {
		return backToStartScreen;
	}

	public void setBackToStartScreen(String backToStartScreen) {
		this.backToStartScreen = backToStartScreen;
	}

	public String getFdkBackToStart() {
		return fdkBackToStart;
	}

	public void setFdkBackToStart(String fdkBackToStart) {
		this.fdkBackToStart = fdkBackToStart;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public Character getExitPathExtType() {
		return exitPathExtType;
	}

	public void setExitPathExtType(Character exitPathExtType) {
		this.exitPathExtType = exitPathExtType;
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

	public String getCardholderSelectAppNext() {
		return cardholderSelectAppNext;
	}

	public void setCardholderSelectAppNext(String cardholderSelectAppNext) {
		this.cardholderSelectAppNext = cardholderSelectAppNext;
	}

	public String getAutomaticSelectAppNext() {
		return automaticSelectAppNext;
	}

	public void setAutomaticSelectAppNext(String automaticSelectAppNext) {
		this.automaticSelectAppNext = automaticSelectAppNext;
	}

	public String getNoUsableAppNext() {
		return noUsableAppNext;
	}

	public void setNoUsableAppNext(String noUsableAppNext) {
		this.noUsableAppNext = noUsableAppNext;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
}