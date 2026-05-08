package com.practice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.model.dto.request.LoginRequestDto;
import com.practice.model.dto.request.RegisterRequestDto;
import com.practice.model.dto.response.AuthResponseDto;
import com.practice.model.entity.Role;
import com.practice.model.entity.User;
import com.practice.repository.UserRepository;
import com.practice.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public AuthResponseDto register(RegisterRequestDto request) {

		if (userRepository.findByEmail(request.getEmail()).isPresent()) {

			throw new RuntimeException("Email already exists");
		}

		User user = new User();

		user.setName(request.getName());

		user.setEmail(request.getEmail());

		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setRole(Role.USER);

		userRepository.save(user);

		String token = jwtService.generateToken(user);

		return new AuthResponseDto(token); 
	}

	public AuthResponseDto login(LoginRequestDto request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));

		boolean isPasswordMatched = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!isPasswordMatched) {

			throw new RuntimeException("Invalid email or password");
		}

		String token = jwtService.generateToken(user);

		return new AuthResponseDto(token);
	}
}