package org.example._kimicommunitybe.service;
import org.example._kimicommunitybe.dto.UserPasswordReqDTO;
import org.example._kimicommunitybe.dto.UserUpdateReqDTO;
import org.example._kimicommunitybe.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example._kimicommunitybe.dto.UserSignReqDTO;
import org.example._kimicommunitybe.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    //의존성 주입.
    @Autowired
    UserRepository userRepository;


    public UserSignReqDTO  createUser( UserSignReqDTO user) {
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
        //2.위에 조건 만족하면, user 생성.(dto->entity) 생각할 것.(수정 필요!!!!!!!)
        //   return userRepository.createUser(user);
        return userRepository.createUser(user);
    }
    //user 정보 수정.
    public String updateUser(Long id,UserUpdateReqDTO user) {
        String nickname = user.getNickname();
        String profile_img = user.getProfile_image();
        //유효하지 않은 user_id 401에러 처리.

        //닉네임 중복 확인.
        if(checkNickname(nickname)){
            throw new DuplicateResourceException("중복된 닉네임입니다.");
        }
        userRepository.updateUser(id, nickname, profile_img);
        return  "";
    }
    //비밀번호 변경.
    public String  updatePassword(Long id, UserPasswordReqDTO password) {
        userRepository.updatePassword(id,password.getPassword() );
        return  "";
    }

    //이메일 중복 체크
    public Boolean checkEmail(String email){
        return userRepository.checkEmail(email);
    }
    //닉네임 중복 체크
    public Boolean checkNickname(String nickname){
        return userRepository.checkNickname(nickname);
    }

    //전체 user 받기
    public List<UserEntity> getAllUsers() {
        return userRepository.getAllUsers();
    }
}