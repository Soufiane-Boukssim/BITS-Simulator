package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class AtmNdcSuppliesDataLogId implements Serializable {

	@Column(name = "BANK_CODE")
	private String bankCode;
	@Column(name = "ATM_TERMINAL_NUMBER")
	private String atmTerminalNumber;
	@Column(name = "SUPPLIES_TS")
	private Timestamp suppliesTs;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAtmTerminalNumber() {
		return atmTerminalNumber;
	}

	public void setAtmTerminalNumber(String atmTerminalNumber) {
		this.atmTerminalNumber = atmTerminalNumber;
	}

	public Timestamp getSuppliesTs() {
		return suppliesTs;
	}

	public void setSuppliesTs(Timestamp suppliesTs) {
		this.suppliesTs = suppliesTs;
	}
}