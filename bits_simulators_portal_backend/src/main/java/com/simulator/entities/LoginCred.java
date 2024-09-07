package com.simulator.entities;

import lombok.Data;

@Data
public class LoginCred {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    
    
  
    private String password;
    private String email;

    private String usercode;
   

    public LoginCred(){}

    public LoginCred(String usercode,/*String email,*/ String password){
        /*this.email=email;*/
        this.usercode=usercode;
        this.password=password;
        }
}
        

