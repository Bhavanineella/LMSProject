package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemhibernate.dao.AdminUserDAOImplemetation;
import com.capgemini.librarymanagementsystemhibernate.dto.BookInfo;
import com.capgemini.librarymanagementsystemhibernate.dto.UserInfo;

public class AdminUsersDAO {

	private AdminUserDAO dao = new AdminUserDAOImplemetation();
	
	@Test
	public void testRegister() {
		UserInfo info = new UserInfo();
		info.setUserId(951753);
		info.setFirstName("Bhavani");
		info.setLastName("Neella");
		info.setMobile(994851751);
		info.setPassword("Bhavani@123");
		info.setRole("User");
		boolean status = dao.register(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testLogin() {
		UserInfo status = dao.login("bhavani@gmail.com", "Bhavani@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById() {
		List<BookInfo> info = dao.searchBookById(123123);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle() {
		List<BookInfo> info = dao.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor() {
		List<BookInfo> info = dao.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo() {
		List<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword() {
		boolean status = dao.updatePassword(1234, "Bhavani@1234", "Bhavan@1234", "Admin");
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
		boolean status = dao.register(info);
		Assertions.assertFalse(status);
	}
	@Test
	public void testLogin1() {
		UserInfo status = dao.login("bhavani@gmail.com", "Bhavani@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById1() {
		List<BookInfo> info = dao.searchBookById(852852);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle1() {
		List<BookInfo> info = dao.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor1() {
		List<BookInfo> info = dao.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo1() {
		List<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword1() {
		boolean status = dao.updatePassword(8522, "shivani", "Shivani@123", "Admin");
		Assertions.assertTrue(status);
	}

	
	
}
