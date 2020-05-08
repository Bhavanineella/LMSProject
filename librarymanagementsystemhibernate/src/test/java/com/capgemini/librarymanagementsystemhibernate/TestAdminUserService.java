package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminUserServiceImplementation;

public class TestAdminUserService {

private AdminUserService service = new AdminUserServiceImplementation();
	
	@Test
	public void testRegister() {
		UserInfo info = new UserInfo();
		info.setUserId(951753);
		info.setFirstName("Varun");
		info.setLastName("Neella");
		info.setMobile(728598698);
		info.setPassword("Varun@123");
		info.setRole("User");
		boolean status = service.register(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testLogin() {
		UserInfo status = service.login("bhavani@gmail.com", "Bhavani@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById() {
		List<BookInfo> info = service.searchBookById(123123);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle() {
		List<BookInfo> info = service.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor() {
		List<BookInfo> info = service.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo() {
		List<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword() {
		boolean status = service.updatePassword(7412, "Bhavani@1234", "Bhavan@1234", "librarian");
		Assertions.assertTrue(status);
	}
	@Test
	public void testRegister1() {
		UserInfo info = new UserInfo();
		info.setUserId(951753);
		info.setFirstName("Varun");
		info.setLastName("Neella");
		info.setMobile(728598698);
		info.setPassword("Varun@123");
		info.setRole("User");
		boolean status = service.register(info);
		Assertions.assertFalse(status);
	}
	@Test
	public void testLogin1() {
		UserInfo status = service.login("bhavani@gmail.com", "Bhavan@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById1() {
		List<BookInfo> info = service.searchBookById(852852);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle1() {
		List<BookInfo> info = service.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor1() {
		List<BookInfo> info = service.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo1() {
		List<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword1() {
		boolean status = service.updatePassword(8521, "shivani", "Shivani@123", "Admin");
		Assertions.assertTrue(status);
	}

}
