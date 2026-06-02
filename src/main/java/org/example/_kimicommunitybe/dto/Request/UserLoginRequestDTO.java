package org.example._kimicommunitybe.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

//어노테이션도 왜 쓰는지 이유를 작성할 것.
@Getter
//setter 는 외부에서도 수정이 가능함으로, 왠만해서는 사용하면 안 된다.
public class UserLoginRequestDTO {
    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "올바른 이메일 주소 형식을 입력해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",message = "대문자,소문자,숫자,특수문자를 각각 최소 1개 포함해야 합니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자이상 20자 이하여야됩니다")
    private String password;
}
