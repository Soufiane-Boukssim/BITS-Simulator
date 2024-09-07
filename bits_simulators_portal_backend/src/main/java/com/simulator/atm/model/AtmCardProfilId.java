package com.simulator.atm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmCardProfilId implements Serializable {
    @Column(name = "CARD_NO", nullable = false,length = 20)
    private String cardNo;

//    @Column(name = "CARD_PROTO",length = 2, nullable = false)
//    private Character cardProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public AtmCardProfilId(String cardNumber) {
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

//    public Character getCardProtocol() {
//        return cardProtocol;
//    }
//
//    public void setCardProtocol(Character cardProtocol) {
//        this.cardProtocol = cardProtocol;
//    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
