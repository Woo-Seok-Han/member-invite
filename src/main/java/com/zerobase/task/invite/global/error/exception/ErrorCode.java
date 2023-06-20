package com.zerobase.task.invite.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // 초대 (Invite)
    UNAUTHORIZED_INVITER_TYPE(HttpStatus.BAD_REQUEST, "I-001", "그룹의 매니저만 초대 할 수 있습니다"),

    // 회원 (Member)
    ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M-001", "이미 가입된 회원입니다."),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "M-002", "해당 회원은 존재하지 않습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

}
