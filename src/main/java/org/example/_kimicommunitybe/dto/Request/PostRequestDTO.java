package org.example._kimicommunitybe.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class PostRequestDTO {
    @NotBlank(message = "제목을 입력해주세요")
    @Size(min = 1, max = 26,message = "제목은 최대 26자까지 가능합니다")
    private String title;

    @NotBlank(message = "본문 내용을 작성해주세요")
    private String content;



    private String content_image;
}
