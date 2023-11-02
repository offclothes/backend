package com.app.oc.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailUtilImpl implements EmailUtil {

    @Autowired
    private JavaMailSender sender;

    @Override
    public Map<String, Object> sendEmail(String toAddress) {
        System.out.println("toAddress = " + toAddress);
        Map<String, Object> result = new HashMap<String, Object>();
        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            StringBuilder body = new StringBuilder();
            body.append("<html> <body><h1>안녕하세요 Off Clothes입니다. </h1>");
            body.append("<div>입점이 완료되었습니다. 로그인 하셔서 의류를 등록해주세요.</div></body></html>");
            helper.setTo(toAddress);
            helper.setSubject("[Off Clothes]입점 심사 결과");
            helper.setText(body.toString(), true);
            result.put("resultCode", 200); //전송 성공
        } catch (MessagingException e) {
            e.printStackTrace();
            result.put("resultCode", 500); //전송 실패
        }
        sender.send(message);
        return result;
    }
}
