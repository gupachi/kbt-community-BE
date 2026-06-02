package org.example._kimicommunitybe.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter              // 1. JSON 변환(Getter)을 위해 필수!
@NoArgsConstructor   // 2. 기본 생성자 (JPA나 라이브러리용)
@AllArgsConstructor  // 3. 모든 필드를 인자로 받는 생성자 (이게 있어야 new ... 로 생성 가능!)

public class UserSignResponseDTO {

    private String nickname;

    private String profile_image;


}
