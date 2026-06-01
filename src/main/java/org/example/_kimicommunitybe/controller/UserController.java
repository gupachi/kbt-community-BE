package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.UserPasswordReqDTO;
import org.example._kimicommunitybe.dto.UserSignReqDTO;
import org.example._kimicommunitybe.dto.UserUpdateReqDTO;
import org.example._kimicommunitybe.entity.UserEntity;
import org.example._kimicommunitybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//controller 는  실질적으로 Url에서 다음과 같이 올바른 형식을 입력하면 해당 service(비즈니스) 로직이 실행되게 한다.
@RestController
@RequestMapping("/users")
public  class UserController {
    @Autowired
    UserService userService;

    //회원가입
    //UserRequestDTO 를 Request body로서 받는다.
    @PostMapping
    public UserSignReqDTO createUser(@RequestBody UserSignReqDTO user) {
        return userService.createUser(user);
    }

    //유저 정보 수정(닉네임,프로필 이미지)
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Long userId,@RequestBody UserUpdateReqDTO user){
        return userService.updateUser(userId,user);
    }
    @PatchMapping("/{userId}/password")
    public String  updateUser(@PathVariable("userId") Long userId, @RequestBody UserPasswordReqDTO password){
        return userService.updatePassword(userId,password);
    }

    //데이터 전체 읽기(테스트)
    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return  userService.getAllUsers();
    }

}
