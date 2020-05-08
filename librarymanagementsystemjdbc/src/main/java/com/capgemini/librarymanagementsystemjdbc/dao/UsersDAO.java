package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;


public interface UsersDAO {
    
	boolean request(int userId, int bookId);
	boolean returnBook(int bookId,int userId,String status);
	List<BorrowedBooks> borrowedBook(int userId);
	
}
