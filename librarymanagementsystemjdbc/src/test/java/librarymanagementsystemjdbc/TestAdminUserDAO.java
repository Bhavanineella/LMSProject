package librarymanagementsystemjdbc;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminUserDAO;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminUsersDAOImplementation;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;

public class TestAdminUserDAO {

	private AdminUserDAO dao = new AdminUsersDAOImplementation();
	
	@Test
	public void testRegister() {
		UsersInfo info = new UsersInfo();
		info.setUserId(951753);
		info.setFirstName("Bhavani");
		info.setLastName("Neella");
		info.setMobile(728598698);
		info.setPassword("Bhavani@123");
		info.setRole("User");
		boolean status = dao.register(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testLogin() {
		UsersInfo status = dao.login("varun@gmail.com", "varu@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById() {
		ArrayList<BookInfo> info = dao.searchBookById(123123);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle() {
		ArrayList<BookInfo> info = dao.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor() {
		ArrayList<BookInfo> info = dao.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo() {
		ArrayList<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword() {
		boolean status = dao.updatePassword("varun@gmail.com", "Varu@1234", "Varun@1234", "librarian");
		Assertions.assertTrue(status);
	}
	@Test
	public void testRegister1() {
		UsersInfo info = new UsersInfo();
		info.setUserId(951753);
		info.setFirstName("Bhavani");
		info.setLastName("Neella");
		info.setMobile(728598698);
		info.setPassword("Bhavani@123");
		info.setRole("User");
		boolean status = dao.register(info);
		Assertions.assertFalse(status);
	}
	@Test
	public void testLogin1() {
		UsersInfo status = dao.login("varun@gmail.com", "Varun@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById1() {
		ArrayList<BookInfo> info = dao.searchBookById(852852);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle1() {
		ArrayList<BookInfo> info = dao.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor1() {
		ArrayList<BookInfo> info = dao.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo1() {
		ArrayList<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword1() {
		boolean status = dao.updatePassword("shivani@gmail.com", "shivani", "Shivani@123", "Admin");
		Assertions.assertTrue(status);
	}

	
	
}
