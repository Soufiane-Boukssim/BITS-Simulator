package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_STATE")
public class NdcState {

	@EmbeddedId
	private NdcStateId id;
	@Column(name = "STATE_TYPE")
	private Character stateType;
	@Column(name = "STATE_WORDING")
	private String stateWording;
	@Column(name = "STATE_1")
	private String state1;
	@Column(name = "STATE_2")
	private String state2;
	@Column(name = "STATE_3")
	private String state3;
	@Column(name = "STATE_4")
	private String state4;
	@Column(name = "STATE_5")
	private String state5;
	@Column(name = "STATE_6")
	private String state6;
	@Column(name = "STATE_7")
	private String state7;
	@Column(name = "STATE_8")
	private String state8;
	@Column(name = "SCREEN_1")
	private String screen1;
	@Column(name = "SCREEN_2")
	private String screen2;
	@Column(name = "SCREEN_3")
	private String screen3;
	@Column(name = "SCREEN_4")
	private String screen4;
	@Column(name = "SCREEN_5")
	private String screen5;
	@Column(name = "SCREEN_6")
	private String screen6;
	@Column(name = "SCREEN_7")
	private String screen7;
	@Column(name = "SCREEN_8")
	private String screen8;

	public NdcStateId getId() {
		return id;
	}

	public void setId(NdcStateId id) {
		this.id = id;
	}

	public Character getStateType() {
		return stateType;
	}

	public void setStateType(Character stateType) {
		this.stateType = stateType;
	}

	public String getStateWording() {
		return stateWording;
	}

	public void setStateWording(String stateWording) {
		this.stateWording = stateWording;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getState3() {
		return state3;
	}

	public void setState3(String state3) {
		this.state3 = state3;
	}

	public String getState4() {
		return state4;
	}

	public void setState4(String state4) {
		this.state4 = state4;
	}

	public String getState5() {
		return state5;
	}

	public void setState5(String state5) {
		this.state5 = state5;
	}

	public String getState6() {
		return state6;
	}

	public void setState6(String state6) {
		this.state6 = state6;
	}

	public String getState7() {
		return state7;
	}

	public void setState7(String state7) {
		this.state7 = state7;
	}

	public String getState8() {
		return state8;
	}

	public void setState8(String state8) {
		this.state8 = state8;
	}

	public String getScreen1() {
		return screen1;
	}

	public void setScreen1(String screen1) {
		this.screen1 = screen1;
	}

	public String getScreen2() {
		return screen2;
	}

	public void setScreen2(String screen2) {
		this.screen2 = screen2;
	}

	public String getScreen3() {
		return screen3;
	}

	public void setScreen3(String screen3) {
		this.screen3 = screen3;
	}

	public String getScreen4() {
		return screen4;
	}

	public void setScreen4(String screen4) {
		this.screen4 = screen4;
	}

	public String getScreen5() {
		return screen5;
	}

	public void setScreen5(String screen5) {
		this.screen5 = screen5;
	}

	public String getScreen6() {
		return screen6;
	}

	public void setScreen6(String screen6) {
		this.screen6 = screen6;
	}

	public String getScreen7() {
		return screen7;
	}

	public void setScreen7(String screen7) {
		this.screen7 = screen7;
	}

	public String getScreen8() {
		return screen8;
	}

	public void setScreen8(String screen8) {
		this.screen8 = screen8;
	}
}