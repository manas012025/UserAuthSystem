package com.UserAuthSystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncUtil {
	
	@Autowired
    JavaMailSender mailSender;
	
	@Async("CommonExecutor")
	public void sendOtp(String toEmail, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println("Mail Sending started");
        message.setTo(toEmail);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);
        System.out.println("Mail Sended");
    }
}
