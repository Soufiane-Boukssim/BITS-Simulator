package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SD_PRESET_OPERATION")
public class NdcSdPresetOperation {

	@EmbeddedId
	private NdcSdPresetOperationId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "NEXT_STATE")
	private String nextState;
	@Column(name = "CLEAR_MASK")
	private String clearMask;
	@Column(name = "PRESET_MASK_A")
	private String presetMaskA;
	@Column(name = "PRESET_MASK_B")
	private String presetMaskB;
	@Column(name = "PRESET_MASK_C")
	private String presetMaskC;
	@Column(name = "PRESET_MASK_D")
	private String presetMaskD;
	@Column(name = "INCLUDE_EXT_STATE")
	private Character includeExtState;
	@Column(name = "EXT_STATE_NUMBER")
	private String extStateNumber;
	@Column(name = "PRESET_MASK_F")
	private String presetMaskF;
	@Column(name = "PRESET_MASK_G")
	private String presetMaskG;
	@Column(name = "PRESET_MASK_H")
	private String presetMaskH;
	@Column(name = "PRESET_MASK_I")
	private String presetMaskI;
	@Column(name = "RESERVED")
	private String reserved;

	public NdcSdPresetOperationId getId() {
		return id;
	}

	public void setId(NdcSdPresetOperationId id) {
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

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	public String getClearMask() {
		return clearMask;
	}

	public void setClearMask(String clearMask) {
		this.clearMask = clearMask;
	}

	public String getPresetMaskA() {
		return presetMaskA;
	}

	public void setPresetMaskA(String presetMaskA) {
		this.presetMaskA = presetMaskA;
	}

	public String getPresetMaskB() {
		return presetMaskB;
	}

	public void setPresetMaskB(String presetMaskB) {
		this.presetMaskB = presetMaskB;
	}

	public String getPresetMaskC() {
		return presetMaskC;
	}

	public void setPresetMaskC(String presetMaskC) {
		this.presetMaskC = presetMaskC;
	}

	public String getPresetMaskD() {
		return presetMaskD;
	}

	public void setPresetMaskD(String presetMaskD) {
		this.presetMaskD = presetMaskD;
	}

	public Character getIncludeExtState() {
		return includeExtState;
	}

	public void setIncludeExtState(Character includeExtState) {
		this.includeExtState = includeExtState;
	}

	public String getExtStateNumber() {
		return extStateNumber;
	}

	public void setExtStateNumber(String extStateNumber) {
		this.extStateNumber = extStateNumber;
	}

	public String getPresetMaskF() {
		return presetMaskF;
	}

	public void setPresetMaskF(String presetMaskF) {
		this.presetMaskF = presetMaskF;
	}

	public String getPresetMaskG() {
		return presetMaskG;
	}

	public void setPresetMaskG(String presetMaskG) {
		this.presetMaskG = presetMaskG;
	}

	public String getPresetMaskH() {
		return presetMaskH;
	}

	public void setPresetMaskH(String presetMaskH) {
		this.presetMaskH = presetMaskH;
	}

	public String getPresetMaskI() {
		return presetMaskI;
	}

	public void setPresetMaskI(String presetMaskI) {
		this.presetMaskI = presetMaskI;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
}