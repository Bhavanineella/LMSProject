package librarymanagementsystem;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.service.UserServiceImplementation;

public class TestUserService {

	private UserService service=new UserServiceImplementation();
	BookInfo info=new BookInfo();
	
	@Test
	public void testRegisterStudent() {
		UserInfo info=new UserInfo();
		info.setId(78965);
		info.setName("Bhavani");
		info.setMobileNo(987456321);
		info.setEmail("bhavani@gmail.com");
		info.setPassword("Bhavani@123");
		boolean status=service.registerUser(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook() {
		UserInfo status = service.loginUser("bhavani@gmail.com", "Bhavani@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle() {
		ArrayList<BookInfo> info = service.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor() {
		ArrayList<BookInfo> info = service.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory() {
		ArrayList<BookInfo> info = service.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo() {
		ArrayList<BookInfo> info = service.getBookInfo();
		Assertions.assertNotNull(info);
	}
	/*
	 * @Test public void testRequest() { BookRequestInfo info = service. }
	 */
	@Test
	public void testRegisterStudent1() {
		UserInfo info=new UserInfo();
		info.setId(7412);
		info.setName("Shivani");
		info.setMobileNo(987456321);
		info.setEmail("shivani@gmail.com");
		info.setPassword("Shivani@123");
		boolean status=service.registerUser(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook1() {
		UserInfo status = service.loginUser("shivani@gmail.com", "Shivani@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		ArrayList<BookInfo> info = service.searchBookByTitle("java");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		ArrayList<BookInfo> info = service.searchBookByAuthor("kalyan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		ArrayList<BookInfo> info = service.searchBookByCategory("maths");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		ArrayList<BookInfo> info = service.getBookInfo();
		Assertions.assertNotNull(info);
	}

}
