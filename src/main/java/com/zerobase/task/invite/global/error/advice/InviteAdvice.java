package com.zerobase.task.invite.global.error.advice;

import com.zerobase.task.invite.global.error.ErrorResponse;
import com.zerobase.task.invite.global.error.exception.AuthorityException;
import com.zerobase.task.invite.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class InviteAdvice {
    @ExceptionHandler({IllegalArgumentException.class, AuthorityException.class})
    public ResponseEntity illegalArgumentHandler(Exception e){
        return ResponseEntity.badRequest().body("잘못된 입력값 입니다. " + e.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e){
        log.error("BusinessException : ", e);
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(errorResponse);
    }
}
