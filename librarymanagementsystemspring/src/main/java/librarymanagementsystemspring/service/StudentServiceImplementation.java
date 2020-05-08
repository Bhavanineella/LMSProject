package librarymanagementsystemspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagementsystemspring.dao.StudentDAO;
import librarymanagementsystemspring.dto.BooksBorrowed;
@Service
public class StudentServiceImplementation implements StudentService{
	
	@Autowired
	private StudentDAO dao;

	@Override
	public List<BooksBorrowed> borrowedBook(int userId) {
		// TODO Auto-generated method stub
		return dao.borrowedBook(userId);
	}

	@Override
	public boolean request(int userId, int bookId) {
		// TODO Auto-generated method stub
		return dao.request(userId, bookId);
	}

	@Override
	public boolean returnBook(int bookId, int userId, String status) {
		// TODO Auto-generated method stub
		return dao.returnBook(bookId, userId, status);
	}

}
