package com.zerobase.task.invite.persistence.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rank implements CodeValue{
    MANAGER("100", "관리자"),
    EMPLOYEE("101", "사원");

    private final String code;
    private final String value;
}
