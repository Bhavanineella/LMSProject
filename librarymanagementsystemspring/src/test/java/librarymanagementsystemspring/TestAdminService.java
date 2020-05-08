package librarymanagementsystemspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.BookIssueDetails;
import librarymanagementsystemspring.dto.RequestInfo;
import librarymanagementsystemspring.dto.UserInfo;
import librarymanagementsystemspring.service.AdminService;

public class TestAdminService {
	@Autowired
private AdminService service;
	
	@Test
	public void testAddBookValid() {
		BookInfo bean = new BookInfo();
		bean.setBookName("Java");
		bean.setAuthorName("James");
		bean.setBookCategory("Programing");
		bean.setPublisherName("Arihent");
		boolean check = service.addBook(bean);
		Assertions.assertTrue(check);
	}
	@Test
	public void testAddBookInvalid() {
		BookInfo bean = new BookInfo();
		bean.setBookName("Java");
		bean.setAuthorName("James");
		bean.setBookCategory("Programing");
		bean.setPublisherName("Arihent");
		boolean check = service.addBook(bean);
		Assertions.assertFalse(check);	
	} 
	
	@Test
	public void testRemoveBookValid() {
		boolean check = service.removeBook(105);
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testRemoveBookInvalid() {
		boolean check = service.removeBook(107);
		Assertions.assertFalse(check);
	}
	
	@Test
	public void testUpdateBookValid() {
		BookInfo book = new BookInfo();
		book.setBookId(104);
		book.setBookName("Maths");
		boolean check = service.updateBook(book);
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testUpdateBookInvalid() {
		BookInfo book = new BookInfo();
		book.setBookId(109);
		book.setBookName("Maths");
		boolean check = service.updateBook(book);
		Assertions.assertFalse(check);
	}
	
	@Test
	public void testIssueBookValid() {
		boolean check = service.issueBook(105, 100002);
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testIssueBookInvalid() {
		boolean check = service.issueBook(109, 100002);
		Assertions.assertFalse(check);
	}
	
	@Test
	public void testBookHistoryDetailsValid() {
		List<Integer> info = service.bookHistoryDetails(100002);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testBookHistoryDetailsInvalid() {
		List<Integer> info = service.bookHistoryDetails(100003);
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(0, info.size());		
	}
	
	@Test
	public void testShowRequestsValid() {
		List<RequestInfo> info = service.showRequests();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testShowRequestsInvalid() {
		List<RequestInfo> info = service.showRequests();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(2, info.size());		
	}
	
	@Test
	public void testShowIssuedBooksValid() {
		List<BookIssueDetails> info = service.showIssuedBooks();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testShowIssuedBooksInvalid() {
		List<BookIssueDetails> info = service.showIssuedBooks();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(2, info.size());		
	}
	
	@Test
	public void testShowUsersValid() {
		List<UserInfo> info = service.showUsers();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(4, info.size());		
	}
	
	@Test
	public void testShowUsersInvalid() {
		List<UserInfo> info = service.showUsers();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(2, info.size());		
	}

}
