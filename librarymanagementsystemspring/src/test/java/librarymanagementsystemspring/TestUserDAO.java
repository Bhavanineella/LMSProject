package librarymanagementsystemspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import librarymanagementsystemspring.dao.UserDAO;
import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.UserInfo;

public class TestUserDAO {
	@Autowired
	private UserDAO dao;
	
	@Test
	public void testRegisterValid() {
		UserInfo bean = new UserInfo();
		bean.setFirstName("Bhavani");
		bean.setLastName("Neella");
		bean.setEmail("bhavani@gmail.com");
		bean.setPassword("Bhavani@123");
		bean.setRole("User");
		boolean check = dao.register(bean);
		Assertions.assertTrue(check);		
	}
	
	@Test
	public void testRegisterInvalid() {
		UserInfo bean = new UserInfo();
		bean.setFirstName("Bhavani");
		bean.setLastName("Neella");
		bean.setEmail("bhavani@gmail.com");
		bean.setPassword("Bhavani@123");
		bean.setRole("User");
		boolean check = dao.register(bean);
		Assertions.assertFalse(check);
	}

	@Test
	public void testLoginValid() {
		UserInfo info = dao.login("varun@gmail.com", "Varun@123");
		Assertions.assertNotNull(info);
	}
	
	@Test
	public void testLoginInvalid() {
		UserInfo info = dao.login("varun@gmail.com", "Varun@123");
		Assertions.assertNull(info);
	}
	
	@Test
	public void testSearchBookByIdValid() {
		List<BookInfo> info = dao.searchBookById(101);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());
		
	}
	
	@Test
	public void testSearchBookByIdInvalid() {
		List<BookInfo> info = dao.searchBookById(109);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());		
	}
	
	@Test
	public void testSearchBookByTitleValid() {
		List<BookInfo> info = dao.searchBookByTitle("MM");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testSearchBookByTitleInvalid() {
		List<BookInfo> info = dao.searchBookByTitle("Maths");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());		
	}
	
	@Test
	public void testSearchBookByAuthorValid() {
		List<BookInfo> info = dao.searchBookByAuthor("sharma");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testSearchBookByAuthorInvalid() {
		List<BookInfo> info = dao.searchBookByAuthor("Jain");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());	
	}
	
	@Test
	public void testBooksInfoValid() {
		List<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(5, info.size());
	}
	
	@Test
	public void testBooksInfoInvalid() {
		List<BookInfo> info = dao.getBooksInfo();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(6, info.size());
	}
	
	@Test
	public void testUpdatePasswordValid() {
		boolean check = dao.updatePassword(100102, "Varun@123", "Admin@123", "Admin");
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testUpdatePasswordInvalid() {
		boolean check = dao.updatePassword(100102, "Varun@123", "Admin@123", "User");
		Assertions.assertFalse(check);
	}
	
}
