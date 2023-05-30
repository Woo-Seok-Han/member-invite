package com.zerobase.task.invite.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 응답 메시지 추상화 개발 예정
 */
@Getter
@AllArgsConstructor
public enum Response {
    SUCCESS(0, "성공하였습니다."),
    FAIL(-1, "실패하였습니다.");

    private int code;
    private String message;
}
