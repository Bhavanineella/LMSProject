package librarymanagementsystemspring.dao;

import java.util.List;

import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.BookIssueDetails;
import librarymanagementsystemspring.dto.RequestInfo;
import librarymanagementsystemspring.dto.UserInfo;

public interface AdminDAO {
	boolean addBook(BookInfo book);
	boolean removeBook(int bookId);
	boolean updateBook(BookInfo book);
	boolean issueBook(int bookId,int userId);
	List<Integer> bookHistoryDetails(int userId);
	List<RequestInfo> showRequests();
	List<BookIssueDetails> showIssuedBooks();
	List<UserInfo> showUsers();
}
