package com.zerobase.task.invite.global.jwt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class JwtTokenDto {

    private String accessToken;
    private String refreshToken;

    private Date accessTokenExpirationTime;
    private Date refreshTokenExpirationTime;



}
