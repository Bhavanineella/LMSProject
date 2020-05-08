package com.capgemini.librarymanagementsystemjdbc.dto;

import java.io.Serializable;

public class BorrowedBooks implements Serializable {
	private int userId;
	private int bookId;
	private String email;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
