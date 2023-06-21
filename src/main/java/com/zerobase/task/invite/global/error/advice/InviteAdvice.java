package com.zerobase.task.invite.global.error.advice;

import com.zerobase.task.invite.api.common.model.ApiResponse;
import com.zerobase.task.invite.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class InviteAdvice {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        String errorMessage = e.getBindingResult()
//                .getAllErrors()
//                .get(0)
//                .getDefaultMessage();
//
//        printExceptionMessage(errorMessage);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.of)
//        return new ResponseEntity<>(new BaseResult.Normal(INVALID_PARAMETER), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity handleBusinessException(BusinessException e){
        log.error("BusinessException : ", e);
        //ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode());
        //return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(errorResponse);
        return ApiResponse.createError(e);

    }

    /**
     * 나머지 예외 발생
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception e) {
        log.error("Business Exception 이외의 예외 : ", e);
        //ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        return ApiResponse.createError(e);
    }


}
