package librarymanagementsystemspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagementsystemspring.dao.UserDAO;
import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.UserInfo;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserDAO dao;

	@Override
	public boolean register(UserInfo user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}

	@Override
	public UserInfo login(String email, String password) {
		// TODO Auto-generated method stub
		return dao.login(email, password);
	}

	@Override
	public List<BookInfo> searchBookById(int bookId) {
		// TODO Auto-generated method stub
		return dao.searchBookById(bookId);
	}

	@Override
	public List<BookInfo> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public List<BookInfo> searchBookByAuthor(String author) {
		// TODO Auto-generated method stub
		return dao.searchBookByAuthor(author);
	}

	@Override
	public List<BookInfo> getBooksInfo() {
		// TODO Auto-generated method stub
		return dao.getBooksInfo();
	}

	@Override
	public boolean updatePassword(int id, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return dao.updatePassword(id, password, newPassword, role);
	}

	
}
