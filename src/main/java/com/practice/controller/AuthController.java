package com.practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.dto.request.LoginRequestDto;
import com.practice.model.dto.request.RegisterRequestDto;
import com.practice.model.dto.response.AuthResponseDto;
import com.practice.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {

		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
		return ResponseEntity.ok(authService.register(request));
	}

	@PostMapping("/login") 
	public ResponseEntity<AuthResponseDto> login(
 
			@RequestBody LoginRequestDto request) {

		return ResponseEntity.ok(authService.login(request));
	} 
 
} 
