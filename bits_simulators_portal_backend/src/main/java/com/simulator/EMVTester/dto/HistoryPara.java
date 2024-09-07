package com.simulator.EMVTester.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "history_para")
public class HistoryPara {
    @Id
    private String userId;

    private Boolean _Declined;
    private Boolean _Able_To_GoOnline ;
    private Boolean _IssAccepted ;
    private Boolean _SendAutheticationData ;
    private Boolean _Refuse ;
    private Boolean _GoOnline ;
    private  String  _Cryptogram ;
    private Boolean _VelocityCheck ;
    private Boolean _SdaSupported ;
    private Boolean _DdaSupported ;
    private Boolean _CdaSupported ;
    private Boolean _CVerifSupported ;
    private Boolean _TRMSupported ;
    private Boolean _IAuthSupported ;
    private Boolean _isPSE ;



}

