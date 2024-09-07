package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_SW_FDK_SWITCH")
public class NdcSwFdkSwitch {

	@EmbeddedId
	private NdcSwFdkSwitchId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "FDK_A_NEXT_STATE")
	private String fdkANextState;
	@Column(name = "FDK_B_NEXT_STATE")
	private String fdkBNextState;
	@Column(name = "FDK_C_NEXT_STATE")
	private String fdkCNextState;
	@Column(name = "FDK_D_NEXT_STATE")
	private String fdkDNextState;
	@Column(name = "FDK_F_NEXT_STATE")
	private String fdkFNextState;
	@Column(name = "FDK_G_NEXT_STATE")
	private String fdkGNextState;
	@Column(name = "FDK_H_NEXT_STATE")
	private String fdkHNextState;
	@Column(name = "FDK_I_NEXT_STATE")
	private String fdkINextState;

	public NdcSwFdkSwitchId getId() {
		return id;
	}

	public void setId(NdcSwFdkSwitchId id) {
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

	public String getFdkANextState() {
		return fdkANextState;
	}

	public void setFdkANextState(String fdkANextState) {
		this.fdkANextState = fdkANextState;
	}

	public String getFdkBNextState() {
		return fdkBNextState;
	}

	public void setFdkBNextState(String fdkBNextState) {
		this.fdkBNextState = fdkBNextState;
	}

	public String getFdkCNextState() {
		return fdkCNextState;
	}

	public void setFdkCNextState(String fdkCNextState) {
		this.fdkCNextState = fdkCNextState;
	}

	public String getFdkDNextState() {
		return fdkDNextState;
	}

	public void setFdkDNextState(String fdkDNextState) {
		this.fdkDNextState = fdkDNextState;
	}

	public String getFdkFNextState() {
		return fdkFNextState;
	}

	public void setFdkFNextState(String fdkFNextState) {
		this.fdkFNextState = fdkFNextState;
	}

	public String getFdkGNextState() {
		return fdkGNextState;
	}

	public void setFdkGNextState(String fdkGNextState) {
		this.fdkGNextState = fdkGNextState;
	}

	public String getFdkHNextState() {
		return fdkHNextState;
	}

	public void setFdkHNextState(String fdkHNextState) {
		this.fdkHNextState = fdkHNextState;
	}

	public String getFdkINextState() {
		return fdkINextState;
	}

	public void setFdkINextState(String fdkINextState) {
		this.fdkINextState = fdkINextState;
	}
}