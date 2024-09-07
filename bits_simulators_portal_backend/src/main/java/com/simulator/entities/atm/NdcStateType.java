package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_STATE_TYPE")
public class NdcStateType {

	@Id
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "STATE_ENTRY_2")
	private String stateEntry2;
	@Column(name = "STATE_ENTRY_3")
	private String stateEntry3;
	@Column(name = "STATE_ENTRY_4")
	private String stateEntry4;
	@Column(name = "STATE_ENTRY_5")
	private String stateEntry5;
	@Column(name = "STATE_ENTRY_6")
	private String stateEntry6;
	@Column(name = "STATE_ENTRY_7")
	private String stateEntry7;
	@Column(name = "STATE_ENTRY_8")
	private String stateEntry8;
	@Column(name = "STATE_ENTRY_9")
	private String stateEntry9;

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

	public String getStateEntry2() {
		return stateEntry2;
	}

	public void setStateEntry2(String stateEntry2) {
		this.stateEntry2 = stateEntry2;
	}

	public String getStateEntry3() {
		return stateEntry3;
	}

	public void setStateEntry3(String stateEntry3) {
		this.stateEntry3 = stateEntry3;
	}

	public String getStateEntry4() {
		return stateEntry4;
	}

	public void setStateEntry4(String stateEntry4) {
		this.stateEntry4 = stateEntry4;
	}

	public String getStateEntry5() {
		return stateEntry5;
	}

	public void setStateEntry5(String stateEntry5) {
		this.stateEntry5 = stateEntry5;
	}

	public String getStateEntry6() {
		return stateEntry6;
	}

	public void setStateEntry6(String stateEntry6) {
		this.stateEntry6 = stateEntry6;
	}

	public String getStateEntry7() {
		return stateEntry7;
	}

	public void setStateEntry7(String stateEntry7) {
		this.stateEntry7 = stateEntry7;
	}

	public String getStateEntry8() {
		return stateEntry8;
	}

	public void setStateEntry8(String stateEntry8) {
		this.stateEntry8 = stateEntry8;
	}

	public String getStateEntry9() {
		return stateEntry9;
	}

	public void setStateEntry9(String stateEntry9) {
		this.stateEntry9 = stateEntry9;
	}
}