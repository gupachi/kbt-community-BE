package org.example._kimicommunitybe.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter

public class UserPasswordRequestDTO {
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",message = "대문자,소문자,숫자,특수문자를 각각 최소 1개 포함해야 합니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자이상 20자 이하여야됩니다")
    private String password;
}
