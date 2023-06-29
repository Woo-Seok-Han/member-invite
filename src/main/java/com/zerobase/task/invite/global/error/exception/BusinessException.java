package com.zerobase.task.invite.global.error.exception;

import com.zerobase.task.invite.api.common.model.constant.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getCode() + " : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
