package org.example._kimicommunitybe.common.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;
    private T data;
}