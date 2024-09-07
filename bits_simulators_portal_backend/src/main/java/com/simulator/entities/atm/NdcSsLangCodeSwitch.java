package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SS_LANG_CODE_SWITCH")
public class NdcSsLangCodeSwitch {

	@EmbeddedId
	private NdcSsLangCodeSwitchId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "NO_LANG_CODE_NEXT_STATE")
	private String noLangCodeNextState;
	@Column(name = "LANG_CODE_0_NEXT_STATE")
	private String langCode0NextState;
	@Column(name = "LANG_CODE_1_NEXT_STATE")
	private String langCode1NextState;
	@Column(name = "LANG_CODE_2_NEXT_STATE")
	private String langCode2NextState;
	@Column(name = "LANG_CODE_3_NEXT_STATE")
	private String langCode3NextState;
	@Column(name = "LANG_CODE_4_NEXT_STATE")
	private String langCode4NextState;
	@Column(name = "LANG_CODE_5_NEXT_STATE")
	private String langCode5NextState;
	@Column(name = "INCLUDE_EXTENSION_STATE")
	private Character includeExtensionState;
	@Column(name = "EXTENSION_STATE_NUMBER")
	private String extensionStateNumber;
	@Column(name = "EXTENSION_STATE_TYPE")
	private Character extensionStateType;
	@Column(name = "LANG_CODE_6_NEXT_STATE")
	private String langCode6NextState;
	@Column(name = "LANG_CODE_7_NEXT_STATE")
	private String langCode7NextState;
	@Column(name = "LANG_CODE_8_NEXT_STATE")
	private String langCode8NextState;
	@Column(name = "LANG_CODE_9_NEXT_STATE")
	private String langCode9NextState;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSsLangCodeSwitchId getId() {
		return id;
	}

	public void setId(NdcSsLangCodeSwitchId id) {
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

	public String getNoLangCodeNextState() {
		return noLangCodeNextState;
	}

	public void setNoLangCodeNextState(String noLangCodeNextState) {
		this.noLangCodeNextState = noLangCodeNextState;
	}

	public String getLangCode0NextState() {
		return langCode0NextState;
	}

	public void setLangCode0NextState(String langCode0NextState) {
		this.langCode0NextState = langCode0NextState;
	}

	public String getLangCode1NextState() {
		return langCode1NextState;
	}

	public void setLangCode1NextState(String langCode1NextState) {
		this.langCode1NextState = langCode1NextState;
	}

	public String getLangCode2NextState() {
		return langCode2NextState;
	}

	public void setLangCode2NextState(String langCode2NextState) {
		this.langCode2NextState = langCode2NextState;
	}

	public String getLangCode3NextState() {
		return langCode3NextState;
	}

	public void setLangCode3NextState(String langCode3NextState) {
		this.langCode3NextState = langCode3NextState;
	}

	public String getLangCode4NextState() {
		return langCode4NextState;
	}

	public void setLangCode4NextState(String langCode4NextState) {
		this.langCode4NextState = langCode4NextState;
	}

	public String getLangCode5NextState() {
		return langCode5NextState;
	}

	public void setLangCode5NextState(String langCode5NextState) {
		this.langCode5NextState = langCode5NextState;
	}

	public Character getIncludeExtensionState() {
		return includeExtensionState;
	}

	public void setIncludeExtensionState(Character includeExtensionState) {
		this.includeExtensionState = includeExtensionState;
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

	public String getLangCode6NextState() {
		return langCode6NextState;
	}

	public void setLangCode6NextState(String langCode6NextState) {
		this.langCode6NextState = langCode6NextState;
	}

	public String getLangCode7NextState() {
		return langCode7NextState;
	}

	public void setLangCode7NextState(String langCode7NextState) {
		this.langCode7NextState = langCode7NextState;
	}

	public String getLangCode8NextState() {
		return langCode8NextState;
	}

	public void setLangCode8NextState(String langCode8NextState) {
		this.langCode8NextState = langCode8NextState;
	}

	public String getLangCode9NextState() {
		return langCode9NextState;
	}

	public void setLangCode9NextState(String langCode9NextState) {
		this.langCode9NextState = langCode9NextState;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}