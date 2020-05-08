

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystem.dao.AdminDAO;
import com.capgemini.librarymanagementsystem.dao.AdminDAOImplementation;
import com.capgemini.librarymanagementsystem.dto.AdminInfo;
import com.capgemini.librarymanagementsystem.dto.BookInfo;
import com.capgemini.librarymanagementsystem.dto.UserInfo;
public class TestCollections {
	private AdminDAO dao=new AdminDAOImplementation();
	@Test
	public void testaddBook() {
		BookInfo info=new BookInfo();
		info.setBookId(12345);
		info.setBookName("javacolle");
		info.setAuthor("jamesgosling");
		info.setCategory("java");
		info.setPublisherName("sunmicrosystems");
		boolean status=dao.addBook(info);
		Assertions.assertTrue(status);
	}
	@Test
	public void testregisterAdmin() {
		AdminInfo adn=new AdminInfo();
		adn.setId(12345);
		adn.setName("Bhavani");
		adn.setMobileNo(994920634);
		adn.setEmail("bhavani@gmail.com");
		adn.setPassword("Bhavani@123");
		boolean status=dao.registerAdmin(adn);
		Assertions.assertTrue(status);
	}
	@Test
	public void removeBook() {
		BookInfo bk=new BookInfo();
		bk.setBookId(12345);
		bk.setBookName("javacolle");
		bk.setAuthor("jamesgosling");
		bk.setCategory("java");
		bk.setPublisherName("sunmicrosystems");
		boolean status=dao.removeBook(12345);
		Assertions.assertTrue(status);
	}
	@Test
	public void updateBook() {
		BookInfo info=new BookInfo();
		info.setBookId(11111);
		info.setBookName("javajdbc");
		info.setAuthor("rknarayan");
		info.setCategory("aptitude");
		info.setPublisherName("skpublications");
		boolean status=dao.addBook(info);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void loginAdmin() {
		AdminInfo admin=dao.loginAdmin("bhavani@gmail.com", "Bhavani@123");
		Assertions.assertNotNull(admin);
		
	}
	
	@Test
	public void bookIssue() {
		UserInfo userInfo=new UserInfo();
		BookInfo bookInfo=new BookInfo();
		
		
	}
	
}