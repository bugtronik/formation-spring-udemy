package com.udemy.demo.book;

import javax.validation.constraints.NotBlank;

public class Book {

	@NotBlank
	private String title;
	private Category category;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
