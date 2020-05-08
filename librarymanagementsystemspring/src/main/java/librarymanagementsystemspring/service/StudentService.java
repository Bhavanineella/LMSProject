package librarymanagementsystemspring.service;

import java.util.List;


import librarymanagementsystemspring.dto.BooksBorrowed;

public interface StudentService {
	List<BooksBorrowed> borrowedBook(int userId);
	boolean request(int userId, int bookId);
	boolean returnBook(int bookId,int userId,String status);
}
