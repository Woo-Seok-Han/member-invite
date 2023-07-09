package com.zerobase.task.invite.infra.mail;

import lombok.Data;

@Data
public class MailMessageInfo {
    private final String to;
    private final String subject;
    private final String content;
}
