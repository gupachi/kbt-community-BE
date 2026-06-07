package org.example._kimicommunitybe.service;
import jakarta.transaction.Transactional;
import org.example._kimicommunitybe.dto.Request.UserPasswordRequestDTO;
import org.example._kimicommunitybe.dto.Response.UserSignResponseDTO;
import org.example._kimicommunitybe.dto.Request.UserUpdateRequestDTO;
import org.example._kimicommunitybe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example._kimicommunitybe.dto.Request.UserSignRequestDTO;
import org.example._kimicommunitybe.repository.UserRepository;

@Service
//User와 관련된 비즈니스 로직만 처리한다.
public class UserService {
   @Autowired
   PasswordEncoder passwordEncoder;

   @Autowired
   UserRepository userRepository;

    //이메일 중복 확인 로직.
    public boolean checkEmailDuplicate(String email) {
        // DB에 이메일이 존재하면 true, 없으면 false를 반환합니다.
        return userRepository.existsByEmail(email);
    }

    // 닉네임 중복 확인 로직.
    public boolean checkNicknameDuplicate(String nickname) {
        // DB에 닉네임이 존재하면 true, 없으면 false를 반환합니다.
        return userRepository.existsByNickname(nickname);
    }
   //회원가입.
   public UserSignResponseDTO createUser(UserSignRequestDTO user){
       String encodedPassword = passwordEncoder.encode(user.getPassword());
//       //1.이메일 db 중복 확인.
//       if(userRepository.existsByEmail(user.getEmail())){
//           throw new IllegalArgumentException("이미 가입된 이메일입니다.");
//       }
//       //2.닉네임 db 중복 확인.
//       if(userRepository.existsByNickname(user.getNickname())){
//           throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
//       }
       //3.입력된 DTO 정보+ ( activate 설정)
       User userEntity = new User();
       userEntity.setEmail(user.getEmail());
       userEntity.setPassword(encodedPassword);
       userEntity.setNickname(user.getNickname());
      // userEntity.setProfileImage(user.getProfileImageUrl());
       userEntity.setActivate(true); // char 타입으로 '1' 저장


       User savedEntity= userRepository.save(userEntity);
       return new UserSignResponseDTO(
               savedEntity.getNickname()
       );
   }
   //유저 정보 수정.
   @Transactional
   public String updateUser(Long id, UserUpdateRequestDTO user) {
       String nickname = user.getNickname();
       String profileImage = user.getProfile_image();

       //닉네임 중복 확인.
       if(nickname!=null && userRepository.existsByNickname(nickname)){
           throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
       }
       userRepository.findById(id).ifPresent(u -> {
           if (nickname != null) u.setNickname(nickname);
        //   if (profileImage != null) u.setProfileImage(profileImage);
       });
       return "";
   }
   //비밀번호 변경.
    @Transactional
   public String updatePassword(Long id, UserPasswordRequestDTO user){
       userRepository.findById(id).ifPresent(u -> {
           if (user.getPassword() != null) u.setPassword(String.valueOf(user.getPassword()));
       });
        return "";
    }

}


