package com.zerobase.task.invite.global.error.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getErrorCode() + " : " + errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
