package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dao.UserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;


public class TestUserDAO {
 
	private UserDAO dao = new UserDAOImplementation();
	
	@Test
	public void testRequest() {
		boolean status = dao.request(852852, 852852);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook() {
		boolean status = dao.returnBook(1234, 123123, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook() {
		List<BorrowedBooksInfo> info = dao.borrowedBook(159753);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testRequest1() {
		boolean status = dao.request(852852, 987654);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook1() {
		boolean status = dao.returnBook(123123, 123456, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook1() {
		List<BorrowedBooksInfo> info = dao.borrowedBook(741852);
		Assertions.assertNotNull(info);
	}
}
