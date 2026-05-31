package org.example._kimicommunitybe.service;
import org.example._kimicommunitybe.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example._kimicommunitybe.dto.UserJoinDTO;
import org.example._kimicommunitybe.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //의존성 주입.
    @Autowired
    UserRepository userRepository;


    public UserJoinDTO createUser( UserJoinDTO user) {
        String email = user.getEmail();
        String nickname = user.getNickname();
        //1,이메일 중복 체크 확인, 닉네임 중복 체크 확인
        if(checkEmail(email)){
            throw new DuplicateResourceException("중복된 이메일입니다.");
        }
        // 2. 닉네임 중복 검사: 중복이면 즉시 예외 던지고 종료
        if(checkNickname(nickname)){
          throw new DuplicateResourceException("중복된 닉네임입니다.");
        }
        //2.위에 조건 만족하면, user 생성.
            return userRepository.createUser(user);

    }
    //controller get user 전체 읽기 요청이 들어오면 repository에 getAll 실행.
    public List<UserJoinDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }

    //이메일 중복 체크
    public Boolean checkEmail(String email){
        return userRepository.checkEmail(email);
    }
    //닉네임 중복 체크
    public Boolean checkNickname(String nickname){
        return userRepository.checkNickname(nickname);
    }

}