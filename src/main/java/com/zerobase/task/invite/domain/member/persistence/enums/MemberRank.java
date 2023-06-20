package com.zerobase.task.invite.domain.member.persistence.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRank {
    MANAGER("100", "관리자"),
    EMPLOYEE("101", "사원");

    private final String code;
    private final String value;
}
