package com.simulator.globalService;

import com.simulator.entities.Mail;

public interface EmailService {
    public void sendCodeByMail(Mail mail);
}
