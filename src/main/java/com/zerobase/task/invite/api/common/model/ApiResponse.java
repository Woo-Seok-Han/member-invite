package com.zerobase.task.invite.api.common.model;

import com.zerobase.task.invite.api.common.model.constant.SuccessCode;
import com.zerobase.task.invite.global.error.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T>{

    // 기본 success, error code
    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "-1";

    private String code;
    private String message;
    private T data;

    public static <T> ResponseEntity<ApiResponse<?>> createSuccess(T data){
        return ResponseEntity.ok()
                .body(
                        ApiResponse.builder()
                                .code(SuccessCode.SUCCESS.getCode())
                                .message(SuccessCode.SUCCESS.getMessage())
                                .data(data)
                                .build());
    }

    public static ResponseEntity<ApiResponse<?>> createError(BusinessException e){
        return ResponseEntity.ok()
                .body(
                        ApiResponse.builder()
                                .code(e.getErrorCode().getCode())
                                .message(e.getErrorCode().getMessage())
                                .build());
    }

    public static ResponseEntity<ApiResponse<?>> createError(String errorMessage){
        return ResponseEntity.ok()
                .body(
                        ApiResponse.builder()
                                .code(ERROR_CODE)
                                .message(errorMessage)
                                .build());
    }

    /**
     * 이외의 예외
     * @param e
     * @return
     */
    public static ResponseEntity<ApiResponse<?>> createError(Exception e){
        return ResponseEntity.ok()
                .body(
                        ApiResponse.builder()
                                .code(ERROR_CODE)
                                .message(e.getMessage())
                                .build());
    }
}
