package com.zerobase.task.invite.persistence.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InviteStatus implements CodeValue {
    VALID("100", "승인 가능"),
    EXPIRED("101", "만료");

    private final String code;
    private final String value;
}
