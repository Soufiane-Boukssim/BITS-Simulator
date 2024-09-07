package com.simulator.iso.controllers;


import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.services.CardHolder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/cardHolder-validate")
@RequiredArgsConstructor
public class CardHolderController {

    @Autowired
    CardHolder cardHolder;

    private final Logger logger = LogManager.getLogger(FunctController.class);
    @PostMapping("/validateCard")
    public ResponseApiJson<?> validateCard(@RequestBody CardValidationRequest cardHolderRequest) {
        String idRequest = "Card-V" + new Date().getTime() + (Math.random() * 9999);
        try {
            // Create a CardHolder object with PAN and expiration date
            CardHolder card = new CardHolder(cardHolderRequest.getPan(), cardHolderRequest.getExp());

            // Check if the card is expired
            if (card.isExpired()) {
                return new ResponseApiJson<>(idRequest, "500", "Card is expired", null);
            }

            return new ResponseApiJson<>(idRequest, "000", "Card is valid", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>(idRequest, "001", "Error validating card", null);
        }
    }

    @PostMapping("/validateCardTrack1")
    public ResponseApiJson<String> validateCardName(@RequestBody CardHolder cardHolderRequest) {
        String idRequest = "Card-Name" + new Date().getTime() + (Math.random() * 9999);
        if (cardHolderRequest.getTrack1() != null) {
            // User inserted track1
            String nameOnCard = cardHolderRequest.getNameOnCard();
            String pan = cardHolderRequest.getPAN();
            String exp=cardHolderRequest.getEXP();

            logger.info("pan ---->::"+pan);
            logger.info("exp--->"+exp);

            return   new ResponseApiJson<>(idRequest, "0001", "Success", nameOnCard);
        } else {
            return  new ResponseApiJson<>("idRequest", "400", "Track1 data is missing");

        }
    }



    @PostMapping("/validateCardTrack2")
    public ResponseApiJson<?> validateCardTrack2(@RequestBody Map<String, String> requestBody) {
        String idRequest = "Card-Track2-" + new Date().getTime() + (Math.random() * 9999);
        try {
            String track2 = requestBody.get("track2");
            cardHolder.parseTrack2(track2); // Cette méthode lève une exception si le track2 est invalide

            String pan = cardHolder.getPAN();
            String exp = cardHolder.getEXP();
            String trailler = cardHolder.getTrailler();


            CardData cardData = new CardData(pan, exp, trailler);

            return  new ResponseApiJson<>(idRequest, "0001", "Success", cardData);

        } catch (Exception e) {
             return new ResponseApiJson<>("idRequest", "400", e.getMessage());

        }
    }


    static class CardData {
        private String pan;
        private String exp;
        private String trailler;

        public CardData(String pan, String exp, String trailler) {
            this.pan = pan;
            this.exp = exp;
            this.trailler = trailler;

        }
        // getter setter for trailler
        public String getTrailler() {
            return trailler;
        }

        public void setTrailler(String trailler) {
            this.trailler = trailler;
        }




        // Getters and setters
        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }
    }

    static class CardValidationRequest {
        private String pan;
        private String exp;

        // Getters and setters
        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getExp() {
            return exp;
        }

        public void setExp(String exp) {
            this.exp = exp;
        }
    }

}
