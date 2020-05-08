package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;


public interface UsersService {

	boolean request(int userId, int bookId);
	boolean returnBook(int bookId,int userId,String status);
	List<BorrowedBooks> borrowedBook(int userId);
	
}
