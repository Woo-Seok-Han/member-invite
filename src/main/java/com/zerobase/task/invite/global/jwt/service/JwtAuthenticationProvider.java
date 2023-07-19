package com.zerobase.task.invite.global.jwt.service;


import com.zerobase.task.invite.api.common.model.constant.ErrorCode;
import com.zerobase.task.invite.domain.member.persistence.entity.Member;
import com.zerobase.task.invite.global.error.exception.AuthorityException;
import com.zerobase.task.invite.global.jwt.constant.Role;
import com.zerobase.task.invite.global.jwt.constant.TokenType;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
public class JwtAuthenticationProvider {

    @Value("${token.secret}")
    protected String secret;

    @Value("${token.access-token-expiration-time}")
    private String accessTokenExpiration;

    @Value("${token.refresh-token-expiration-time}")
    private String refreshTokenExpiration;

    public String createJwtTokenDto(Member member) {

        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExcpireTime = createRefreshTokenExcpireTime();

        String accessToken = "";
        String refreshToken = "";

        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public void validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.error("Expired Token Access", e);
            throw new AuthorityException(ErrorCode.EXPIRED_TOKEN_ACCESS);
        } catch (Exception e) {
            log.error("Invalid Token Access", e);
            throw new AuthorityException(ErrorCode.INVALID_TOKEN_ACCESS);
        }
    }

    public String createAccessToken(Long memberId) {

        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
            .setSubject(TokenType.ACCESS.name())
            .setIssuedAt(new Date())
            .setExpiration(createAccessTokenExpireTime())
            .claim("role", Role.ADMIN)
            .claim("memberId", memberId)
            .setHeaderParam("typ", "JWT")
            .compact();
    }

    public String createRefreshToken(Long memberId) {

        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
            .setSubject(TokenType.REFRESH.name())
            .setIssuedAt(new Date())
            .setExpiration(createRefreshTokenExcpireTime())
            .setHeaderParam("typ", "JWT")
            .compact();
    }

    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpiration));
    }

    public Date createRefreshTokenExcpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpiration));
    }

}
