package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryFactory;

public class UsersServiceImplementation implements UsersService {
	
	private UsersDAO dao=LibraryFactory.getUsersDAO();
	
	@Override
	public boolean request(int userId, int bookId) {
		return dao.request(userId, bookId);
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int userId) {
		return dao.borrowedBook(userId);
	}
	
	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		return dao.returnBook(bookId, userId, status);
	}

}
