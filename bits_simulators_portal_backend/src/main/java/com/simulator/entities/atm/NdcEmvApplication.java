package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_EMV_APPLICATION")
public class NdcEmvApplication {

	@EmbeddedId
	private NdcEmvApplicationId id;
	@Column(name = "FULL_PARTIAL_PRIMARY_AID")
	private String fullPartialPrimaryAid;
	@Column(name = "DEFAULT_APPLI_LABEL")
	private String defaultAppliLabel;
	@Column(name = "PRIMARY_AID_ICC_APPLI_TYPE")
	private String primaryAidIccAppliType;
	@Column(name = "PRIMARY_AID_LOW_APPLI_VER")
	private String primaryAidLowAppliVer;
	@Column(name = "PRIMARY_AID_HIGH_APPLI_VER")
	private String primaryAidHighAppliVer;
	@Column(name = "PRIMARY_AID_TERM_ACTION")
	private String primaryAidTermAction;
	@Column(name = "TRANS_REQ_DATA")
	private String transReqData;
	@Column(name = "COMPLETED_DATA")
	private String completedData;

	public NdcEmvApplicationId getId() {
		return id;
	}

	public void setId(NdcEmvApplicationId id) {
		this.id = id;
	}

	public String getFullPartialPrimaryAid() {
		return fullPartialPrimaryAid;
	}

	public void setFullPartialPrimaryAid(String fullPartialPrimaryAid) {
		this.fullPartialPrimaryAid = fullPartialPrimaryAid;
	}

	public String getDefaultAppliLabel() {
		return defaultAppliLabel;
	}

	public void setDefaultAppliLabel(String defaultAppliLabel) {
		this.defaultAppliLabel = defaultAppliLabel;
	}

	public String getPrimaryAidIccAppliType() {
		return primaryAidIccAppliType;
	}

	public void setPrimaryAidIccAppliType(String primaryAidIccAppliType) {
		this.primaryAidIccAppliType = primaryAidIccAppliType;
	}

	public String getPrimaryAidLowAppliVer() {
		return primaryAidLowAppliVer;
	}

	public void setPrimaryAidLowAppliVer(String primaryAidLowAppliVer) {
		this.primaryAidLowAppliVer = primaryAidLowAppliVer;
	}

	public String getPrimaryAidHighAppliVer() {
		return primaryAidHighAppliVer;
	}

	public void setPrimaryAidHighAppliVer(String primaryAidHighAppliVer) {
		this.primaryAidHighAppliVer = primaryAidHighAppliVer;
	}

	public String getPrimaryAidTermAction() {
		return primaryAidTermAction;
	}

	public void setPrimaryAidTermAction(String primaryAidTermAction) {
		this.primaryAidTermAction = primaryAidTermAction;
	}

	public String getTransReqData() {
		return transReqData;
	}

	public void setTransReqData(String transReqData) {
		this.transReqData = transReqData;
	}

	public String getCompletedData() {
		return completedData;
	}

	public void setCompletedData(String completedData) {
		this.completedData = completedData;
	}
}