package com.practice.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.repository.UserRepository;

//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.practice.repository.UserRepository;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailService(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}  
//
//	@Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		com.practice.model.entity.User user  = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return User.builder()
//                .username(user.getEmail())
//                .password(user.getPassword())
//                .authorities(user.getRole().name())
//                .build();
//    }
//}  
                                
@Service
public class CustomUserDetailService implements UserDetailsService{
	
	private final UserRepository userRepository;
		public CustomUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

     
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.practice.model.entity.User user = userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("User not Found"));
		  
		return User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.authorities(user.getRole().name())
				.build();
	}

}