package com.practice.model.dto.request;

public class RegisterRequestDto {
	private String name;

	private String email;

	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPpassword(String ppassword) {
		this.password = ppassword;
	}

	public RegisterRequestDto() {
	}

	public RegisterRequestDto(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
