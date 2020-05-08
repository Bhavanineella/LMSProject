package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;


public interface UsersService {

	boolean request(int userId, int bookId);
	boolean returnBook(int bookId,int userId,String status);
	List<BorrowedBooksInfo> borrowedBook(int userId);

}
