package com.sandi.commonsvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@ToString
public class ApiErrorDto {
    private HttpStatus status;
    private String messageKey;
    private String message;
    private String cause;
    private Instant dateTime;
}