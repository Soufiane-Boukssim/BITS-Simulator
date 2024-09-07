package com.simulator.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "API_USER_MANAGEMENT")
@Entity
public class ApiUserManagement implements java.io.Serializable {

    // Fields
    @Column(name="USER_CODE" , length=15 , nullable = false)
    @Id
    private String userCode;
    @Column(name="USER_NAME" , length=30 ) //, nullable = false)
    private String userName;
    @Column(name="USER_TYPE" , length=1)
    private String userType;
    //length in gcb local  15
    // in cbg lenght =255
    @Column(name="USER_PASSWORD" )
    private String userPassword;
    @Column(name="USER_BANK_CODE" , length=5)
    private String userBankCode;
    @Column(name="USER_BRANCH_CODE" , length=5)
    private String userBranchCode;
    @Column(name="NUMBER_OF_TRIES" ,precision =3 ,scale = 0)
    private Long numberOfTries;
    @Column(name="NUMBER_OF_TRIES_ALLOWED" , precision =3 ,scale = 0)
    private Long numberOfTriesAllowed;

    @JsonIgnore
    @Column(name="IP_ADDRESS_MANG" , length=1)
    private String ipAddressMang;
    @JsonIgnore
    @Column(name="IP_ADDRESS" , length=20)
    private String ipAddress;
    @JsonIgnore
    @Column(name="CONNECTED" , length=1)
    private String connected;
    @Column(name="FIRST_CONNECTION" , length=1)
    private Character firstConnection;
    @Column(name="NBRE_SESSION_ALLOWED" , precision =3 ,scale = 0)
    private Integer nbreSessionAllowed;
    //in CBG db precision =2 ,scale = 0
    //@Column(name="NBRE_SEESION_CONNECTED" ,precision =3 ,scale = 0)
    @Column(name="NBRE_SEESION_CONNECTED" ,precision =2 ,scale = 0)
    private Integer nbreSeesionConnected;
    //in CBG and MAYbe gcb db TOO, precision =2 ,scale = 0
    //@Column(name="LENGTH_PASSWORD" ,precision =3 ,scale = 0)
    @Column(name="LENGTH_PASSWORD" ,precision =2 ,scale = 0)
    private Integer lengthPassword;
    @JsonIgnore
    @Column(name="COMPLEXITY_FLAG" , length = 1)
    private Character complexityFlag;

    @Column(name="EXPIRATION_PASSWORD" , precision =4 ,scale = 0)
    private Long expirationPassword;
    @Column(name="DATE_START_PASS" , length=7)
    private Date dateStartPass;
    @Column(name="DATE_END_PASS" , length=7)
    private Date dateEndPass;
    @Column(name="BLOCK_ACCESS" , length=1)
    private Character blockAccess;

    @JsonIgnore
    @Column(name="LANGUAGE_CODE" , length=3)
    private String languageCode;

    //length in db cbg and gcb 1024
    //@Column(name="LAST_4_PWD" , length=1023)
    @Column(name="LAST_4_PWD" , length=1024)
    private String last4Pwd;



    //this 2 two Couloumns doesn't existe in CBG DB
    //@Column(name="EMAIL" , length=100)
    //private String email;
    //@Column(name="MOBILE_NUMBER" , length=20)
    // private String mobileNumber;

    // additional couloumn in db cbg
    /*
     * @Column(name="FORGOTPWD_REQUEST" , length=1) private Character
     * forgotPwdRequest;
     *
     * @Column(name="SECRET_KEY" , length=100) private String secretKey;
     */

    @Column(name="ALLOWED_SERVICES" , length=100)
    private String allowedServices;


    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserBankCode() {
        return userBankCode;
    }
    public void setUserBankCode(String userBankCode) {
        this.userBankCode = userBankCode;
    }
    public String getUserBranchCode() {
        return userBranchCode;
    }
    public void setUserBranchCode(String userBranchCode) {
        this.userBranchCode = userBranchCode;
    }
    public String getIpAddressMang() {
        return ipAddressMang;
    }
    public void setIpAddressMang(String ipAddressMang) {
        this.ipAddressMang = ipAddressMang;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getConnected() {
        return connected;
    }
    public void setConnected(String connected) {
        this.connected = connected;
    }
    public String getLanguageCode() {
        return languageCode;
    }
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
    public Character getFirstConnection() {
        return firstConnection;
    }
    public void setFirstConnection(Character firstConnection) {
        this.firstConnection = firstConnection;
    }
    public Long getNumberOfTries() {
        return numberOfTries;
    }
    public void setNumberOfTries(Long numberOfTries) {
        this.numberOfTries = numberOfTries;
    }
    public Long getNumberOfTriesAllowed() {
        return numberOfTriesAllowed;
    }
    public void setNumberOfTriesAllowed(Long numberOfTriesAllowed) {
        this.numberOfTriesAllowed = numberOfTriesAllowed;
    }
    public Integer getNbreSessionAllowed() {
        return nbreSessionAllowed;
    }
    public void setNbreSessionAllowed(Integer nbreSessionAllowed) {
        this.nbreSessionAllowed = nbreSessionAllowed;
    }
    public Integer getNbreSeesionConnected() {
        return nbreSeesionConnected;
    }
    public void setNbreSeesionConnected(Integer nbreSeesionConnected) {
        this.nbreSeesionConnected = nbreSeesionConnected;
    }
    public Integer getLengthPassword() {
        return lengthPassword;
    }
    public void setLengthPassword(Integer lengthPassword) {
        this.lengthPassword = lengthPassword;
    }
    public Character getComplexityFlag() {
        return complexityFlag;
    }
    public void setComplexityFlag(Character complexityFlag) {
        this.complexityFlag = complexityFlag;
    }
    public Long getExpirationPassword() {
        return expirationPassword;
    }
    public void setExpirationPassword(Long expirationPassword) {
        this.expirationPassword = expirationPassword;
    }
    public Date getDateStartPass() {
        return dateStartPass;
    }
    public void setDateStartPass(Date dateStartPass) {
        this.dateStartPass = dateStartPass;
    }
    public Date getDateEndPass() {
        return dateEndPass;
    }
    public void setDateEndPass(Date dateEndPass) {
        this.dateEndPass = dateEndPass;
    }



    /*
     * public String getEmail() { return email; } public void setEmail(String email)
     * { this.email = email; } public String getMobileNumber() { return
     * mobileNumber; } public void setMobileNumber(String mobileNumber) {
     * this.mobileNumber = mobileNumber; }
     */



    public Character getBlockAccess() {
        return blockAccess;
    }
    public void setBlockAccess(Character blockAccess) {
        this.blockAccess = blockAccess;
    }
    public String getLast4Pwd() {
        return last4Pwd;
    }
    public void setLast4Pwd(String last4Pwd) {
        this.last4Pwd = last4Pwd;
    }
    public String getAllowedServices() {
        return allowedServices;
    }
    public void setAllowedServices(String allowedServices) {
        this.allowedServices = allowedServices;
    }

}
