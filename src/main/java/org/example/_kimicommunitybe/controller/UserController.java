package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.Request.UserLoginRequestDTO;
import org.example._kimicommunitybe.dto.Request.UserSignRequestDTO;
import org.example._kimicommunitybe.dto.Response.UserSignResponseDTO;
import org.example._kimicommunitybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//controller 는  실질적으로 Url에서 다음과 같이 올바른 형식을 입력하면 해당 service(비즈니스) 로직이 실행되게 한다.
@RestController
@RequestMapping("/users")
public  class UserController {
    @Autowired
    UserService userService;
    //로그인

    //회원가입(user_id 어떻게 받을지 고민)
    @PostMapping
    public ResponseEntity<UserSignResponseDTO>  createUser(@RequestBody UserSignRequestDTO user) {
        UserSignResponseDTO responseDTO = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
