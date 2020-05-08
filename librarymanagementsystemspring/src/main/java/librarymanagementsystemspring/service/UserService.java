package librarymanagementsystemspring.service;

import java.util.List;

import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.UserInfo;

public interface UserService {
	boolean register(UserInfo user);
	UserInfo login(String email,String password);	
	List<BookInfo> searchBookById(int bId);
	List<BookInfo> searchBookByTitle(String bookName);
	List<BookInfo> searchBookByAuthor(String author);
	List<BookInfo> getBooksInfo();
	boolean updatePassword(int id,String password,String newPassword,String role);

}
