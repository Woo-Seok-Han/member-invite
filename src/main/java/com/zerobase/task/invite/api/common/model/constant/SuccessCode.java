package com.zerobase.task.invite.api.common.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    SUCCESS(HttpStatus.OK, "0", "성공 하였습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
