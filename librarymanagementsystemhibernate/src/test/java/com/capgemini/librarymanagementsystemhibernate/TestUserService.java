package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BorrowedBooksInfo;
import com.capgemini.librarymanagementsystemhibernate.service.UsersService;
import com.capgemini.librarymanagementsystemhibernate.service.UsersServiceImplementation;

public class TestUserService {

private UsersService service = new UsersServiceImplementation();
	
	@Test
	public void testRequest() {
		boolean status = service.request(852852, 852852);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook() {
		boolean status = service.returnBook(1234, 123123, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook() {
		List<BorrowedBooksInfo> info = service.borrowedBook(159753);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testRequest1() {
		boolean status = service.request(852852, 987654);
		Assertions.assertTrue(status);
	}
	@Test
	public void testReturnBook1() {
		boolean status = service.returnBook(123123, 123456, "yes");
		Assertions.assertTrue(status);
	}
	@Test
	public void testBorrowedBook1() {
		List<BorrowedBooksInfo> info = service.borrowedBook(741852);
		Assertions.assertNotNull(info);
	}

}
