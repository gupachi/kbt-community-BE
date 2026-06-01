package org.example._kimicommunitybe.repository;

import org.example._kimicommunitybe.dto.UserLoginReqDTO;
import org.example._kimicommunitybe.dto.UserSignReqDTO;
import org.example._kimicommunitybe.entity.UserEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
//CRUD 로직 처리, DB 접근 처리
public class UserRepository {
    //메모리에 user 정보를 저장할 ArrayList 를 정의한다.
    static public ArrayList<UserEntity> users;

    //이 클래스에 사용되는 모든 users 이름의 ArrayList 는 계속 하나의 ArrayList 를 참조한다.
    static {
        //ArrayList 생성(이후 db에 저장)
        users = new ArrayList<>();
        users.add(new UserEntity("kim","1234","tester1","https://image.kr/img.jpg"));

    }
    //로그인 시 해당되는 user 정보를 db(현재는 arraylist) 로 부터 가져온다.
    //값 비교 형태는 Object.equals(A,B) 형태로 작성해야 된다.
    public Optional<UserEntity> getUser(UserLoginReqDTO loginDTO){
        return users.stream()
                .filter(user -> Objects.equals(user.getEmail(),loginDTO.getEmail())
                        && Objects.equals(user.getPassword(),loginDTO.getPassword())).findAny();
    }

    //회원가입 : user 추가.
//    public String createUser(UserSignReqDTO user){
//
//      userEntity.setUser_id(10L);
//      userEntity.setEmail(user.getEmail());
//      userEntity.setPassword(user.getPassword());
//      userEntity.setProfileImage(user.getProfile_image());
//
//      users.add(user);
//      return user;
//    }

    //회원 정보 수정 : (nickname,profile_image) 수정.
    public void updateUser(Long id, String nickname,String profile_image){
        users.stream()
                .filter(user-> user.getId().equals(id))
                .findAny()
                .ifPresent(user-> {
                    if(nickname != null) user.setNickname(nickname);
                    if(profile_image != null)user.setProfileImage(profile_image);
                        }
                        );
    }
    //비밀번호 변경.
    public void updatePassword(Long id,String password){
        users.stream()
                .filter(user-> user.getId().equals(id))
                .findAny()
                .ifPresent(user-> {
                            if(password != null) user.setPassword(password);
                        }
                );
    }

    //중복된 이메일 체크.
    public boolean checkEmail(String email){
        return users.stream()
                .anyMatch(user -> Objects.equals(user.getEmail(),email));

    }

    //중복된 닉네임 체크.
    public boolean checkNickname(String nickname){
        return users.stream()
                .anyMatch(user -> Objects.equals(user.getNickname(),nickname));
    }


    //전체 user 정보 조회(Test용)
    public List<UserEntity> getAllUsers(){
        return users;
    }

}