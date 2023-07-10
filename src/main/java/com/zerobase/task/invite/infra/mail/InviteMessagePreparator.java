package com.zerobase.task.invite.infra.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@RequiredArgsConstructor
public class InviteMessagePreparator implements MimeMessagePreparator {

    private final MailMessageInfo messageInfo;

    @Override
    public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setTo(messageInfo.getTo());
        mimeMessageHelper.setSubject(messageInfo.getSubject());
        mimeMessageHelper.setText(messageInfo.getContent(), true);
    }
}
