package com.zerobase.task.invite.advice;

import com.zerobase.task.invite.exception.AuthorityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class InviteAdvice {
    @ExceptionHandler({IllegalArgumentException.class, AuthorityException.class})
    public ResponseEntity illegalArgumentHandler(Exception e){
        return ResponseEntity.badRequest().body("잘못된 입력값 입니다. " + e.getMessage());
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String noSuchElementHandler(Exception e){
        return "조회 결과가 존재 하지 않습니다.";
//        .body("조회 결과가 존재 하지 않습니다. " + e.getMessage());
    }
}
