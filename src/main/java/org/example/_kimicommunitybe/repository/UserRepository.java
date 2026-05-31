package org.example._kimicommunitybe.repository;

import lombok.Getter;
import lombok.Setter;
import org.example._kimicommunitybe.dto.LoginDTO;
import org.example._kimicommunitybe.dto.UserJoinDTO;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
//CRUD 로직 처리, DB 접근 처리
public class UserRepository {
    //JVM 메모리에 user 정보를 저장할 ArrayList 를 정의한다.
    static public ArrayList<UserJoinDTO> users;

    //이 클래스에 사용되는 모든 users 이름의 ArrayList 는 계속 하나의 ArrayList 를 참조한다.
    static {
        //ArrayList 생성
        users = new ArrayList<>();
        users.add(new UserJoinDTO("kim","1234","tester1","https://image.kr/img.jpg"));

    }
    //로그인 시 해당되는 user 정보를 db 로 부터 가져온다.
    //Object.equals(A,B) 형태로 작성해야 된다.
    public Optional<UserJoinDTO> getUser(LoginDTO loginDTO){
        return users.stream()
                .filter(user -> Objects.equals(user.getEmail(),loginDTO.getEmail())
                        && Objects.equals(user.getPassword(),loginDTO.getPassword())).findAny();
    }

    //회원가입 : user 추가.
    public UserJoinDTO createUser(UserJoinDTO user){
        //db에다가 저장하는 기능을 넣어줘야 된다.
        users.add(user);
        return  user;
    }

    //READ 문
    //저장되있는 전체 정보 조회.
    public List<UserJoinDTO> getAllUsers(){
        return users;
    }




}