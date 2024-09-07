package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_activity")
public class TerminalActivity {
    @Id
    @Column(name = "terminal_number", nullable = false,length = 15)
    private String terminalNumber;
    @Column(name = "last_tsn", length = 4)
    private String lastTsn;
    @Column(name = "accumulated_tsn", length = 4)
    private String accumulatedTsn;
    @Column(name = "cassette1_type",length = 3)
    private String cassette1Type;
    @Column(name = "cassette1_disponible")
    private int cassette1Disponible;
    @Column(name = "cassette1_distribuer")
    private int cassette1Distribuer;
    @Column(name = "cassette1_reject")
    private int cassette1Reject;
    @Column(name = "cassette1_last_note")
    private int cassette1LastNote;


    @Column(name = "cassette2_type",length = 3)
    private String cassette2Type;
    @Column(name = "cassette2_disponible")
    private int cassette2Disponible;
    @Column(name = "cassette2_distribuer")
    private int cassette2Distribuer;
    @Column(name = "cassette2_reject")
    private int cassette2Reject;
    @Column(name = "cassette2_last_note")
    private int cassette2LastNote;

    @Column(name = "cassette3_type",length = 3)
    private String cassette3Type;
    @Column(name = "cassette3_disponible")
    private int cassette3Disponible;
    @Column(name = "cassette3_distribuer")
    private int cassette3Distribuer;
    @Column(name = "cassette3_reject")
    private int cassette3Reject;
    @Column(name = "cassette3_last_note")
    private int cassette3LastNote;

    @Column(name = "cassette4_type",length = 3)
    private String cassette4Type;
    @Column(name = "cassette4_disponible")
    private int cassette4Disponible;
    @Column(name = "cassette4_distribuer")
    private int cassette4Distribuer;
    @Column(name = "cassette4_reject")
    private int cassette4Reject;
    @Column(name = "cassette4_last_note")
    private int cassette4LastNote;

    @Column(name = "card_captured_nbr")
    private int cardCapturedNbr;
    @Column(name = "envlope_deposit_nbr")
    private int envlopeDepositNbr;
    @Column(name = "camera_film_remaining")
    private int cameraFilmRemaining;

    @Column(name = "laste_cleared", length = 30)
    private String lasteCleared;

    @Override
    public String toString() {
        return "TerminalActivity{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", lastTsn='" + lastTsn + '\'' +
                ", accumulatedTsn='" + accumulatedTsn + '\'' +
                ", cassette1Type='" + cassette1Type + '\'' +
                ", cassette1Disponible=" + cassette1Disponible +
                ", cassette1Distribuer=" + cassette1Distribuer +
                ", cassette1Reject=" + cassette1Reject +
                ", cassette1LastNote=" + cassette1LastNote +
                ", cassette2Type='" + cassette2Type + '\'' +
                ", cassette2Disponible=" + cassette2Disponible +
                ", cassette2Distribuer=" + cassette2Distribuer +
                ", cassette2Reject=" + cassette2Reject +
                ", cassette2LastNote=" + cassette2LastNote +
                ", cassette3Type='" + cassette3Type + '\'' +
                ", cassette3Disponible=" + cassette3Disponible +
                ", cassette3Distribuer=" + cassette3Distribuer +
                ", cassette3Reject=" + cassette3Reject +
                ", cassette3LastNote=" + cassette3LastNote +
                ", cassette4Type='" + cassette4Type + '\'' +
                ", cassette4Disponible=" + cassette4Disponible +
                ", cassette4Distribuer=" + cassette4Distribuer +
                ", cassette4Reject=" + cassette4Reject +
                ", cassette4LastNote=" + cassette4LastNote +
                ", cardCapturedNbr=" + cardCapturedNbr +
                ", envlopeDepositNbr=" + envlopeDepositNbr +
                ", cameraFilmRemaining=" + cameraFilmRemaining +
                ", lasteCleared='" + lasteCleared + '\'' +
                '}';
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getLastTsn() {
        return lastTsn;
    }

    public void setLastTsn(String lastTsn) {
        this.lastTsn = lastTsn;
    }

    public String getAccumulatedTsn() {
        return accumulatedTsn;
    }

    public void setAccumulatedTsn(String accumulatedTsn) {
        this.accumulatedTsn = accumulatedTsn;
    }

    public String getCassette1Type() {
        return cassette1Type;
    }

    public void setCassette1Type(String cassette1Type) {
        this.cassette1Type = cassette1Type;
    }

    public int getCassette1Disponible() {
        return cassette1Disponible;
    }

    public void setCassette1Disponible(int cassette1Disponible) {
        this.cassette1Disponible = cassette1Disponible;
    }

    public int getCassette1Distribuer() {
        return cassette1Distribuer;
    }

    public void setCassette1Distribuer(int cassette1Distribuer) {
        this.cassette1Distribuer = cassette1Distribuer;
    }

    public int getCassette1Reject() {
        return cassette1Reject;
    }

    public void setCassette1Reject(int cassette1Reject) {
        this.cassette1Reject = cassette1Reject;
    }

    public int getCassette1LastNote() {
        return cassette1LastNote;
    }

    public void setCassette1LastNote(int cassette1LastNote) {
        this.cassette1LastNote = cassette1LastNote;
    }

    public String getCassette2Type() {
        return cassette2Type;
    }

    public void setCassette2Type(String cassette2Type) {
        this.cassette2Type = cassette2Type;
    }

    public int getCassette2Disponible() {
        return cassette2Disponible;
    }

    public void setCassette2Disponible(int cassette2Disponible) {
        this.cassette2Disponible = cassette2Disponible;
    }

    public int getCassette2Distribuer() {
        return cassette2Distribuer;
    }

    public void setCassette2Distribuer(int cassette2Distribuer) {
        this.cassette2Distribuer = cassette2Distribuer;
    }

    public int getCassette2Reject() {
        return cassette2Reject;
    }

    public void setCassette2Reject(int cassette2Reject) {
        this.cassette2Reject = cassette2Reject;
    }

    public int getCassette2LastNote() {
        return cassette2LastNote;
    }

    public void setCassette2LastNote(int cassette2LastNote) {
        this.cassette2LastNote = cassette2LastNote;
    }

    public String getCassette3Type() {
        return cassette3Type;
    }

    public void setCassette3Type(String cassette3Type) {
        this.cassette3Type = cassette3Type;
    }

    public int getCassette3Disponible() {
        return cassette3Disponible;
    }

    public void setCassette3Disponible(int cassette3Disponible) {
        this.cassette3Disponible = cassette3Disponible;
    }

    public int getCassette3Distribuer() {
        return cassette3Distribuer;
    }

    public void setCassette3Distribuer(int cassette3Distribuer) {
        this.cassette3Distribuer = cassette3Distribuer;
    }

    public int getCassette3Reject() {
        return cassette3Reject;
    }

    public void setCassette3Reject(int cassette3Reject) {
        this.cassette3Reject = cassette3Reject;
    }

    public int getCassette3LastNote() {
        return cassette3LastNote;
    }

    public void setCassette3LastNote(int cassette3LastNote) {
        this.cassette3LastNote = cassette3LastNote;
    }

    public String getCassette4Type() {
        return cassette4Type;
    }

    public void setCassette4Type(String cassette4Type) {
        this.cassette4Type = cassette4Type;
    }

    public int getCassette4Disponible() {
        return cassette4Disponible;
    }

    public void setCassette4Disponible(int cassette4Disponible) {
        this.cassette4Disponible = cassette4Disponible;
    }

    public int getCassette4Distribuer() {
        return cassette4Distribuer;
    }

    public void setCassette4Distribuer(int cassette4Distribuer) {
        this.cassette4Distribuer = cassette4Distribuer;
    }

    public int getCassette4Reject() {
        return cassette4Reject;
    }

    public void setCassette4Reject(int cassette4Reject) {
        this.cassette4Reject = cassette4Reject;
    }

    public int getCassette4LastNote() {
        return cassette4LastNote;
    }

    public void setCassette4LastNote(int cassette4LastNote) {
        this.cassette4LastNote = cassette4LastNote;
    }

    public int getCardCapturedNbr() {
        return cardCapturedNbr;
    }

    public void setCardCapturedNbr(int cardCapturedNbr) {
        this.cardCapturedNbr = cardCapturedNbr;
    }

    public int getEnvlopeDepositNbr() {
        return envlopeDepositNbr;
    }

    public void setEnvlopeDepositNbr(int envlopeDepositNbr) {
        this.envlopeDepositNbr = envlopeDepositNbr;
    }

    public int getCameraFilmRemaining() {
        return cameraFilmRemaining;
    }

    public void setCameraFilmRemaining(int cameraFilmRemaining) {
        this.cameraFilmRemaining = cameraFilmRemaining;
    }

    public String getLasteCleared() {
        return lasteCleared;
    }

    public void setLasteCleared(String lasteCleared) {
        this.lasteCleared = lasteCleared;
    }

    public TerminalActivity() {
    }

    public TerminalActivity(String terminalNumber, String lastTsn, String accumulatedTsn, String cassette1Type, int cassette1Disponible, int cassette1Distribuer, int cassette1Reject, int cassette1LastNote, String cassette2Type, int cassette2Disponible, int cassette2Distribuer, int cassette2Reject, int cassette2LastNote, String cassette3Type, int cassette3Disponible, int cassette3Distribuer, int cassette3Reject, int cassette3LastNote, String cassette4Type, int cassette4Disponible, int cassette4Distribuer, int cassette4Reject, int cassette4LastNote, int cardCapturedNbr, int envlopeDepositNbr, int cameraFilmRemaining, String lasteCleared) {
        this.terminalNumber = terminalNumber;
        this.lastTsn = lastTsn;
        this.accumulatedTsn = accumulatedTsn;
        this.cassette1Type = cassette1Type;
        this.cassette1Disponible = cassette1Disponible;
        this.cassette1Distribuer = cassette1Distribuer;
        this.cassette1Reject = cassette1Reject;
        this.cassette1LastNote = cassette1LastNote;
        this.cassette2Type = cassette2Type;
        this.cassette2Disponible = cassette2Disponible;
        this.cassette2Distribuer = cassette2Distribuer;
        this.cassette2Reject = cassette2Reject;
        this.cassette2LastNote = cassette2LastNote;
        this.cassette3Type = cassette3Type;
        this.cassette3Disponible = cassette3Disponible;
        this.cassette3Distribuer = cassette3Distribuer;
        this.cassette3Reject = cassette3Reject;
        this.cassette3LastNote = cassette3LastNote;
        this.cassette4Type = cassette4Type;
        this.cassette4Disponible = cassette4Disponible;
        this.cassette4Distribuer = cassette4Distribuer;
        this.cassette4Reject = cassette4Reject;
        this.cassette4LastNote = cassette4LastNote;
        this.cardCapturedNbr = cardCapturedNbr;
        this.envlopeDepositNbr = envlopeDepositNbr;
        this.cameraFilmRemaining = cameraFilmRemaining;
        this.lasteCleared = lasteCleared;
    }
}
