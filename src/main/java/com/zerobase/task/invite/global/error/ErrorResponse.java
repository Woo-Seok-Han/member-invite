package com.zerobase.task.invite.global.error;

import com.zerobase.task.invite.global.error.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .errorCode(errorCode.getErrorCode())
                .errorMessage(errorCode.getErrorMessage())
                .build();
    }


}
