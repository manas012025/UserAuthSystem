package com.UserAuthSystem.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.UserAuthSystem.dto.ResponseDto;
import com.UserAuthSystem.dto.UserDto;
import com.UserAuthSystem.entity.User;
import com.UserAuthSystem.repository.UserRepo;
import com.UserAuthSystem.service.UserAuthService;
import com.UserAuthSystem.util.UserUtil;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService{
	
	private final UserRepo userRepo;
	
	private final UserUtil userUtil;
	
	@Override
	public ResponseDto signUp(UserDto userDto) {
		try {
		User user=new User();
		userUtil.dtoToEntity(userDto,user);
		User savedUser=null;
		UserDto response=new UserDto();
		if(user.getId()!=null) {
			User exist=userRepo.findById(user.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
			userUtil.validateUser(user,exist);
			savedUser=userRepo.save(exist);
			userUtil.entityToDto(response,savedUser);
			return new ResponseDto(HttpStatus.OK,HttpStatus.OK.value(),response,"User Data Updated");
		}else {
			if (userRepo.findByUsername(user.getUsername()) != null) {
				return new ResponseDto(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),null,"Username Already Exists");
			}
			savedUser=userRepo.save(user);
			userUtil.entityToDto(response, savedUser);
			return new ResponseDto(HttpStatus.CREATED,HttpStatus.CREATED.value(),response,"User Data Created");
		}
		}catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseDto(HttpStatus.NO_CONTENT,HttpStatus.NO_CONTENT.value(),null,e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value(),null,e.getMessage());
		}
	}

	@Override
	public ResponseDto fetch() {
		List<User> users=userRepo.findAll();
		if(!CollectionUtils.isEmpty(users)) {
			List<UserDto> response=users.stream().map(e->{
				UserDto dto=new UserDto();
				userUtil.entityToDto(dto, e);
				return dto;}).collect(Collectors.toList());
			return new ResponseDto(HttpStatus.OK,HttpStatus.OK.value(),response,"Users Data Fetched");
		}
		return new ResponseDto(HttpStatus.NO_CONTENT,HttpStatus.NO_CONTENT.value(),null,"No User Data Present");
	}

}
