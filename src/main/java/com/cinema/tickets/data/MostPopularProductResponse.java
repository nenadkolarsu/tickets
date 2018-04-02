package com.cinema.tickets.data;

import java.io.Serializable;

public class MostPopularProductResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private Long value;

	public MostPopularProductResponse(String name, Long value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[product=" + name + ", occurrences=" + value + "]";
	}

}