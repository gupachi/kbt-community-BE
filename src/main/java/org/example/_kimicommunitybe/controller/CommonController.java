package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.LoginDTO;
import org.example._kimicommunitybe.service.JwtTokenService;
import org.example._kimicommunitybe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("")
//이름이 독립적인 API 들 저장.
public class CommonController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    //접근제한자 [return 타입] 이름(파라미터)
    public ResponseEntity<Map<String, String>>  loginUser(@RequestBody LoginDTO user) {
        return loginService.loginUser(user);

    }
}
