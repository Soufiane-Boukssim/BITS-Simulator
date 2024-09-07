package com.simulator.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_ACTIVITY_TRACKING")
@Entity
public class UserActivityTracking implements java.io.Serializable {

    @Id
    @Column(name="id" , length=10 , nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idTrack;

    // Fields
    @Column(name="USER_CODE" , length=15 , nullable = false)
    private String userCode;

    @Column(name="OPERATION" , length=10 )
    private String operation;

    @Column(name="OPERATION_LINK" , length=1024 )
    private String operationLink;

    @Column(name="USER_IP", length=32 )
    private String userIp;

    @Column(name="STATUS", length=32 )
    private String status;

    @Column(name="RESP_CODE", length=4 )
    private String respCode;

    @Column(name="REQ_ID", length=16 )
    private String reqId;

    @Column(name="SOURCE_REQ_ID", length=16 )
    private String srcReqId;

    @Column(name="TS_REQUEST", length=100 )
    private String tsRequest;

    @Column(name="TS_RESPONSE", length=100 )
    private String tsResponse;

    @Column(name="WALLET_NUMBER", length=10 )
    private String walletNumber;

    @Column(name="REQUEST_BODY", length=1000 )
    private String requestBody;

    @Column(name="RESPONSE_BODY", length=1000 )
    private String responseBody;

    @Column(name="USER_AGENT", length=200 )
    private String userAgent;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }


    public String getTsRequest() {
        return tsRequest;
    }

    public void setTsRequest(String tsRequest) {
        this.tsRequest = tsRequest;
    }

    public String getTsResponse() {
        return tsResponse;
    }

    public void setTsResponse(String tsResponse) {
        this.tsResponse = tsResponse;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String debitAuthCode() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public int getIdTrack() {
        return idTrack;
    }

    public void setIdTrack(int idTrack) {
        this.idTrack = idTrack;
    }

    public String getReqId() {
        return reqId;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public String getOperationLink() {
        return operationLink;
    }

    public void setOperationLink(String operationLink) {
        this.operationLink = operationLink;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSrcReqId() {
        return srcReqId;
    }

    public void setSrcReqId(String srcReqId) {
        this.srcReqId = srcReqId;
    }


}
