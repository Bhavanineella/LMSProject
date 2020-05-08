package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryFactory;

public class AdminUserServiceImplementation implements AdminUserService {

    private AdminUserDAO dao=LibraryFactory.getAdminUserDAO();
	
	@Override
	public boolean register(UsersInfo user) {
		return dao.register(user);
	}

	@Override
	public UsersInfo login(String email, String password) {
		return dao.login(email, password);
	}


	@Override
	public ArrayList<BookInfo> searchBookByTitle(String bookName) {
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public ArrayList<BookInfo> searchBookByAuthor(String author) {
		return dao.searchBookByAuthor(author);
	}

	@Override
	public ArrayList<BookInfo> searchBookById(int bookId) {
		return dao.searchBookById(bookId);
	}
     
	@Override
	public ArrayList<BookInfo> getBooksInfo() {
		return dao.getBooksInfo();
	}
	
	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		return dao.updatePassword(email, password, newPassword, role);
	}

	

}
