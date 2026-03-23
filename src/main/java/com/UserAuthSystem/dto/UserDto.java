package com.UserAuthSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
	private Long id;
	@NotBlank(message = "username Mandatory")
	private String username;
	private String email;
	private String mobile;
	@NotBlank(message = "username Mandatory")
	private String password;
	@NotBlank(message = "username Mandatory")
	private String role;
}
