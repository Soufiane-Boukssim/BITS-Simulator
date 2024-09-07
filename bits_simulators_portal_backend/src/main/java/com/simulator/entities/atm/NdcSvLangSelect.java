package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SV_LANG_SELECT")
public class NdcSvLangSelect {

	@EmbeddedId
	private NdcSvLangSelectId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "NEXT_STATE")
	private Character nextState;
	@Column(name = "LANGUAGE_CODE_GROUP_1")
	private Character languageCodeGroup1;
	@Column(name = "LANGUAGE_CODE_GROUP_2")
	private Character languageCodeGroup2;
	@Column(name = "LANGUAGE_CODE_GROUP_3")
	private Character languageCodeGroup3;
	@Column(name = "LANGUAGE_CODE_GROUP_4")
	private Character languageCodeGroup4;
	@Column(name = "LANGUAGE_CODE_GROUP_5")
	private Character languageCodeGroup5;
	@Column(name = "LANGUAGE_CODE_GROUP_6")
	private Character languageCodeGroup6;
	@Column(name = "SCREEN_GROUP_SIZE")
	private Character screenGroupSize;

	public NdcSvLangSelectId getId() {
		return id;
	}

	public void setId(NdcSvLangSelectId id) {
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

	public Character getNextState() {
		return nextState;
	}

	public void setNextState(Character nextState) {
		this.nextState = nextState;
	}

	public Character getLanguageCodeGroup1() {
		return languageCodeGroup1;
	}

	public void setLanguageCodeGroup1(Character languageCodeGroup1) {
		this.languageCodeGroup1 = languageCodeGroup1;
	}

	public Character getLanguageCodeGroup2() {
		return languageCodeGroup2;
	}

	public void setLanguageCodeGroup2(Character languageCodeGroup2) {
		this.languageCodeGroup2 = languageCodeGroup2;
	}

	public Character getLanguageCodeGroup3() {
		return languageCodeGroup3;
	}

	public void setLanguageCodeGroup3(Character languageCodeGroup3) {
		this.languageCodeGroup3 = languageCodeGroup3;
	}

	public Character getLanguageCodeGroup4() {
		return languageCodeGroup4;
	}

	public void setLanguageCodeGroup4(Character languageCodeGroup4) {
		this.languageCodeGroup4 = languageCodeGroup4;
	}

	public Character getLanguageCodeGroup5() {
		return languageCodeGroup5;
	}

	public void setLanguageCodeGroup5(Character languageCodeGroup5) {
		this.languageCodeGroup5 = languageCodeGroup5;
	}

	public Character getLanguageCodeGroup6() {
		return languageCodeGroup6;
	}

	public void setLanguageCodeGroup6(Character languageCodeGroup6) {
		this.languageCodeGroup6 = languageCodeGroup6;
	}

	public Character getScreenGroupSize() {
		return screenGroupSize;
	}

	public void setScreenGroupSize(Character screenGroupSize) {
		this.screenGroupSize = screenGroupSize;
	}
}