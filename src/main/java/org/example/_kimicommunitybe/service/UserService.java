package org.example._kimicommunitybe.service;
import jakarta.transaction.Transactional;
import org.example._kimicommunitybe.dto.Request.UserPasswordRequestDTO;
import org.example._kimicommunitybe.dto.Response.UserSignResponseDTO;
import org.example._kimicommunitybe.dto.Request.UserUpdateRequestDTO;
import org.example._kimicommunitybe.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example._kimicommunitybe.dto.Request.UserSignRequestDTO;
import org.example._kimicommunitybe.repository.UserRepository;

@Service
//User와 관련된 비즈니스 로직만 처리한다.
public class UserService {
   @Autowired
   UserRepository userRepository;
//   private final JwtTokenService jwtTokenService;
//
//    public  UserService(JwtTokenService jwtTokenService){
//        this.jwtTokenService=jwtTokenService;
//    }
   //로그인.

   //회원가입.
   public UserSignResponseDTO createUser(UserSignRequestDTO user){
       //1.이메일 db 중복 확인.
       if(userRepository.existsByEmail(user.getEmail())){
           throw new IllegalArgumentException("이미 가입된 이메일입니다.");
       }
       //2.닉네임 db 중복 확인.
       if(userRepository.existsByNickname(user.getNickname())){
           throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
       }
       //3.입력된 DTO 정보+ ( activate 설정)
       UserEntity userEntity = new UserEntity();
       userEntity.setEmail(user.getEmail());
       userEntity.setPassword(user.getPassword());
       userEntity.setNickname(user.getNickname());
       userEntity.setProfileImage(user.getProfile_image());
       userEntity.setActivate('1'); // char 타입으로 '1' 저장


       UserEntity savedEntity= userRepository.save(userEntity);
       return new UserSignResponseDTO(
               savedEntity.getNickname(),
               savedEntity.getProfileImage()
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
           if (profileImage != null) u.setProfileImage(profileImage);
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


