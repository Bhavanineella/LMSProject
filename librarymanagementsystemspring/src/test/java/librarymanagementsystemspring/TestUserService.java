package librarymanagementsystemspring;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.UserInfo;
import librarymanagementsystemspring.service.UserService;

public class TestUserService {
	@Autowired
private UserService service ;
	
	@Test
	public void testRegisterValid() {
		UserInfo bean = new UserInfo();
		bean.setFirstName("Bhavani");
		bean.setLastName("Neella");
		bean.setEmail("bhavani@gmail.com");
		bean.setPassword("Bhavani@123");
		bean.setRole("User");
		boolean check = service.register(bean);
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
		boolean check = service.register(bean);
		Assertions.assertFalse(check);
	}

	@Test
	public void testLoginValid() {
		UserInfo info = service.login("varun@gmail.com", "Varun@123");
		Assertions.assertNotNull(info);
	}
	
	@Test
	public void testLoginInvalid() {
		UserInfo info = service.login("varun@gmail.com", "Varun@123");
		Assertions.assertNull(info);
	}
	
	@Test
	public void testSearchBookByIdValid() {
		List<BookInfo> info = service.searchBookById(101);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());
		
	}
	
	@Test
	public void testSearchBookByIdInvalid() {
		List<BookInfo> info = service.searchBookById(109);
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());		
	}
	
	@Test
	public void testSearchBookByTitleValid() {
		List<BookInfo> info = service.searchBookByTitle("MM");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testSearchBookByTitleInvalid() {
		List<BookInfo> info = service.searchBookByTitle("Maths");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());		
	}
	
	@Test
	public void testSearchBookByAuthorValid() {
		List<BookInfo> info = service.searchBookByAuthor("sharma");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(1, info.size());		
	}
	
	@Test
	public void testSearchBookByAuthorInvalid() {
		List<BookInfo> info = service.searchBookByAuthor("Jain");
		Assertions.assertNotNull(info);
		Assertions.assertEquals(0, info.size());	
	}
	
	@Test
	public void testBooksInfoValid() {
		List<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
		Assertions.assertEquals(5, info.size());
	}
	
	@Test
	public void testBooksInfoInvalid() {
		List<BookInfo> info = service.getBooksInfo();
		Assertions.assertNotNull(info);
		Assertions.assertNotEquals(6, info.size());
	}
	
	@Test
	public void testUpdatePasswordValid() {
		boolean check = service.updatePassword(100102, "ammU@123", "Admin@123", "admin");
		Assertions.assertTrue(check);
	}
	
	@Test
	public void testUpdatePasswordInvalid() {
		boolean check = service.updatePassword(100102, "ammU@123", "Admin@123", "student");
		Assertions.assertFalse(check);
	}
	


}
