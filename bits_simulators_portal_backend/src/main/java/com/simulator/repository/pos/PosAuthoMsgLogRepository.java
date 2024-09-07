package com.simulator.repository.pos;

import com.simulator.entities.pos.PosAuthoMsgLog;
import com.simulator.entities.pos.PosAuthoMsgLogId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PosAuthoMsgLogRepository extends JpaRepository<PosAuthoMsgLog, PosAuthoMsgLogId> {


    List<PosAuthoMsgLog> findByAuthoMsgLogIdAutDateTimeBetweenAndAuthoMsgLogIdAutProtocolAndAuthoMsgLogIdInstCode(
            Date dateFrom, Date dateTo, String protocol, String instCode);
}
