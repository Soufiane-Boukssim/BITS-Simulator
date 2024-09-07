package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_EMV_TRANSACTION")
public class NdcEmvTransaction {

	@EmbeddedId
	private NdcEmvTransactionId id;
	@Column(name = "TRANSACTION_CATEGORY_CODE")
	private String transactionCategoryCode;

	public NdcEmvTransactionId getId() {
		return id;
	}

	public void setId(NdcEmvTransactionId id) {
		this.id = id;
	}

	public String getTransactionCategoryCode() {
		return transactionCategoryCode;
	}

	public void setTransactionCategoryCode(String transactionCategoryCode) {
		this.transactionCategoryCode = transactionCategoryCode;
	}
}