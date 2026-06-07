package org.example._kimicommunitybe.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example._kimicommunitybe.dto.Request.UserLoginRequestDTO;
import org.example._kimicommunitybe.entity.RefreshToken;
import org.example._kimicommunitybe.entity.User;
import org.example._kimicommunitybe.jwt.JwtProvider;
import org.example._kimicommunitybe.repository.RefreshTokenRepository;
import org.example._kimicommunitybe.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    private static final int ACCESS_TOKEN_EXPIRATION = 15 * 60; // 15분
    private static final int REFRESH_TOKEN_EXPIRATION = 14 * 24 * 3600; // 14일


    @Transactional
    public String loginUser(UserLoginRequestDTO login, HttpServletResponse response) {
        User user = userRepository.findByEmail(login.getEmail()).orElse(null);

        if (user == null || !checkPassword(user,login.getPassword())) {
            return null;
        }

        // 기존 리프레시 토큰 무효화
        refreshTokenRepository.deleteByUserId(user.getUserId());
        // 새로운 토큰 발급 및 저장
        var tokenResponse = generateAndSaveTokens(user);

        // 쿠키 추가
        addTokenCookies(response, tokenResponse);

        return tokenResponse.accessToken();
    }

    @Transactional
    public TokenResponse refreshTokens(String refreshToken, HttpServletResponse response) {
        var parsedRefreshToken = jwtProvider.parse(refreshToken);

        RefreshToken entity = refreshTokenRepository.findByTokenAndRevokedFalse(refreshToken).orElse(null);

        if (entity == null || entity.getExpiresAt().isBefore(Instant.now())) {
            return null;
        }

        Long userId = Long.valueOf(parsedRefreshToken.getBody().getSubject());
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        // refresh token은 유지하고 access token만 새로 발급
        String newAccessToken = jwtProvider.createAccessToken(userId,user.getEmail());

        // access token 쿠키만 갱신
        addTokenCookie(response, "accessToken", newAccessToken, ACCESS_TOKEN_EXPIRATION);

        return new TokenResponse(newAccessToken, refreshToken);
    }

    public void logoutUser(HttpServletResponse response) {
        // 쿠키 즉시 만료
        addTokenCookie(response, "accessToken", null, 0);
        addTokenCookie(response, "refreshToken", null, 0);
    }

    /** Access / Refresh 토큰을 새로 발급하고 DB에 저장 */
    private TokenResponse generateAndSaveTokens(User user) {
        String accessToken = jwtProvider.createAccessToken(user.getId(), user.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        RefreshToken refreshEntity = new RefreshToken();
        refreshEntity.setUser(user);
        refreshEntity.setToken(refreshToken);
        refreshEntity.setExpiresAt(Instant.now().plusSeconds(REFRESH_TOKEN_EXPIRATION));
        refreshEntity.setRevoked(false);
        refreshTokenRepository.save(refreshEntity);

        return new TokenResponse(accessToken, refreshToken);
    }

    /** AccessToken + RefreshToken 쿠키를 한번에 추가 */
    private void addTokenCookies(HttpServletResponse response, TokenResponse tokenResponse) {
        addTokenCookie(response, "accessToken", tokenResponse.accessToken(), ACCESS_TOKEN_EXPIRATION);
        addTokenCookie(response, "refreshToken", tokenResponse.refreshToken(), REFRESH_TOKEN_EXPIRATION);
    }

    /** 공통 쿠키 생성 로직 */
    private void addTokenCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    private boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public record TokenResponse(String accessToken, String refreshToken) { }
}