package com.shu.icpc.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JavaMailSender mailSender;

    //properties IOC inject
    @Value("${mail.fromMail.addr}")
    private String host;

    /**
     * to:接收者的地址
     * subject:主题
     * content:文本内容
     */
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(host);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
}