package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.pos.PosAuthoMsgLog;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.model.PosAuthoMsgLogSearchParams;
import com.simulator.repository.pos.PosAuthoMsgLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public  class PosAuthMsgLogService {
    private final Logger logger = LogManager.getLogger(PosAuthMsgLogService.class);
    @Autowired
    PosAuthoMsgLogRepository authoMsgLogRepository;

    public ResponseApiJson<List<PosAuthoMsgLog>> getAllMessage(PosAuthoMsgLogSearchParams posAuthoMsgLogSearchParams) {
        String idRequest = "GAM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Date dateFrom = parseDate(posAuthoMsgLogSearchParams.getDateFromStr(), "00:00:00");
            Date dateTo = parseDate(posAuthoMsgLogSearchParams.getDateToStr(), "23:59:59");
            List<PosAuthoMsgLog> mtiDefs = authoMsgLogRepository.findByAuthoMsgLogIdAutDateTimeBetweenAndAuthoMsgLogIdAutProtocolAndAuthoMsgLogIdInstCode(
                    dateFrom, dateTo, posAuthoMsgLogSearchParams.getProtocol(), posAuthoMsgLogSearchParams.getInstCode());

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





