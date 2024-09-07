package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_DISPLAY_ANIMED")
public class NdcDisplayAnimed {

	@EmbeddedId
	private NdcDisplayAnimedId id;
	@Column(name = "SCREEN_NUMBER")
	private String screenNumber;
	@Column(name = "SCREEN_ORDER")
	private int screenOrder;
	@Column(name = "SCREEN_TIMER")
	private int screenTimer;

	public NdcDisplayAnimedId getId() {
		return id;
	}

	public void setId(NdcDisplayAnimedId id) {
		this.id = id;
	}

	public String getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(String screenNumber) {
		this.screenNumber = screenNumber;
	}

	public int getScreenOrder() {
		return screenOrder;
	}

	public void setScreenOrder(int screenOrder) {
		this.screenOrder = screenOrder;
	}

	public int getScreenTimer() {
		return screenTimer;
	}

	public void setScreenTimer(int screenTimer) {
		this.screenTimer = screenTimer;
	}
}