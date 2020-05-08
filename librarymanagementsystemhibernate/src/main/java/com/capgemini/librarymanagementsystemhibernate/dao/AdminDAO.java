package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;


public interface AdminDAO {

	boolean addBook(BookInfo book);
	boolean removeBook(int bookId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bookId,int userId);
	List<Integer> bookHistoryDetails(int userId);
	List<RequestInfo> showRequests();
	List<BookIssueInfo> showIssuedBooks();
	List<UserInfo> showUsers();
	
}
