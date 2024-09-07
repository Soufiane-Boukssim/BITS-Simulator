package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TMP")
public class Tmp {

    @Id
    @Column(name = "PROFILE_CODE", length = 10)
    private String profileCode;

    @Column(name = "FIT_CODE", length = 10)
    private String fitCode;

    @Column(name = "FIT_NAME", length = 100)
    private String fitName;

    @Column(name = "PIIDX_INSTITUTION_ID_INDEX")
    private int piidxInstitutionIdIndex;

    @Column(name = "PFIID_INSTITUTION_ID", length = 100)
    private String pfiidInstitutionId;

    @Column(name = "PSTDX_INDIRECT_NEXT_STATE")
    private int pstDxIndirectNextState;

    @Column(name = "PAGDX_ALGORITHM_BANK_ID_INDEX")
    private int pagDxAlgorithmBankIdIndex;

    @Column(name = "PMXPN_MAX_PIN_DIGITS_ENTERED")
    private int pmxPnMaxPinDigitsEntered;

    @Column(name = "PCKDX_MAX_PIN_DIGITS_CHECKED")
    private int pckDxMaxPinDigitsChecked;

    @Column(name = "PINPD_PIN_PAD")
    private int pinPdPinPad;

    @Column(name = "PANDX_PAN_DATA_INDEX")
    private int panDxPanDataIndex;

    @Column(name = "PANLN_PAN_DATA_LENGTH")
    private int panLnPanDataLength;

    @Column(name = "PANPD_PAN_PAD")
    private int panPdPanPad;

    @Column(name = "PRCNT_TRACK3_PIN_RETRY_COUNT")
    private int prcntTrack3PinRetryCount;

    @Column(name = "POFDX_PIN_OFFSET_INDEX")
    private int pofDxPinOffsetIndex;

    @Column(name = "PDCTB_DECIMALISATION_TABLE", length = 100)
    private String pdctbDecimalisationTable;

    @Column(name = "PEKEY_ENCRYPTED_PIN_KEY", length = 100)
    private String pekeyEncryptedPinKey;

    @Column(name = "PINDX_PIN_INDEX_REF_POINT", length = 100)
    private String pindxPinIndexRefPoint;

    @Column(name = "PLNDX_LANGUAGE_CODE_INDEX")
    private int plndxLanguageCodeIndex;

    public Tmp() {
    }

    public Tmp(String profileCode, String fitCode, String fitName, int piidxInstitutionIdIndex, String pfiidInstitutionId, int pstDxIndirectNextState, int pagDxAlgorithmBankIdIndex, int pmxPnMaxPinDigitsEntered, int pckDxMaxPinDigitsChecked, int pinPdPinPad, int panDxPanDataIndex, int panLnPanDataLength, int panPdPanPad, int prcntTrack3PinRetryCount, int pofDxPinOffsetIndex, String pdctbDecimalisationTable, String pekeyEncryptedPinKey, String pindxPinIndexRefPoint, int plndxLanguageCodeIndex) {
        this.profileCode = profileCode;
        this.fitCode = fitCode;
        this.fitName = fitName;
        this.piidxInstitutionIdIndex = piidxInstitutionIdIndex;
        this.pfiidInstitutionId = pfiidInstitutionId;
        this.pstDxIndirectNextState = pstDxIndirectNextState;
        this.pagDxAlgorithmBankIdIndex = pagDxAlgorithmBankIdIndex;
        this.pmxPnMaxPinDigitsEntered = pmxPnMaxPinDigitsEntered;
        this.pckDxMaxPinDigitsChecked = pckDxMaxPinDigitsChecked;
        this.pinPdPinPad = pinPdPinPad;
        this.panDxPanDataIndex = panDxPanDataIndex;
        this.panLnPanDataLength = panLnPanDataLength;
        this.panPdPanPad = panPdPanPad;
        this.prcntTrack3PinRetryCount = prcntTrack3PinRetryCount;
        this.pofDxPinOffsetIndex = pofDxPinOffsetIndex;
        this.pdctbDecimalisationTable = pdctbDecimalisationTable;
        this.pekeyEncryptedPinKey = pekeyEncryptedPinKey;
        this.pindxPinIndexRefPoint = pindxPinIndexRefPoint;
        this.plndxLanguageCodeIndex = plndxLanguageCodeIndex;
    }
}
