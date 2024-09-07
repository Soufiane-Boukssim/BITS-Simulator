package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ATM_NDC_FITNESS_DATA")
public class ATMNdcFitnessData {

	@EmbeddedId
	private ATMNdcFitnessDataId id;
	@Column(name = "FITNESS_DATA")
	private String fitnessData;

	public ATMNdcFitnessDataId getId() {
		return id;
	}

	public void setId(ATMNdcFitnessDataId id) {
		this.id = id;
	}

	public String getFitnessData() {
		return fitnessData;
	}

	public void setFitnessData(String fitnessData) {
		this.fitnessData = fitnessData;
	}
}