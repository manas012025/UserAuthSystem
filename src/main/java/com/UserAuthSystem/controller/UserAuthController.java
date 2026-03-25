package com.UserAuthSystem.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserAuthSystem.dto.ResponseDto;
import com.UserAuthSystem.dto.UserDto;
import com.UserAuthSystem.entity.User;
import com.UserAuthSystem.repository.UserRepo;
import com.UserAuthSystem.security.JwtUtil;
import com.UserAuthSystem.service.UserAuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserAuthController {
	
    private final AuthenticationManager authManager;

    private final JwtUtil jwtUtil;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    
    private final UserAuthService service;

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		ResponseDto response = service.signUp(userDto);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<?> fetch(){
		ResponseDto response=service.fetch();
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {

		authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    user.getUsername(),
	                    user.getPassword()
	            )
	    );

	    User dbUser = userRepo.findByUsername(user.getUsername());

	    // generate new sessionId
	    String sessionId = UUID.randomUUID().toString();
	    dbUser.setSessionId(sessionId);
	    userRepo.save(dbUser);

	    String token = jwtUtil.generateToken(user.getUsername(), sessionId);

	    return ResponseEntity.ok(Map.of("token", token));
    }
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody UserDto userDto) {

	    String username = userDto.getUsername();

	    User user = userRepo.findByUsername(username);

	    //invalidate session
	    user.setSessionId(UUID.randomUUID().toString());
	    userRepo.save(user);

	    return ResponseEntity.ok("Logged out successfully");
	}
}
