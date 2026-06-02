package org.example._kimicommunitybe.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequestDTO {
    @NotBlank(message = "닉네임을 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9가-힣]+$", message = "공백은 포함할 수 없습니다.")
    @Size(min = 1, max = 10,message = "닉네임은 최대 10자까지 가능합니다")
    private String nickname;

    @NotBlank(message = "프로필 사진을 추가해주세요")
    private String profile_image;
}
