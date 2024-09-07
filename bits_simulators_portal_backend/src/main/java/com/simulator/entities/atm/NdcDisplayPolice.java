package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_DISPLAY_POLICE")
public class NdcDisplayPolice {

	@Id
	@Column(name = "policeCode")
	private String policeCode;
	@Column(name = "POLICE_DESIGNATOR")
	private String policeDesignator;
	@Column(name = "POLICE_NAME")
	private String policeName;
	@Column(name = "POLICE_SIZE")
	private int policeSize;

	public String getPoliceCode() {
		return policeCode;
	}

	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}

	public String getPoliceDesignator() {
		return policeDesignator;
	}

	public void setPoliceDesignator(String policeDesignator) {
		this.policeDesignator = policeDesignator;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public int getPoliceSize() {
		return policeSize;
	}

	public void setPoliceSize(int policeSize) {
		this.policeSize = policeSize;
	}
}