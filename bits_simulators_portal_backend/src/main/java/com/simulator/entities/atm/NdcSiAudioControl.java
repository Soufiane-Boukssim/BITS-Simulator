package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SI_AUDIO_CONTROL")
public class NdcSiAudioControl {

	@EmbeddedId
	private NdcSiAudioControlId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "PROMPT_SCREEN")
	private String promptScreen;
	@Column(name = "TIMEOUT_NEXT_STATE")
	private String timeoutNextState;
	@Column(name = "FUNCT_COMPLET_NEXT")
	private String functCompletNext;
	@Column(name = "INCREASE_VOLUM_FDK_MASK")
	private String increaseVolumFdkMask;
	@Column(name = "DECREASE_VOLUM_FDK_MASK")
	private String decreaseVolumFdkMask;
	@Column(name = "AUDIO_CONTROLE")
	private String audioControle;
	@Column(name = "AUDIO_MESSAGE")
	private String audioMessage;

	public NdcSiAudioControlId getId() {
		return id;
	}

	public void setId(NdcSiAudioControlId id) {
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

	public String getPromptScreen() {
		return promptScreen;
	}

	public void setPromptScreen(String promptScreen) {
		this.promptScreen = promptScreen;
	}

	public String getTimeoutNextState() {
		return timeoutNextState;
	}

	public void setTimeoutNextState(String timeoutNextState) {
		this.timeoutNextState = timeoutNextState;
	}

	public String getFunctCompletNext() {
		return functCompletNext;
	}

	public void setFunctCompletNext(String functCompletNext) {
		this.functCompletNext = functCompletNext;
	}

	public String getIncreaseVolumFdkMask() {
		return increaseVolumFdkMask;
	}

	public void setIncreaseVolumFdkMask(String increaseVolumFdkMask) {
		this.increaseVolumFdkMask = increaseVolumFdkMask;
	}

	public String getDecreaseVolumFdkMask() {
		return decreaseVolumFdkMask;
	}

	public void setDecreaseVolumFdkMask(String decreaseVolumFdkMask) {
		this.decreaseVolumFdkMask = decreaseVolumFdkMask;
	}

	public String getAudioControle() {
		return audioControle;
	}

	public void setAudioControle(String audioControle) {
		this.audioControle = audioControle;
	}

	public String getAudioMessage() {
		return audioMessage;
	}

	public void setAudioMessage(String audioMessage) {
		this.audioMessage = audioMessage;
	}
}