package com.UserAuthSystem.service;

import org.springframework.stereotype.Service;

import com.UserAuthSystem.dto.ResponseDto;
import com.UserAuthSystem.dto.UserDto;

import jakarta.validation.Valid;

@Service
public interface UserAuthService {

	ResponseDto signUp(@Valid UserDto userDto);

	ResponseDto fetch();

}
