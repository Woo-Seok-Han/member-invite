package com.zerobase.task.invite.infra.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {

    private final MailSender mailSender;
    private final JavaMailSender javaMailSender;

    public void sendTextMail(String to, String subject, String content) {
        SimpleMailMessage smm = new SimpleMailMessage();

        smm.setTo(to);
        smm.setSubject(subject);
        smm.setText(content);

        mailSender.send(smm);
    }

    public void sendMail(String to, String subject, String content) {

        MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true,
                    "UTF-8");
                mimeMessageHelper.setTo(to);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(content, true);
            }
        };

        try {
            javaMailSender.send(mimeMessagePreparator);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
