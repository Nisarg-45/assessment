
package com.practice.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.practice.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.practice.model.entity.User;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Service
//public class JwtService {
//
//	@Value("${jwt.secret}")
//	private String secret;
//	@Value("${jwt.expiration}")
//	private long expiration;
//
//	public String generateToken(User user) {
//
//		return Jwts.builder().setSubject(user.getEmail()) // store email
//				.claim("role", user.getRole().name()) // store role
//				.setIssuedAt(new Date()) // token created time
//				.setExpiration(new Date(System.currentTimeMillis() + expiration)) // expiry time
//				.signWith(SignatureAlgorithm.HS256, secret) // sign token
//				.compact();
//	}
//
//	// Extract email from token
//	public String extractUsername(String token) {
//		return getClaims(token).getSubject();
//	}
//
//	// Validate token
//	public boolean validateToken(String token, String email) {
//
//		String extractedEmail = extractUsername(token);
//
//		return extractedEmail.equals(email) && !isTokenExpired(token);
//	}
//
//	// Check token expired or not
//	private boolean isTokenExpired(String token) {
//		return getClaims(token).getExpiration().before(new Date());
//	}
//
//	// Extract all claims from token
//	private Claims getClaims(String token) {
//
//		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//	}
//}
@Service
public class JwtService{
	 
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private long expiration;
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setSubject(user.getEmail())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.claim("role", user.getRole().name())
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token) 
				.getBody();
	}
	
	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	} 
	
	public boolean validateToken(String token, String email) {
		String extractedEmail = getClaims(token).getSubject();
		
		return extractedEmail.equals(email) && !isTokenExpired(token);
	}
}

