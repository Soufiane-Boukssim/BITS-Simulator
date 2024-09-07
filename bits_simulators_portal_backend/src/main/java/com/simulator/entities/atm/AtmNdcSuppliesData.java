package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ATM_NDC_SUPPLIES_DATA")
public class AtmNdcSuppliesData {

	@EmbeddedId
	private AtmNdcSuppliesDataId id;
	@Column(name = "SUPPLIES_DATA")
	private String suppliesData;

	public AtmNdcSuppliesDataId getId() {
		return id;
	}

	public void setId(AtmNdcSuppliesDataId id) {
		this.id = id;
	}

	public String getSuppliesData() {
		return suppliesData;
	}

	public void setSuppliesData(String suppliesData) {
		this.suppliesData = suppliesData;
	}
}