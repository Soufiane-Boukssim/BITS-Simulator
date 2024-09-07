package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SK_FIT_SWITCH")
public class NdcSkFitSwitch {

	@EmbeddedId
	private NdcSkFitSwitchId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "NEXT_STATE_INDEX_0")
	private String nextStateIndex0;
	@Column(name = "NEXT_STATE_INDEX_1")
	private String nextStateIndex1;
	@Column(name = "NEXT_STATE_INDEX_2")
	private String nextStateIndex2;
	@Column(name = "NEXT_STATE_INDEX_3")
	private String nextStateIndex3;
	@Column(name = "NEXT_STATE_INDEX_4")
	private String nextStateIndex4;
	@Column(name = "NEXT_STATE_INDEX_5")
	private String nextStateIndex5;
	@Column(name = "NEXT_STATE_INDEX_6")
	private String nextStateIndex6;
	@Column(name = "NEXT_STATE_INDEX_7")
	private String nextStateIndex7;

	public NdcSkFitSwitchId getId() {
		return id;
	}

	public void setId(NdcSkFitSwitchId id) {
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

	public String getNextStateIndex0() {
		return nextStateIndex0;
	}

	public void setNextStateIndex0(String nextStateIndex0) {
		this.nextStateIndex0 = nextStateIndex0;
	}

	public String getNextStateIndex1() {
		return nextStateIndex1;
	}

	public void setNextStateIndex1(String nextStateIndex1) {
		this.nextStateIndex1 = nextStateIndex1;
	}

	public String getNextStateIndex2() {
		return nextStateIndex2;
	}

	public void setNextStateIndex2(String nextStateIndex2) {
		this.nextStateIndex2 = nextStateIndex2;
	}

	public String getNextStateIndex3() {
		return nextStateIndex3;
	}

	public void setNextStateIndex3(String nextStateIndex3) {
		this.nextStateIndex3 = nextStateIndex3;
	}

	public String getNextStateIndex4() {
		return nextStateIndex4;
	}

	public void setNextStateIndex4(String nextStateIndex4) {
		this.nextStateIndex4 = nextStateIndex4;
	}

	public String getNextStateIndex5() {
		return nextStateIndex5;
	}

	public void setNextStateIndex5(String nextStateIndex5) {
		this.nextStateIndex5 = nextStateIndex5;
	}

	public String getNextStateIndex6() {
		return nextStateIndex6;
	}

	public void setNextStateIndex6(String nextStateIndex6) {
		this.nextStateIndex6 = nextStateIndex6;
	}

	public String getNextStateIndex7() {
		return nextStateIndex7;
	}

	public void setNextStateIndex7(String nextStateIndex7) {
		this.nextStateIndex7 = nextStateIndex7;
	}
}