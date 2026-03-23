package com.UserAuthSystem.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.UserAuthSystem.dto.UserDto;
import com.UserAuthSystem.entity.User;

@Service
public class UserUtil {

	public void dtoToEntity(UserDto userDto, User user) {
		if(userDto.getId()!=null)
			user.setId(userDto.getId());
		
		if(userDto.getUsername()!=null&&!Strings.isBlank(userDto.getUsername())) {
			user.setUsername(userDto.getUsername());
		}
		if(userDto.getUsername()!=null&&!Strings.isBlank(userDto.getUsername())) {
			user.setEmail(userDto.getEmail());
		}
		if(userDto.getUsername()!=null&&!Strings.isBlank(userDto.getUsername())) {
			user.setMobile(userDto.getMobile());
		}
		if(userDto.getUsername()!=null&&!Strings.isBlank(userDto.getUsername())) {
			user.setPassword(userDto.getPassword());
		}
		if(userDto.getUsername()!=null&&!Strings.isBlank(userDto.getUsername())) {
			user.setRole(userDto.getRole());
		}
	}

	public void validateUser(User newUser, User exist) {
		if(newUser.getUsername()!=null&&!Strings.isBlank(newUser.getUsername())) {
			exist.setUsername(newUser.getUsername());
		}
		if(newUser.getUsername()!=null&&!Strings.isBlank(newUser.getUsername())) {
			exist.setEmail(newUser.getEmail());
		}
		if(newUser.getUsername()!=null&&!Strings.isBlank(newUser.getUsername())) {
			exist.setMobile(newUser.getMobile());
		}
		if(newUser.getUsername()!=null&&!Strings.isBlank(newUser.getUsername())) {
			exist.setPassword(newUser.getPassword());
		}
		if(newUser.getUsername()!=null&&!Strings.isBlank(newUser.getUsername())) {
			exist.setRole(newUser.getRole());
		}
	}
	
}
