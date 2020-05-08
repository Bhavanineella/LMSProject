package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;


public interface AdminService {

	boolean addBook(BookInfo book);
	boolean removeBook(int bookId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bookId,int userId);
	ArrayList<BookIssueDetails> bookHistoryDetails(int userId);
	ArrayList<RequestDetails> showRequests();
	ArrayList<BookIssueDetails> showIssuedBooks();
	ArrayList<UsersInfo> showUsers();
	
}
