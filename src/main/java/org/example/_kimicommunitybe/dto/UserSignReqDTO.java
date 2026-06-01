package org.example._kimicommunitybe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//user entity 구성 (username, userId) 속성을 가지고 있다.
public class UserSignReqDTO {
    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "올바른 이메일 주소 형식을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",message = "대문자,소문자,숫자,특수문자를 각각 최소 1개 포함해야 합니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자이상 20자 이하여야됩니다")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9가-힣]+$", message = "공백은 포함할 수 없습니다.")
    @Size(min = 1, max = 10,message = "닉네임은 최대 10자까지 가능합니다")
    private String nickname;

    @NotBlank(message = "프로필 사진을 추가해주세요")
    private String profile_image;
}
