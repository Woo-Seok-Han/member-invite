package com.zerobase.task.invite.global.jwt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.zerobase.task.invite.global.error.exception.AuthorityException;
import com.zerobase.task.invite.global.jwt.constant.TokenType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtAuthenticationProviderTest {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @DisplayName("Value 어노테이션을 이용한 프로퍼티 값 주입")
    @Test
    void propertyValue(){

        String secret = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";
        String accessTokenExpirationTime = "3600000000000000";  // 15분: 1000(ms) x 60(s) x 60(m)
        String refreshTokenExpirationTime = "1209600000"; // 2주: 1000(ms) x 60(s) x 60(m) x 24(h) x 14(d)

        assertThat(jwtAuthenticationProvider.getSecret()).isEqualTo(secret);
        assertThat(jwtAuthenticationProvider.getAccessTokenExpiration()).isEqualTo(accessTokenExpirationTime);
        assertThat(jwtAuthenticationProvider.getRefreshTokenExpiration()).isEqualTo(refreshTokenExpirationTime);
    }

    @DisplayName("Access token 발급 및 검증 테스트")
    @Test
    void createAccessTokenAndValidate() {

        // given
        Long memberId = 1L;
        String accessToken = jwtAuthenticationProvider.createAccessToken(memberId);

        System.out.println("accessToken = " + accessToken);

        // then
        assertDoesNotThrow(() -> {
            jwtAuthenticationProvider.validateToken(accessToken);
        });
    }

    @DisplayName("만료된 토큰 검증 테스트")
    @Test
    void expiredTokenCheck() {

        //given
        Instant now = new Date().toInstant();

        String expiredToken = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256,
                jwtAuthenticationProvider.secret.getBytes(StandardCharsets.UTF_8))
            .setSubject(TokenType.ACCESS.name())
            .setIssuedAt(new Date())
            .setExpiration(Date.from(now.plus(-1, ChronoUnit.DAYS)))
            .compact();

        System.out.println("expiredToken = " + expiredToken);

        // then
        assertThrows(AuthorityException.class, () -> {
            jwtAuthenticationProvider.validateToken(expiredToken);
        });
    }
}