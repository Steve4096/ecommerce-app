package com.example.demo.Exceptions;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponseClass {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
