package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.AuthoMsgLog;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.iso.model.AuthoMsgLogSearchParams;
import com.simulator.repository.AuthoMsgLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public  class AuthMsgLogService {
    private final Logger logger = LogManager.getLogger(AuthMsgLogService.class);
    @Autowired
    AuthoMsgLogRepository authoMsgLogRepository;

    public ResponseApiJson<List<AuthoMsgLog>> getAllMessage(AuthoMsgLogSearchParams authoMsgLogSearchParams) {
        String idRequest = "GAM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Date dateFrom = parseDate(authoMsgLogSearchParams.getDateFromStr(), "00:00:00");
            Date dateTo = parseDate(authoMsgLogSearchParams.getDateToStr(), "23:59:59");
            List<AuthoMsgLog> mtiDefs = authoMsgLogRepository.findByAuthoMsgLogIdAutDateTimeBetweenAndAuthoMsgLogIdAutProtocolAndAuthoMsgLogIdInstCode(
                    dateFrom, dateTo, authoMsgLogSearchParams.getProtocol(), authoMsgLogSearchParams.getInstCode());

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all messages succesfully  !", mtiDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMessage please check with your provider !");

        }
    }






    private Date parseDate(String dateStr, String defaultTime) {
        if (dateStr == null || dateStr.isEmpty()) {
            dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr + " " + defaultTime);
        } catch (ParseException e) {
            // Handle parsing exception, log or throw as needed
            e.printStackTrace();
            return null;
        }
    }
}





