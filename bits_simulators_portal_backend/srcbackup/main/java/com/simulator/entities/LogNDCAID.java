package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "terminal_ndc_aid")
public class LogNDCAID {
    @Id
    @Column(name = "entry_number", nullable = false, length = 2)
    private String entryNumber;
    @Column(name = "fullAID",length = 255)
    private String fullAID;
    @Column(name = "application_label",length = 255)
    private String applicationLabel;
    @Column(name = "LAVN",length = 4)
    private String LAVN;
    @Column(name = "HAVN",length = 4)
    private String HAVN;

    @Column(name = "TAC_Decline",length = 10)
    private String TACDecline;

    @Column(name = "TAGs_Trs_Req",length = 255)
    private String TAGSTrsReq;

    @Column(name = "TAGs_Completion",length = 255)
    private String TAGSCompletion;

    @Column(name = "secondary_AID",length = 255)
    private String secondaryAID;

    public LogNDCAID(){}
    public LogNDCAID(String entryNumber, String fullAID, String applicationLabel, String LAVN, String HAVN, String TACDecline, String TAGSTrsReq, String TAGSCompletion, String secondaryAID) {
        this.entryNumber = entryNumber;
        this.fullAID = fullAID;
        this.applicationLabel = applicationLabel;
        this.LAVN = LAVN;
        this.HAVN = HAVN;
        this.TACDecline = TACDecline;
        this.TAGSTrsReq = TAGSTrsReq;
        this.TAGSCompletion = TAGSCompletion;
        this.secondaryAID = secondaryAID;
    }

    public String getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(String entryNumber) {
        this.entryNumber = entryNumber;
    }

    public String getFullAID() {
        return fullAID;
    }

    public void setFullAID(String fullAID) {
        this.fullAID = fullAID;
    }

    public String getApplicationLabel() {
        return applicationLabel;
    }

    public void setApplicationLabel(String applicationLabel) {
        this.applicationLabel = applicationLabel;
    }

    public String getLAVN() {
        return LAVN;
    }

    public void setLAVN(String LAVN) {
        this.LAVN = LAVN;
    }

    public String getHAVN() {
        return HAVN;
    }

    public void setHAVN(String HAVN) {
        this.HAVN = HAVN;
    }

    public String getTACDecline() {
        return TACDecline;
    }

    public void setTACDecline(String TACDecline) {
        this.TACDecline = TACDecline;
    }

    public String getTAGSTrsReq() {
        return TAGSTrsReq;
    }

    public void setTAGSTrsReq(String TAGSTrsReq) {
        this.TAGSTrsReq = TAGSTrsReq;
    }

    public String getTAGSCompletion() {
        return TAGSCompletion;
    }

    public void setTAGSCompletion(String TAGSCompletion) {
        this.TAGSCompletion = TAGSCompletion;
    }

    public String getSecondaryAID() {
        return secondaryAID;
    }

    public void setSecondaryAID(String secondaryAID) {
        this.secondaryAID = secondaryAID;
    }

    @Override
    public String toString() {
        return "TerminalNDCAID{" +
                "entryNumber='" + entryNumber + '\'' +
                ", fullAID='" + fullAID + '\'' +
                ", applicationLabel='" + applicationLabel + '\'' +
                ", LAVN='" + LAVN + '\'' +
                ", HAVN='" + HAVN + '\'' +
                ", TACDecline='" + TACDecline + '\'' +
                ", TAGSTrsReq='" + TAGSTrsReq + '\'' +
                ", TAGSCompletion='" + TAGSCompletion + '\'' +
                ", secondaryAID='" + secondaryAID + '\'' +
                '}';
    }
}
