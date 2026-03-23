package com.UserAuthSystem.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
	private HttpStatus status;
	private int statusCode;
	private Object data;
	private String message;
}
