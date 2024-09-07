package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "inst_param")
@Data
public class InstParam {
    @EmbeddedId
    private InstParamId id;

    @Column(name = "INST_DESC", length = 70)
    private String instDesc;

    @Column(name = "INST_TRANS_CUR1", length = 3)
    private String instTransCur1;

    @Column(name = "INST_TRANS_CUR2", length = 3)
    private String instTransCur2;

    @Column(name = "INST_TRANS_CUR3", length = 3)
    private String instTransCur3;

    @Column(name = "INST_SETT_CURR", length = 3)
    private String instSettCurr;

    @Column(name = "SINGLE_MSG")
    private Character singleMsg;

    @Column(name = "PIN_KEY", length = 256)
    private String pinKey;

    @Column(name = "MAC_KEY", length = 256)
    private String macKey;

    @Column(name = "Pin_format")
    private Integer pinFormat;

    @Column(name = "Pin_key_len")
    private Integer pinKeyLen;

    @Column(name = "MAC_key_len")
    private Integer macKeyLen;

    @Column(name = "MASTER_KEY", length = 256)
    private String masterKey;

    @Column(name = "CHIP_SUPPORTED")
    private Character chipSupported;

    @Column(name = "TIME_OUT_COMMS")
    private Integer timeOutComms;

    @Column(name = "ACQ_CODE")
    private String acqCode;

    public String getAcqCode() {
        return acqCode;
    }

    public void setAcqCode(String acqCode) {
        this.acqCode = acqCode;
    }

    public InstParamId getId() {
        return id;
    }

    public void setId(InstParamId id) {
        this.id = id;
    }

    public String getInstDesc() {
        return instDesc;
    }

    public void setInstDesc(String instDesc) {
        this.instDesc = instDesc;
    }

    public String getInstTransCur1() {
        return instTransCur1;
    }

    public void setInstTransCur1(String instTransCur1) {
        this.instTransCur1 = instTransCur1;
    }

    public String getInstTransCur2() {
        return instTransCur2;
    }

    public void setInstTransCur2(String instTransCur2) {
        this.instTransCur2 = instTransCur2;
    }

    public String getInstTransCur3() {
        return instTransCur3;
    }

    public void setInstTransCur3(String instTransCur3) {
        this.instTransCur3 = instTransCur3;
    }

    public String getInstSettCurr() {
        return instSettCurr;
    }

    public void setInstSettCurr(String instSettCurr) {
        this.instSettCurr = instSettCurr;
    }

    public Character getSingleMsg() {
        return singleMsg;
    }

    public void setSingleMsg(Character singleMsg) {
        this.singleMsg = singleMsg;
    }

    public String getPinKey() {
        return pinKey;
    }

    public void setPinKey(String pinKey) {
        this.pinKey = pinKey;
    }

    public String getMacKey() {
        return macKey;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey;
    }

    public Integer getPinFormat() {
        return pinFormat;
    }

    public void setPinFormat(Integer pinFormat) {
        this.pinFormat = pinFormat;
    }

    public Integer getPinKeyLen() {
        return pinKeyLen;
    }

    public void setPinKeyLen(Integer pinKeyLen) {
        this.pinKeyLen = pinKeyLen;
    }

    public Integer getMacKeyLen() {
        return macKeyLen;
    }

    public void setMacKeyLen(Integer macKeyLen) {
        this.macKeyLen = macKeyLen;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    public Character getChipSupported() {
        return chipSupported;
    }

    public void setChipSupported(Character chipSupported) {
        this.chipSupported = chipSupported;
    }

    public Integer getTimeOutComms() {
        return timeOutComms;
    }

    public void setTimeOutComms(Integer timeOutComms) {
        this.timeOutComms = timeOutComms;
    }

    @Override
    public String toString() {
        return "InstParam{" +
                "id=" + id +
                ", instDesc='" + instDesc + '\'' +
                ", instTransCur1='" + instTransCur1 + '\'' +
                ", instTransCur2='" + instTransCur2 + '\'' +
                ", instTransCur3='" + instTransCur3 + '\'' +
                ", instSettCurr='" + instSettCurr + '\'' +
                ", singleMsg=" + singleMsg +
                ", pinKey='" + pinKey + '\'' +
                ", macKey='" + macKey + '\'' +
                ", pinFormat=" + pinFormat +
                ", pinKeyLen=" + pinKeyLen +
                ", macKeyLen=" + macKeyLen +
                ", masterKey='" + masterKey + '\'' +
                ", chipSupported=" + chipSupported +
                ", timeOutComms=" + timeOutComms +
                ", acqCode='" + acqCode + '\'' +
                '}';
    }
}
