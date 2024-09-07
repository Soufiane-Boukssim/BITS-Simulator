package com.simulator.iso.model;

import com.simulator.entities.CardProfileId;
import com.simulator.entities.IccProfile;
import lombok.Data;

import java.util.List;

@Data
public class CardProfileWithIccModel {

    private CardProfileId id;

    private String cardNumber;
    //@Column(name = "CARD_TYPE",length = 2)
    private String cardType;

    //@Column(name = "ISSUER_NAME",length = 40)
    private String issuerName;

    //@Column(name = "CARD_DESC",length = 40)
    private String cardDesc;

    //@Column(name = "ISSUER_CODE",length = 40)
    private String issuerCode;

    //@Column(name = "BIN",length = 12)
    private String bin;

    //@Column(name = "ACCOUNT_CURR",length = 3)
    private String accountCurr;

    //@Column(name = "ACCOUNT_TYPE",length = 3)
    private String accountType;

    //@Column(name = "ACC_ACCMOUNT")
    private double accAmount;

    //@Column(name = "AVAILABLE")
    private double available;

    //@Column(name = "CARD_EXPIRY",length = 8)
    private String cardExpiry;

    //@Column(name = "CVN_VAL",length = 3)
    private String cvnVal;

    //@Column(name = "CVN2_VAL",length = 7)
    private String cvn2Val;

    //@Column(name = "PIN",length = 12)
    private String PIN;

    //@Column(name = "CF_TYPE",length = 2)
    private String cfType;

    //@Column(name = "CF_NO",length = 32)
    private String cfNo;

    //@Column(name = "FIRST_NAME",length = 50)
    private String firstName;

    //@Column(name = "LAST_NAME",length = 50)
    private String lastName;

    //@Column(name = "MOBILE",length = 20)
    private String mobile;

    //@Column(name = "TRACK1",length = 79)
    private String track1;

    //@Column(name = "TRACK2",length = 37)
    private String track2;

    //@Column(name = "TRACK3",length = 104)
    private String track3;

    //@Column(name = "IC_AID",length = 20)
    private String icAid;

    //@Column(name = "IC_PROFILE",length = 4)
    private String icProfile;

    //@Column(name = "IC_MK_AC",length = 128)
    private String icMkAc;

    //@Column(name = "IC_MK_SMC",length = 128)
    private String icMkSmc;

    //@Column(name = "IC_MK_SMI",length = 128)
    private String icMkSmi;

    private List<IccProfile> tags;
}