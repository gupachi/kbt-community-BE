package org.example._kimicommunitybe.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDTO {
    @NotBlank(message="내용을 입력해주세요")
    private String  text;
}
