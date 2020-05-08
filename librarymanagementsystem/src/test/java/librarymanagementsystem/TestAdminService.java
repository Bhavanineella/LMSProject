package librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.RequestInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.AdminServiceImplementation;

public class TestAdminService {

	private AdminService service=new AdminServiceImplementation();
	BookInfo info=new BookInfo();
	
	@Test
	public void testaddBook() {
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setAuthor("jamesgosling");
		info.setCategory("java");
		info.setPublisherName("sunmicrosystems");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testregisterAdmin() {
		AdminInfo adn=new AdminInfo();
		adn.setId(12345);
		adn.setName("bhavani");
		adn.setMobileNo(994851751);
		adn.setEmail("bhavani@gmail.com");
		adn.setPassword("Bhavani@123");
		boolean status=service.registerAdmin(adn);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook() {
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setAuthor("jamesgosling");
		info.setCategory("java");
		info.setPublisherName("sunmicrosystems");
		boolean status=service.removeBook(12345);
		Assertions.assertTrue(status);
	}
	@Test
	public void testUpdateBook() {
		info.setBookId(11111);
		info.setBookName("javajdbc");
		info.setAuthor("rknarayan");
		info.setCategory("aptitude");
		info.setPublisherName("skpublications");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook() {
		AdminInfo status = service.loginAdmin("bhavani@gmail.com", "Bhavani@123");
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
		ArrayList<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowStudents() {
		List<UserInfo> info = service.showUsers();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowRequests() {
		List<RequestInfo> info = service.showRequests();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testaddBook1() {
		info.setBookId(4567);
		info.setBookName("servlet");
		info.setAuthor("GiridharSir");
		info.setCategory("java");
		info.setPublisherName("servers");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testregisterAdmin1() {
		AdminInfo adn=new AdminInfo();
		adn.setId(12345);
		adn.setName("varun");
		adn.setMobileNo(728598698);
		adn.setEmail("varun@gmail.com");
		adn.setPassword("Varun@123");
		boolean status=service.registerAdmin(adn);
		Assertions.assertTrue(status);
	}
	@Test
	public void testRemoveBook1() {
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setAuthor("jamesgosling");
		info.setCategory("java");
		info.setPublisherName("sunmicrosystems");
		boolean status=service.removeBook(12345);
		Assertions.assertTrue(status);
	}
	@Test
	public void testUpdateBook1() {
		info.setBookId(11111);
		info.setBookName("javajdbc");
		info.setAuthor("rknarayan");
		info.setCategory("aptitude");
		info.setPublisherName("skpublications");
		boolean status=service.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testAuthenticateBook1() {
		AdminInfo status = service.loginAdmin("bhavani@gmail.com", "Bhavani@123");
		Assertions.assertNotNull(status);
	}
	@Test
	public void testSearchByTitle1() {
		ArrayList<BookInfo> info = service.searchBookByTitle("javajdbc");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByAuthor1() {
		ArrayList<BookInfo> info = service.searchBookByAuthor("rknarayan");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testSearchByCategory1() {
		ArrayList<BookInfo> info = service.searchBookByCategory("aptitude");
		Assertions.assertNotNull(info);
	}
	@Test
	public void testGetBookInfo1() {
		ArrayList<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowStudents1() {
		List<UserInfo> info = service.showUsers();
		Assertions.assertNotNull(info);
	}
	@Test
	public void testShowRequests1() {
		List<RequestInfo> info = service.showRequests();
		Assertions.assertNotNull(info);
	}

}
