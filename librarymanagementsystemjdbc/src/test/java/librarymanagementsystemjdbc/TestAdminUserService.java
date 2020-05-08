package librarymanagementsystemjdbc;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.service.AdminUserService;
import com.capgemini.librarymanagementsystemjdbc.service.AdminUserServiceImplementation;

public class TestAdminUserService {

private AdminUserService service = new AdminUserServiceImplementation();
	
	@Test
	public void testRegister() {
		UsersInfo info = new UsersInfo();
		info.setUserId(951753);
		info.setFirstName("Bhavani");
		info.setLastName("Neella");
		info.setMobile(728598698);
		info.setPassword("Bhavani@123");
		info.setRole("User");
		boolean status = service.register(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testLogin() {
		UsersInfo status = service.login("varun@gmail.com", "Varu@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById() {
		ArrayList<BookInfo> info = service.searchBookById(123123);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle() {
		ArrayList<BookInfo> info = service.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor() {
		ArrayList<BookInfo> info = service.searchBookByAuthor("james");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo() {
		ArrayList<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword() {
		boolean status = service.updatePassword("varun@gmail.com", "Varu@1234", "Varun@1234", "Admin");
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
		boolean status = service.register(info);
		Assertions.assertFalse(status);
	}
	@Test
	public void testLogin1() {
		UsersInfo status = service.login("varun@gmail.com", "Varun@1234");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchBookById1() {
		ArrayList<BookInfo> info = service.searchBookById(852852);
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByTitle1() {
		ArrayList<BookInfo> info = service.searchBookByTitle("phy");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchBookByAuthor1() {
		ArrayList<BookInfo> info = service.searchBookByAuthor("chai");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBooksInfo1() {
		ArrayList<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testUpdatePassword1() {
		boolean status = service.updatePassword("shiva@gmail.com", "shivakumar", "Shiv123@", "librarian");
		Assertions.assertTrue(status);
	}

}
