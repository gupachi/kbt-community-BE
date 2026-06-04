package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.Request.UserLoginRequestDTO;
import org.example._kimicommunitybe.service.LoginService;
import org.example._kimicommunitybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("")
public class CommonController {
    @Autowired
    LoginService loginService;
    //로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserLoginRequestDTO login){
      return   loginService.loginUser(login);
    }
}
