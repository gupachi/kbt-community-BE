package org.example._kimicommunitybe.controller;

import org.example._kimicommunitybe.dto.Request.PostRequestDTO;
import org.example._kimicommunitybe.dto.Request.UserSignRequestDTO;
import org.example._kimicommunitybe.dto.Response.UserSignResponseDTO;
import org.example._kimicommunitybe.dto.Request.UserPasswordRequestDTO;
import org.example._kimicommunitybe.dto.Request.UserUpdateRequestDTO;
import org.example._kimicommunitybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//controller 는  실질적으로 Url에서 다음과 같이 올바른 형식을 입력하면 해당 service(비즈니스) 로직이 실행되게 한다.
@RestController
@RequestMapping("/users")
public  class UserController {
    @Autowired
    UserService userService;
    //회원가입
    //(수정!!) user_id 받는 방법 고민하고 받을 것
    @PostMapping
    public ResponseEntity<UserSignResponseDTO>  createUser(@RequestBody UserSignRequestDTO user) {
        UserSignResponseDTO responseDTO = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    //유저 정보 수정.
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Long userId,@RequestBody UserUpdateRequestDTO user){
        return userService.updateUser(userId,user);
    }
    //비밀번호 변경.
    @PatchMapping("/{userId}/password")
    public String  updateUser(@PathVariable("userId") Long userId, @RequestBody UserPasswordRequestDTO password){
        return userService.updatePassword(userId,password);
    }

}
