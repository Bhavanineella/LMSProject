package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;

public interface AdminUserDAO {
    
	boolean register(UsersInfo user);
	UsersInfo login(String email,String password);
	
	ArrayList<BookInfo> searchBookById(int bId);
	ArrayList<BookInfo> searchBookByTitle(String bookName);
	ArrayList<BookInfo> searchBookByAuthor(String author);
	ArrayList<BookInfo> getBooksInfo();
	boolean updatePassword(String email,String password,String newPassword,String role);

	
}
