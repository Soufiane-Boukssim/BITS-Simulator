package com.simulator.globalService;

import com.simulator.entities.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{

    private  final JavaMailSender javaMailSender;


    @Override
    public void sendCodeByMail(Mail mail)  {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("marketplace@bits.ma");
        simpleMailMessage.setTo(mail.getTo());
        simpleMailMessage.setSubject("Reset Password Code");
        String textContent = "Your reset code: " + mail.getCode();
        simpleMailMessage.setText(textContent);

        javaMailSender.send(simpleMailMessage);
    }
}
