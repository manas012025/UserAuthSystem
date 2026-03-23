package com.UserAuthSystem.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.UserAuthSystem.dto.ResponseDto;
import com.UserAuthSystem.dto.UserDto;
import com.UserAuthSystem.entity.User;
import com.UserAuthSystem.repository.UserRepo;
import com.UserAuthSystem.service.UserAuthService;
import com.UserAuthSystem.util.UserUtil;

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
		if(user.getId()!=null) {
			User exist=userRepo.findById(user.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
			userUtil.validateUser(user,exist);
			savedUser=userRepo.save(exist);
			return new ResponseDto(HttpStatus.OK,HttpStatus.OK.value(),savedUser,"User Data Updated");
		}else {
			savedUser=userRepo.save(user);
			return new ResponseDto(HttpStatus.CREATED,HttpStatus.CREATED.value(),savedUser,"User Data Created");
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

}
