package librarymanagementsystem;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.UserDAO;
import com.capgemini.librarymanagementsystem.dao.UserDAOImplementation;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;

public class TestUserDAO {

	private UserDAO dao=new UserDAOImplementation();
	BookInfo info=new BookInfo();
	
	@Test
	public void testRegisterStudent() {
		UserInfo info=new UserInfo();
		info.setId(12345);
		info.setName("bhavani");
		info.setMobileNo(994851751);
		info.setEmail("bhavani@gmail.com");
		info.setPassword("Bhavani@123");
		boolean status=dao.registerUser(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook() {
		UserInfo status = dao.loginUser("bhavani@gmail.com", "Bhavani@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle() {
		ArrayList<BookInfo> info = dao.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor() {
		ArrayList<BookInfo> info = dao.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory() {
		ArrayList<BookInfo> info = dao.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo() {
		ArrayList<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	/*
	 * @Test public void testRequest() { BookRequestInfo info = dao. }
	 */
	@Test
	public void testRegisterStudent1() {
		UserInfo info=new UserInfo();
		info.setId(54321);
		info.setName("Shivani");
		info.setMobileNo(991216571);
		info.setEmail("shivani@gmail.com");
		info.setPassword("Shivani@123");
		boolean status=dao.registerUser(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook1() {
		UserInfo status = dao.loginUser("shivani@gmail.com", "Shivani@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		ArrayList<BookInfo> info = dao.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		ArrayList<BookInfo> info = dao.searchBookByAuthor("kalyan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		ArrayList<BookInfo> info = dao.searchBookByCategory("maths");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		ArrayList<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
	}

}
