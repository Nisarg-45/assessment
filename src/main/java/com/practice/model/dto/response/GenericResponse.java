package com.practice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "error", "data" })
public class GenericResponse<T> {
	private String error;
	private T data;

	public GenericResponse(T data, String error) {
	    this.data = data;
	    this.error = error;
	}
	public GenericResponse(T data) {
		this.data = data;
	}

	public GenericResponse(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public T getData() {
		return data;
	}
}