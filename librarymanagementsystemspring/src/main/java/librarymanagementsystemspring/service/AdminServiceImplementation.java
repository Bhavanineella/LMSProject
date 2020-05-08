package librarymanagementsystemspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagementsystemspring.dao.AdminDAO;
import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.BookIssueDetails;
import librarymanagementsystemspring.dto.RequestInfo;
import librarymanagementsystemspring.dto.UserInfo;

@Service
public class AdminServiceImplementation implements AdminService{
	
	@Autowired
	private AdminDAO dao;

	@Override
	public boolean addBook(BookInfo book) {
		// TODO Auto-generated method stub
		return dao.addBook(book);
	}

	@Override
	public boolean removeBook(int bookId) {
		// TODO Auto-generated method stub
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookInfo book) {
		// TODO Auto-generated method stub
		return dao.updateBook(book);
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		// TODO Auto-generated method stub
		return dao.issueBook(bookId, userId);
	}

	@Override
	public List<Integer> bookHistoryDetails(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestInfo> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
	}

	@Override
	public List<BookIssueDetails> showIssuedBooks() {
		// TODO Auto-generated method stub
		return dao.showIssuedBooks();
	}

	@Override
	public List<UserInfo> showUsers() {
		// TODO Auto-generated method stub
		return dao.showUsers();
	}

}
