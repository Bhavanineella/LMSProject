package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;
import com.capgemini.librarymanagementsystemhibernate.factory.LibraryFactory;

public class UsersServiceImplementation implements UsersService {

	private UserDAO dao = LibraryFactory.getUserDAO();
	
	@Override
	public boolean request(int userId, int bookId) {
		return dao.request(userId, bookId);
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		return dao.returnBook(bookId, userId, status);
	}

	@Override
	public List<BorrowedBooksInfo> borrowedBook(int userId) {
		return dao.borrowedBook(userId);
	}

}
