package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.UserJoinDTO;
import org.example._kimicommunitybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller 는  실질적으로 Url에서 다음과 같이 올바른 형식을 입력하면 해당 service(비즈니스) 로직이 실행되게 한다.
@RestController
@RequestMapping("/users")
public  class UserController {
    @Autowired
    UserService userService;

    //회원가입
    //UserJoinDTO 를 Request body로서 받는다.
    @PostMapping
    public UserJoinDTO createUser(@Validated @RequestBody UserJoinDTO user){
        return userService.createUser(user);
    }


    //데이터 전체 읽기(테스트)
    @GetMapping("/all")
    public List<UserJoinDTO> getAllUsers() {
        return  userService.getAllUsers();
    }
}
