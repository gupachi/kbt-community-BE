package org.example._kimicommunitybe.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example._kimicommunitybe.dto.Request.UserLoginRequestDTO;
import org.example._kimicommunitybe.jwt.JwtProvider;
import org.example._kimicommunitybe.service.LoginService;
import org.example._kimicommunitybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RestController
@RequestMapping("")
public class CommonController {
    @Autowired
    LoginService loginService;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/auth/check")
    public ResponseEntity<String> checkSession() {
        // 실제로는 여기서 SecurityContext나 세션을 확인해야 합니다.
        // 지금은 테스트를 위해 무조건 200 OK를 반환합니다.
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/refresh")
    @ResponseBody
    public Map<String, String> refresh(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                       HttpServletResponse response) {

        if (refreshToken == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Map.of("error", "Refresh token missing");
        }

        try {
            var tokenRes = loginService.refreshTokens(refreshToken, response);

            if (tokenRes == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return Map.of("error", "Refresh token invalid or expired");
            }

            return Map.of(
                    "accessToken", tokenRes.accessToken(),
                    "refreshToken", tokenRes.refreshToken()
            );
        } catch (ResponseStatusException exception) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Map.of("error", "Refresh token invalid or expired");
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody @Valid UserLoginRequestDTO login, HttpServletResponse response) {
        //1.아이디와 비밀번호 검증
        loginService.loginUser(login, response);
        //2.검증 통과하면 JWT 토큰 생성. - 아니면 jwt 있으면,

        return null;
    }

}