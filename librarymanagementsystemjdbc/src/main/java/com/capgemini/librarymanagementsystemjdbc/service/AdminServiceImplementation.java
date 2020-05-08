package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.factory.LibraryFactory;


public class AdminServiceImplementation implements AdminService {

	private AdminDAO dao=LibraryFactory.getAdminDAO();
	
	@Override
	public boolean addBook(BookInfo book) {
		return dao.addBook(book);
	}

	public boolean removeBook(int bookId) {
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bookId,int userId) {
		return dao.issueBook(bookId,userId);
	}

	@Override
	public ArrayList<BookIssueDetails> bookHistoryDetails(int userId) {
		return dao.bookHistoryDetails(userId);
	}
	
	@Override
	public ArrayList<RequestDetails> showRequests() {
		return dao.showRequests();
	}

	@Override
	public ArrayList<BookIssueDetails> showIssuedBooks() {
		return dao.showIssuedBooks();
	}

	@Override
	public ArrayList<UsersInfo> showUsers() {
		return dao.showUsers();
	}

}
