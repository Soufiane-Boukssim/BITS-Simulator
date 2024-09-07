package com.simulator.repository;

import com.simulator.entities.AuthoMsgLog;
import com.simulator.entities.AuthoMsgLogId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AuthoMsgLogRepository extends JpaRepository<AuthoMsgLog, AuthoMsgLogId> {


    List<AuthoMsgLog> findByAuthoMsgLogIdAutDateTimeBetweenAndAuthoMsgLogIdAutProtocolAndAuthoMsgLogIdInstCode(
            Date dateFrom, Date dateTo, String protocol, String instCode);
}