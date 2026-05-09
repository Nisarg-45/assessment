package com.practice.security;

//package com.practice.security;

//
//import java.io.IOException;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	private final JwtService jwtService;
//	private final CustomUserDetailsService userDetailsService;
//
//	public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
//		this.jwtService = jwtService;
//		this.userDetailsService = userDetailsService;
//	}
//  
//	@Override
//	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws ServletException, IOException {
//
//		String header = request.getHeader("Authorization");
//
//		if (header != null && header.startsWith("Bearer ")) {
//			String token = header.substring(7);
//			String email = jwtService.extractUsername(token);
//
//			// if valid user(email) is there and user is not authenticated yet then extract user from DB
//			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//				UserDetails user = userDetailsService.loadUserByUsername(email);
//
//				if (jwtService.validateToken(token, user.getUsername())) {
//					SecurityContextHolder.getContext().setAuthentication(
//							new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
//				}
//			} 
//		}
//
//		chain.doFilter(request, response);
//	}
//
//}

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final CustomUserDetailService userDetailService;

	public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailService userDetailService) {
		super();
		this.jwtService = jwtService;
		this.userDetailService = userDetailService;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			String email = jwtService.extractUsername(token);

			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails user = userDetailService.loadUserByUsername(email);

				if (jwtService.validateToken(token, user.getUsername())) {
					SecurityContextHolder.getContext().setAuthentication(
							new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
				}
			}
		}
		filterChain.doFilter(request, response);
	}

}

//
//Every request comes here first
//↓
//Reads Authorization header
//↓
//Checks Bearer token exists or not
//↓
//Extracts JWT token
//↓
//Extracts email from token
//↓
//Checks user not already authenticated
//↓
//Loads user details from database
//↓
//Validates token
//↓
//Creates Authentication object
//↓
//Stores authenticated user in SecurityContextHolder
//↓
//Allows request to continue
