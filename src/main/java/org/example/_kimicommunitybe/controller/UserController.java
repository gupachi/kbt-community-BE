package org.example._kimicommunitybe.controller;

import jakarta.validation.Valid;
import org.example._kimicommunitybe.common.config.ApiResponse;
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

    //이메일 중복 체크
    @GetMapping("/email/check")
    public ResponseEntity<String> checkEmail(@RequestParam(name = "email") String email) {
        boolean isDuplicated = userService.checkEmailDuplicate(email);

        if (isDuplicated) {
            // 이미 존재하는 이메일이면 409 Conflict (충돌) 에러와 메시지를 보냅니다.
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 이메일입니다.");
        }
        // 사용 가능하면 200 OK를 보냅니다.
        return ResponseEntity.ok("사용 가능한 이메일입니다.");
    }

    // 닉네임 중복 체크
    @GetMapping("/nickname/check")
    public ResponseEntity<String> checkNickname(@RequestParam(name = "nickname") String nickname) {
        boolean isDuplicated = userService.checkNicknameDuplicate(nickname);

        if (isDuplicated) {
            // 이미 존재하는 닉네임이면 409 에러를 보냅니다.
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 닉네임입니다.");
        }
        return ResponseEntity.ok("사용 가능한 닉네임입니다.");
    }
    //회원가입
    @PostMapping
    public ResponseEntity<ApiResponse<UserSignResponseDTO>> createUser(@RequestBody @Valid UserSignRequestDTO user) {
        UserSignResponseDTO responseDTO = userService.createUser(user);

        // code와 data(responseDTO)를 담아서 보냄
        ApiResponse<UserSignResponseDTO> body = new ApiResponse<>("SUCCESS", responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
    //유저 정보 수정.
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Long userId,@RequestBody @Valid UserUpdateRequestDTO user){
        return userService.updateUser(userId,user);
    }
    //비밀번호 변경.
    @PatchMapping("/{userId}/password")
    public String  updateUser(@PathVariable("userId") Long userId, @RequestBody @Valid UserPasswordRequestDTO password){
        return userService.updatePassword(userId,password);
    }

}
