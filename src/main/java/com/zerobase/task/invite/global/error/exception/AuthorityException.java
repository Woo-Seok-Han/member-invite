package com.zerobase.task.invite.global.error.exception;

import com.zerobase.task.invite.api.common.model.constant.ErrorCode;

public class AuthorityException extends BusinessException {

    public AuthorityException(ErrorCode errorCode) {
        super(errorCode);
    }
}
