package librarymanagementsystemspring.dao;

import java.util.List;

import librarymanagementsystemspring.dto.BooksBorrowed;

public interface StudentDAO {
	List<BooksBorrowed> borrowedBook(int userId);
	boolean request(int userId, int bookId);
	boolean returnBook(int bookId,int userId,String status);
}
