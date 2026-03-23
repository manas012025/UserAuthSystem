package com.UserAuthSystem.service;

import org.springframework.stereotype.Service;

import com.UserAuthSystem.dto.ResponseDto;
import com.UserAuthSystem.dto.UserDto;

@Service
public interface UserAuthService {

	ResponseDto signUp(UserDto userDto);

}
