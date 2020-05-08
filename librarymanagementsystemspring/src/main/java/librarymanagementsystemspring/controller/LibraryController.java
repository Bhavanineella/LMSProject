package librarymanagementsystemspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import librarymanagementsystemspring.dto.BookInfo;
import librarymanagementsystemspring.dto.BookIssueDetails;
import librarymanagementsystemspring.dto.BooksBorrowed;
import librarymanagementsystemspring.dto.LibraryResponse;
import librarymanagementsystemspring.dto.RequestInfo;
import librarymanagementsystemspring.dto.UserInfo;
import librarymanagementsystemspring.service.AdminService;
import librarymanagementsystemspring.service.StudentService;
import librarymanagementsystemspring.service.UserService;

@RestController
public class LibraryController {

	@Autowired
	private UserService service;
	
	@Autowired
	private AdminService service1;
	
	@Autowired
	private StudentService service2;

	@PostMapping(path = "/addUser", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse addUser(@RequestBody UserInfo user) {
		boolean isAdded = service.register(user);

		LibraryResponse response = new LibraryResponse();
		if(isAdded) {
			response.setMessage("Record is inserted Successfully");
		} else {
			response.setError(true);
			response.setMessage("Record is not inserted");
		}
		return response;
	}
	
	
	@PostMapping(path = "/login", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse authentication(String email, String password) {
		UserInfo userLogin = service.login(email, password);
		LibraryResponse response = new LibraryResponse();
		if (userLogin != null) {
			response.setMessage("Login succesfull");
		} else {
			response.setError(true);
			response.setMessage("Invalid credentials,Please try again");
		}
		return response;
	}

	@PostMapping(path = "/addBook", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse addBook(@RequestBody BookInfo bean) {
		boolean isAdded = service1.addBook(bean);

		LibraryResponse response = new LibraryResponse();
		if(isAdded) {
			response.setMessage("Record is inserted Successfully");
		} else {
			response.setError(true);
			response.setMessage("Record is not inserted");
		}
		return response;	
	}

	@DeleteMapping(path="/deleteBook/{bookId}",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse removeBook(@PathVariable(name="bookId") int bookId ) {
		boolean isDeleted = service1.removeBook(bookId);
		LibraryResponse response = new LibraryResponse();
		if(isDeleted) {
			response.setMessage("Record deleted");
		} else {
			response.setError(true);
			response.setMessage("Record not deleted");
		}
		return response;		
	}
	
	@PutMapping(path = "/updateBook",
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse updateBook(@RequestBody BookInfo bean) {
		boolean isUpdated = service1.updateBook(bean);
		LibraryResponse response = new LibraryResponse();
		if(isUpdated) {
			response.setMessage("Record is updated");
		} else {
			response.setError(true);
			response.setMessage("Record not updated");
		}
		return response;		
	}
	
	@GetMapping(path="/getBooksById",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksById(int bookId) {
		List<BookInfo> recordList = service.searchBookById(bookId);
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBookInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Id not present");
		}
		return response;
				
	}
	
	@GetMapping(path="/getBooksByTitle",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksByTitle(String bookName) {
		List<BookInfo> recordList = service.searchBookByTitle(bookName);
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBookInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Title not present");
		}
		return response;
				
	}
	
	@GetMapping(path="/getBooksByAuthor",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksByAuthor(String author) {
		List<BookInfo> recordList = service.searchBookByAuthor(author);
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBookInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Author not present");
		}
		return response;
				
	}
	
	@GetMapping(path="/getBooksInfo",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBooksInfo() {
		List<BookInfo> recordList = service.getBooksInfo();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBookInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("Book with given Author not present");
		}
		return response;
	}
	
	@GetMapping(path="/getBorrowedBooks",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse getBorrowedBooks(int userId) {
		List<BooksBorrowed> recordList = service2.borrowedBook(userId);
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setBorrowed(recordList);
		} else {
			response.setError(true);
			response.setMessage("The respective user hasn't borrowed any books");
		}
		return response;
	}
	
	@GetMapping(path="/showRequests",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse showRequests() {
		List<RequestInfo> recordList = service1.showRequests();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setRequestDetails(recordList);
		} else {
			response.setError(true);
			response.setMessage("No requests has been received");
		}
		return response;
	}
	
	@GetMapping(path="/showIssuedBooks",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse showIssuedBooks() {
		List<BookIssueDetails> recordList = service1.showIssuedBooks();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setIssue(recordList);
		} else {
			response.setError(true);
			response.setMessage("No Books has been issued");
		}
		return response;
	}
	
	@GetMapping(path="/showUsers",
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse showUsers() {
		List<UserInfo> recordList = service1.showUsers();
		LibraryResponse response = new LibraryResponse();
		
		if(recordList != null && !recordList.isEmpty()) {
			response.setUserInfo(recordList);
		} else {
			response.setError(true);
			response.setMessage("No Users in the database");
		}
		return response;
	}
	
	@PutMapping(path = "/updatePassword",
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse updatePassord(int id, String password, String newPassword, String role) {
		boolean isUpdated = service.updatePassword(id, password, newPassword, role);
		LibraryResponse response = new LibraryResponse();
		
		if(isUpdated) {
			response.setMessage("Password is updated");
		} else {
			response.setError(true);
			response.setMessage("Password is not updated");
		}
		return response;	
	}
	
	@PostMapping(path = "/requestBook", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse requestBook(int userId,int bookId) {
		boolean isRequested = service2.request(userId, bookId);

		LibraryResponse response = new LibraryResponse();
		if(isRequested) {
			response.setMessage("Request Placed");
		} else {
			response.setError(true);
			response.setMessage("Request not placed");
		}
		return response;
	}

	@PostMapping(path = "/issueBook", 
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse issueBook(int userId,int bookId) {
		boolean isIssued = service1.issueBook(bookId, userId);

		LibraryResponse response = new LibraryResponse();
		if(isIssued) {
			response.setMessage("Book Issued");
		} else {
			response.setError(true);
			response.setMessage("Book not Issued");
		}
		return response;
	}
	
	
	@PostMapping(path="/returnBook",
			consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public LibraryResponse returnBook(int userId,int bookId,String status ) {
		boolean isReturned = service2.returnBook(bookId, userId, status);
		LibraryResponse response = new LibraryResponse();
		if(isReturned) {
			response.setMessage("Book Returned");
		} else {
			response.setError(true);
			response.setMessage("Book not returned");
		}
		return response;		
	}
		
}
