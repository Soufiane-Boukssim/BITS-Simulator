package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "ATM_NDC_FITNESS_DATA_LOG")
public class AtmNdcFitnessDataLog {

	@Id
	@Column(name = "bankCode")
	private String bankCode;
	@Column(name = "ATM_TERMINAL_NUMBER")
	private String atmTerminalNumber;
	@Column(name = "FITNESS_TS")
	private Timestamp fitnessTimestamp;
	@Column(name = "FITNESS_DATA")
	private String fitnessData;

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

	public Timestamp getFitnessTimestamp() {
		return fitnessTimestamp;
	}

	public void setFitnessTimestamp(Timestamp fitnessTimestamp) {
		this.fitnessTimestamp = fitnessTimestamp;
	}

	public String getFitnessData() {
		return fitnessData;
	}

	public void setFitnessData(String fitnessData) {
		this.fitnessData = fitnessData;
	}
}