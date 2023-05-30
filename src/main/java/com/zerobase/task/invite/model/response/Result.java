package com.zerobase.task.invite.model.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 응답 메시지 추상화 개발 예정
 */
@Getter
public class Result<T> {

    private int code;
    private String message;
    private T data;

    @Builder
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
