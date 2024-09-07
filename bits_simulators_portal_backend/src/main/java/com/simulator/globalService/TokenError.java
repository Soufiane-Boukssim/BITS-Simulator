package com.simulator.globalService;

import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.util.Date;

public class TokenError {

    private static final Logger logger = LogManager.getLogger(TokenError.class);
    public ResponseApiJson<?> issue(int i) {
        logger.info("issue number : " + i);
        String idRequest = "VT_" + new Date().getTime() + (Math.random() * 9999);
        JSONArray jsonArray = new JSONArray();
        ResponseApiJson<String> responseApiJson;
        if (i == 1) {
            logger.info("missing token");
            responseApiJson = new ResponseApiJson<>(idRequest,
                    "003",
                    "missing token",
                    "missing token"
            );
        }
        else if (i == 0)
            responseApiJson = new ResponseApiJson<>(idRequest,
                    "004",
                    "invalid token",
                    "invalid token");
        else if (i == 2)
            responseApiJson = new ResponseApiJson<>(idRequest,
                    "005",
                    "Expired token",
                    "Expired token");
        else if (i == 3)
            responseApiJson = new ResponseApiJson<>(idRequest,
                    "007",
                    "Access denied",
                    "Access denied"
            );
        else if (i == 4) {
            responseApiJson = new ResponseApiJson<>(idRequest,
                    "006",
                    "Revoked token",
                    "Revoked token");
        }
        else if (i == 5) {
            return new ResponseApiJson<>(
                    idRequest,
                    "001",
                    "Invalid Credentials !",
                    "Invalid Credentials !"
            );
        } else {
            return new ResponseApiJson<>(
                    idRequest,
                    "001",
                    "Error occurred !"
            );
        }
        return responseApiJson;
    }
}