package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_EMV_TERMINAL")
public class NdcEmvTerminal {

	@EmbeddedId
	private NdcEmvTerminalId id;
	@Column(name = "TERM_TYPE")
	private String termType;

	public NdcEmvTerminalId getId() {
		return id;
	}

	public void setId(NdcEmvTerminalId id) {
		this.id = id;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}
}