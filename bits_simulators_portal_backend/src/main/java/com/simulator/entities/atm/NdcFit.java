package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_FIT")
public class NdcFit {

	@EmbeddedId
	private NdcFitId id;
	@Column(name = "FIT_NAME")
	private String fitName;
	@Column(name = "PIIDX_INSTITUTION_ID_INDEX")
	private String piidxInstitutionIdIndex;
	@Column(name = "PFIID_INSTITUTION_ID")
	private String pfiidInstitutionId;
	@Column(name = "PSTDX_INDIRECT_NEXT_STATE")
	private String pstdxIndirectNextState;
	@Column(name = "PAGDX_ALGORITHM_BANK_ID_INDEX")
	private String pagdxAlgorithmBankIdIndex;
	@Column(name = "PMXPN_MAX_PIN_DIGITS_ENTERED")
	private String pmxpnMaxPinDigitsEntered;
	@Column(name = "PCKDX_MAX_PIN_DIGITS_CHECKED")
	private String pckdxMaxPinDigitsChecked;
	@Column(name = "PINPD_PIN_PAD")
	private String pinpdPinPad;
	@Column(name = "PANDX_PAN_DATA_INDEX")
	private String pandxPanDataIndex;
	@Column(name = "PANLN_PAN_DATA_LENGTH")
	private String panlnPanDataLength;
	@Column(name = "PANPD_PAN_PAD")
	private String panpdPanPad;
	@Column(name = "PRCNT_TRACK3_PIN_RETRAY_COUNT")
	private String prcntTrack3PinRetryCount;
	@Column(name = "POFDX_PIN_OFFSET_INDEX")
	private String pofdxPinOffsetIndex;
	@Column(name = "PDCTB_DECIMALISATION_TABLE")
	private String pdctbDecimalisationTable;
	@Column(name = "PEKEY_ENCRYPTED_PIN_KEY")
	private String pekeyEncryptedPinKey;
	@Column(name = "PINDX_PIN_INDEX_REF_POINT")
	private String pindxPinIndexRefPoint;
	@Column(name = "PLNDX_LANGUAGE_CODE_INDEX")
	private String plndxLanguageCodeIndex;

	public NdcFitId getId() {
		return id;
	}

	public void setId(NdcFitId id) {
		this.id = id;
	}

	public String getFitName() {
		return fitName;
	}

	public void setFitName(String fitName) {
		this.fitName = fitName;
	}

	public String getPiidxInstitutionIdIndex() {
		return piidxInstitutionIdIndex;
	}

	public void setPiidxInstitutionIdIndex(String piidxInstitutionIdIndex) {
		this.piidxInstitutionIdIndex = piidxInstitutionIdIndex;
	}

	public String getPfiidInstitutionId() {
		return pfiidInstitutionId;
	}

	public void setPfiidInstitutionId(String pfiidInstitutionId) {
		this.pfiidInstitutionId = pfiidInstitutionId;
	}

	public String getPstdxIndirectNextState() {
		return pstdxIndirectNextState;
	}

	public void setPstdxIndirectNextState(String pstdxIndirectNextState) {
		this.pstdxIndirectNextState = pstdxIndirectNextState;
	}

	public String getPagdxAlgorithmBankIdIndex() {
		return pagdxAlgorithmBankIdIndex;
	}

	public void setPagdxAlgorithmBankIdIndex(String pagdxAlgorithmBankIdIndex) {
		this.pagdxAlgorithmBankIdIndex = pagdxAlgorithmBankIdIndex;
	}

	public String getPmxpnMaxPinDigitsEntered() {
		return pmxpnMaxPinDigitsEntered;
	}

	public void setPmxpnMaxPinDigitsEntered(String pmxpnMaxPinDigitsEntered) {
		this.pmxpnMaxPinDigitsEntered = pmxpnMaxPinDigitsEntered;
	}

	public String getPckdxMaxPinDigitsChecked() {
		return pckdxMaxPinDigitsChecked;
	}

	public void setPckdxMaxPinDigitsChecked(String pckdxMaxPinDigitsChecked) {
		this.pckdxMaxPinDigitsChecked = pckdxMaxPinDigitsChecked;
	}

	public String getPinpdPinPad() {
		return pinpdPinPad;
	}

	public void setPinpdPinPad(String pinpdPinPad) {
		this.pinpdPinPad = pinpdPinPad;
	}

	public String getPandxPanDataIndex() {
		return pandxPanDataIndex;
	}

	public void setPandxPanDataIndex(String pandxPanDataIndex) {
		this.pandxPanDataIndex = pandxPanDataIndex;
	}

	public String getPanlnPanDataLength() {
		return panlnPanDataLength;
	}

	public void setPanlnPanDataLength(String panlnPanDataLength) {
		this.panlnPanDataLength = panlnPanDataLength;
	}

	public String getPanpdPanPad() {
		return panpdPanPad;
	}

	public void setPanpdPanPad(String panpdPanPad) {
		this.panpdPanPad = panpdPanPad;
	}

	public String getPrcntTrack3PinRetryCount() {
		return prcntTrack3PinRetryCount;
	}

	public void setPrcntTrack3PinRetryCount(String prcntTrack3PinRetryCount) {
		this.prcntTrack3PinRetryCount = prcntTrack3PinRetryCount;
	}

	public String getPofdxPinOffsetIndex() {
		return pofdxPinOffsetIndex;
	}

	public void setPofdxPinOffsetIndex(String pofdxPinOffsetIndex) {
		this.pofdxPinOffsetIndex = pofdxPinOffsetIndex;
	}

	public String getPdctbDecimalisationTable() {
		return pdctbDecimalisationTable;
	}

	public void setPdctbDecimalisationTable(String pdctbDecimalisationTable) {
		this.pdctbDecimalisationTable = pdctbDecimalisationTable;
	}

	public String getPekeyEncryptedPinKey() {
		return pekeyEncryptedPinKey;
	}

	public void setPekeyEncryptedPinKey(String pekeyEncryptedPinKey) {
		this.pekeyEncryptedPinKey = pekeyEncryptedPinKey;
	}

	public String getPindxPinIndexRefPoint() {
		return pindxPinIndexRefPoint;
	}

	public void setPindxPinIndexRefPoint(String pindxPinIndexRefPoint) {
		this.pindxPinIndexRefPoint = pindxPinIndexRefPoint;
	}

	public String getPlndxLanguageCodeIndex() {
		return plndxLanguageCodeIndex;
	}

	public void setPlndxLanguageCodeIndex(String plndxLanguageCodeIndex) {
		this.plndxLanguageCodeIndex = plndxLanguageCodeIndex;
	}
}