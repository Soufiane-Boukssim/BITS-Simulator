package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SMINUS_AUTOMATIC_LANG_SEL")
public class NdcSminusAutomaticLangSel {

	@EmbeddedId
	private NdcSminusAutomaticLangSelId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "LANGUAGE_MATCH_NEXT")
	private String languageMatchNext;
	@Column(name = "NO_LANGUAGE_MATCH_NEXT")
	private String noLanguageMatchNext;

	public NdcSminusAutomaticLangSelId getId() {
		return id;
	}

	public void setId(NdcSminusAutomaticLangSelId id) {
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

	public String getLanguageMatchNext() {
		return languageMatchNext;
	}

	public void setLanguageMatchNext(String languageMatchNext) {
		this.languageMatchNext = languageMatchNext;
	}

	public String getNoLanguageMatchNext() {
		return noLanguageMatchNext;
	}

	public void setNoLanguageMatchNext(String noLanguageMatchNext) {
		this.noLanguageMatchNext = noLanguageMatchNext;
	}
}