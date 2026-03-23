package com.UserAuthSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserAuthSystem.dto.ResponseDto;
import com.UserAuthSystem.dto.UserDto;
import com.UserAuthSystem.service.UserAuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAuthController {
	
	private final UserAuthService service;

	
	public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto){
		ResponseDto response=service.signUp(userDto);
		return new ResponseEntity<>(response, response.getStatus());
	}
}
