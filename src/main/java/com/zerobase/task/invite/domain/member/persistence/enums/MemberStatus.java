package com.zerobase.task.invite.domain.member.persistence.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {

    TEMPORARY("0", "임시 회원"),
    REGULAR("1", "정규 회원");

    private final String code;
    private final String value;
}
