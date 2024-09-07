package com.simulator.iso.cryptoKey.cryptoKeyController;


import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.cryptoKey.CryproKeyService.DESSun;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/Crypto/key")
@RequiredArgsConstructor
public class CryptoKeyController {

    @Autowired
    DESSun desSun;


    @PostMapping("/encrypt")
    public ResponseApiJson<String> encryptData(@RequestBody EncryptionRequest request) {
        String idRequest = "ENCRYPT_" + new Date().getTime() + (Math.random() * 9999);
        try {
            String encryptedResult = desSun.encrypt(request.getKey(), request.getData());
            return new ResponseApiJson<>(idRequest ,"000", "Encryption successful", encryptedResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseApiJson<>(idRequest, "001", "Encryption failed", null);
        }
    }



    static class EncryptionRequest {
        private String key;
        private String data;

        // Getters and setters
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


}
