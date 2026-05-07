package com.practice.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.practice.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long jwtExpiration;

	public String generateToken(User user) {
 
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("role", user.getRole().name())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(String token) {

		return extractAllClaims(token).getSubject();
	}
 
	public String extractRole(String token) {

		return extractAllClaims(token)
				.get("role", String.class);
	}

	public boolean validateToken(String token, String email) {

		String username = extractUsername(token);

		return username.equals(email)
				&& !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {

		return extractExpiration(token)
				.before(new Date());
	}

	private Date extractExpiration(String token) {

		return extractAllClaims(token)
				.getExpiration();
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSigningKey() {

		byte[] keyBytes =
				Decoders.BASE64.decode(secretKey);

		return Keys.hmacShaKeyFor(keyBytes);
	}
}