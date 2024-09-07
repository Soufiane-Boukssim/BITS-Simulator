package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
public class CardProfileId implements Serializable {

    @Column(name = "CARD_NO", nullable = false,length = 19)
    private String cardNo;

    @Column(name = "CARD_PROTO",length = 1, nullable = false)
    private Character cardProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public CardProfileId(String cardNumber) {
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Character getCardProtocol() {
        return cardProtocol;
    }

    public void setCardProtocol(Character cardProtocol) {
        this.cardProtocol = cardProtocol;
    }

    public CardProfileId(String cardNo, Character cardProtocol) {
        this.cardNo = cardNo;
        this.cardProtocol = cardProtocol;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
