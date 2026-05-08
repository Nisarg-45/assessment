package com.practice.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.model.entity.User;
import com.practice.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}
 
	@Override
	public UserDetails loadUserByUsername(String email)

			throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email)

				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return org.springframework.security.core.userdetails.User

				.builder()

				.username(user.getEmail())

				.password(user.getPassword())

				.roles(user.getRole().name())

				.build();
	}
}