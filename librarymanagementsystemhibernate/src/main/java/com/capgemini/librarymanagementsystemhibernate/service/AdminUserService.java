package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;

public interface AdminUserService {

	boolean register(UserInfo user);
	UserInfo login(String email,String password);
	List<BookInfo> searchBookById(int bookId);
	List<BookInfo> searchBookByTitle(String bookName);
	List<BookInfo> searchBookByAuthor(String author);
	List<BookInfo> getBooksInfo();
	boolean updatePassword(int id, String password, String newPassword, String role);

}
