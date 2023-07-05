package com.zerobase.task.invite.api.common.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // 초대 (Invite)
    UNAUTHORIZED_INVITER_TYPE(HttpStatus.BAD_REQUEST, "I-001", "그룹의 매니저만 초대 할 수 있습니다"),
    ALREADY_VERIFIED(HttpStatus.BAD_REQUEST, "I-002", "이미 수락 되었습니다."),
    EXPIRED_INVITE(HttpStatus.BAD_REQUEST, "I-003", "인증이 만료된 초대 입니다."),
    INVALID_INVITE(HttpStatus.BAD_REQUEST, "I-004", "유효하지 않은 초대 요청 입니다."),

    // 회원 (Member)
    ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M-001", "이미 가입된 회원입니다."),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "M-002", "해당 회원은 존재하지 않습니다."),
    NOT_VALID_EMAIL(HttpStatus.BAD_REQUEST, "M-003", "존재하지 않는 이메일 주소 입니다.")

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
