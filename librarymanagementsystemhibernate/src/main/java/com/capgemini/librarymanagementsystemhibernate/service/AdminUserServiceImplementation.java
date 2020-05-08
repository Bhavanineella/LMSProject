package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;
import com.capgemini.librarymanagementsystemhibernate.factory.LibraryFactory;

public class AdminUserServiceImplementation implements AdminUserService {

	private AdminUserDAO dao = LibraryFactory.getAdminUserDAO();

	@Override
	public boolean register(UserInfo user) {
		return dao.register(user);
	}

	@Override
	public UserInfo login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public List<BookInfo> searchBookById(int bookId) {
		return dao.searchBookById(bookId);
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		return dao.updatePassword(id, password, newPassword, role);
	}
	
	

}
