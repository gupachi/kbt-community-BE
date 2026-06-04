package org.example._kimicommunitybe.service;


import org.example._kimicommunitybe.dto.Request.UserLoginRequestDTO;
import org.example._kimicommunitybe.entity.UserEntity;
import org.example._kimicommunitybe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {
    //로그인 비즈니스 로직 작성.
    private final JwtTokenService jwtTokenService;

    public  LoginService(JwtTokenService jwtTokenService){
        this.jwtTokenService=jwtTokenService;
    }
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Map<String, String>> loginUser(UserLoginRequestDTO login){
       //1.db에서 해당되는 객체 가져오기.
       Optional<UserEntity> result = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword());
       System.out.println(login.getEmail());
        System.out.println(login.getPassword());
       if(result.isPresent()){
            //1.정상 확인 되면 토큰 발급함.
            //jwt.claim 이 정보 확인 용도라고 했는데 (user email 정보를 가지고 토큰을 만들어도 될까?)
            //userJoinEntity 로 바꾼 다음에는 user.getUserid() 를 가져온다.
            //이메일이 간편하기는 하지만,string 자체는 헤더의 용량을 너무 늘린다.
            String token = jwtTokenService.createToken(login.getEmail(), "ROLE_USER");

            Map<String, String> response = new HashMap<>();
            response.put("access token",token);

            return ResponseEntity.ok(response);
            //.사용자가 있음이 확인되면 JWT 를 발급한다.
        }
        //db에서 로그인 정보와 일치된 사용자를 찾지 못한 경우.
        else{
            return ResponseEntity.status(401).body(Map.of("error","로그인 정보가 유효하지 않습니다."));
        }

    }
    //회원가입 비즈니
}