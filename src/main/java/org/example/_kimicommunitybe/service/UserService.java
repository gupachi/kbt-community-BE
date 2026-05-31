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


    public UserJoinDTO createUser( UserJoinDTO user){
        //1,이메일 중복 체크 확인

        //2.닉네임 중복 체크 확인
        return userRepository.createUser(user);
    }
    //controller get user 전체 읽기 요청이 들어오면 repository에 getAll 실행.
    public List<UserJoinDTO> getAllUsers() {

        return userRepository.getAllUsers();
    }

}