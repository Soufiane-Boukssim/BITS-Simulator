package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "autho_msg_log")
public class AuthoMsgLog {
    @EmbeddedId
    private AuthoMsgLogId authoMsgLogId;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    @Column(name = "SYSTEM_MODE",length = 3)
    private String systemMode;
    @Column(name = "MTI_REQ",length = 4)
    private String mtiReq;
    @Column(name = "MTI_REP",length = 4)
    private String mtiRes;
    @Column(name = "AUT_CASE_NO",length = 50)
    private String autCaseNo;
    @Column(name = "AUT_CASE_SET_ID",length = 50)
    private String autCaseSetId;
    @Column(name = "AUT_CASE_CARD_PRF",length = 50)
    private String autCaseCardPrf;
    @Column(name = "AUT_CASE_MRC_PRF",length = 50)
    private String autCaseMrcPrf;
    @Column(name = "AUT_CASE_TERM_PRF",length = 50)
    private String autCaseTermPrf;
    @Lob
    @Column(name = "AUT_BUFFER_REQ",length = 1000)
    private String autBufferReq;
    @Lob
    @Column(name = "AUT_BUFFER_REP",length = 1000)
    private String autBufferRep;
    @Column(name = "AUT_ACTION_CODE",length = 3)
    private String autActionCode;
    @Column(name = "AUT_REFERENCE",length = 20)
    private String autReference;
    @Column(name = "AUT_STAN",length = 11)
    private String autStan;

    @Column(name = "AUT_USER",length = 20)
    private String autUser;

    @Lob
    @Column(name = "CHECK_VERIF",length = 2000)
    private String checkVerif;

    public AuthoMsgLogId getAuthoMsgLogId() {
        return authoMsgLogId;
    }

    public void setAuthoMsgLogId(AuthoMsgLogId authoMsgLogId) {
        this.authoMsgLogId = authoMsgLogId;
    }

    public String getSystemMode() {
        return systemMode;
    }

    public void setSystemMode(String systemMode) {
        this.systemMode = systemMode;
    }

    public String getMtiReq() {
        return mtiReq;
    }

    public void setMtiReq(String mtiReq) {
        this.mtiReq = mtiReq;
    }

    public String getMtiRep() {
        return mtiRes;
    }

    public void setMtiRep(String mtiRep) {
        this.mtiRes = mtiRep;
    }

    public String getAutCaseNo() {
        return autCaseNo;
    }

    public void setAutCaseNo(String autCaseNo) {
        this.autCaseNo = autCaseNo;
    }

    public String getAutCaseSetId() {
        return autCaseSetId;
    }

    public void setAutCaseSetId(String autCaseSetId) {
        this.autCaseSetId = autCaseSetId;
    }

    public String getAutCaseCardPrf() {
        return autCaseCardPrf;
    }

    public void setAutCaseCardPrf(String autCaseCardPrf) {
        this.autCaseCardPrf = autCaseCardPrf;
    }

    public String getAutCaseMrcPrf() {
        return autCaseMrcPrf;
    }

    public void setAutCaseMrcPrf(String autCaseMrcPrf) {
        this.autCaseMrcPrf = autCaseMrcPrf;
    }

    public String getAutCaseTermPrf() {
        return autCaseTermPrf;
    }

    public void setAutCaseTermPrf(String autCaseTermPrf) {
        this.autCaseTermPrf = autCaseTermPrf;
    }

    public String getAutBufferReq() {
        return autBufferReq;
    }

    public void setAutBufferReq(String autBufferReq) {
        this.autBufferReq = autBufferReq;
    }

    public String getAutBufferRep() {
        return autBufferRep;
    }

    public void setAutBufferRep(String autBufferRep) {
        this.autBufferRep = autBufferRep;
    }

    public String getAutActionCode() {
        return autActionCode;
    }

    public void setAutActionCode(String autActionCode) {
        this.autActionCode = autActionCode;
    }

    public String getAutReference() {
        return autReference;
    }

    public void setAutReference(String autReference) {
        this.autReference = autReference;
    }

    public String getAutStan() {
        return autStan;
    }

    public void setAutStan(String autStan) {
        this.autStan = autStan;
    }

    public String getAutUser() {
        return autUser;
    }

    public void setAutUser(String autUser) {
        this.autUser = autUser;
    }

    public String getCheckVerif() {
        return checkVerif;
    }

    public void setCheckVerif(String checkVerif) {
        this.checkVerif = checkVerif;
    }

    public AuthoMsgLog(AuthoMsgLogId authoMsgLogId, String systemMode, String mtiReq, String mtiRep, String autCaseNo, String autCaseSetId, String autCaseCardPrf, String autCaseMrcPrf, String autCaseTermPrf, String autBufferReq, String autBufferRep, String autActionCode, String autReference, String autStan, String autUser, String checkVerif) {
        this.authoMsgLogId = authoMsgLogId;
        this.systemMode = systemMode;
        this.mtiReq = mtiReq;
        this.mtiRes = mtiRep;
        this.autCaseNo = autCaseNo;
        this.autCaseSetId = autCaseSetId;
        this.autCaseCardPrf = autCaseCardPrf;
        this.autCaseMrcPrf = autCaseMrcPrf;
        this.autCaseTermPrf = autCaseTermPrf;
        this.autBufferReq = autBufferReq;
        this.autBufferRep = autBufferRep;
        this.autActionCode = autActionCode;
        this.autReference = autReference;
        this.autStan = autStan;
        this.autUser = autUser;
        this.checkVerif = checkVerif;
    }

    public AuthoMsgLog() {
    }

    @Override
    public String toString() {
        return "AuthoMsgLog{" +
                "authoMsgLogId=" + authoMsgLogId +
                ", systemMode='" + systemMode + '\'' +
                ", mtiReq='" + mtiReq + '\'' +
                ", mtiRep='" + mtiRes + '\'' +
                ", autCaseNo='" + autCaseNo + '\'' +
                ", autCaseSetId='" + autCaseSetId + '\'' +
                ", autCaseCardPrf='" + autCaseCardPrf + '\'' +
                ", autCaseMrcPrf='" + autCaseMrcPrf + '\'' +
                ", autCaseTermPrf='" + autCaseTermPrf + '\'' +
                ", autBufferReq='" + autBufferReq + '\'' +
                ", autBufferRep='" + autBufferRep + '\'' +
                ", autActionCode='" + autActionCode + '\'' +
                ", autReference='" + autReference + '\'' +
                ", autStan='" + autStan + '\'' +
                ", autUser='" + autUser + '\'' +
                ", checkVerif='" + checkVerif + '\'' +
                '}';
    }
}
