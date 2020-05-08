package librarymanagementsystemspring.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LibraryResponse {
	
	private boolean error;
	private String message;
	
	private UserInfo user;
	private BookInfo bookInfo;
	private BookIssueDetails bookIssue;
	private BooksBorrowed booksBorrowed;
	private RequestInfo requestInfo;
	
	private List<UserInfo> users;
	private List<BookInfo> book;
	private List<BookIssueDetails> issue;
	private List<BooksBorrowed> borrowed;
	private List<RequestInfo> info;
	
	
	public List<UserInfo> getUsers() {
		return users;
	}
	public void setUserInfo(List<UserInfo> users) {
		this.users = users;
	}
	public List<BookInfo> getBook() {
		return book;
	}
	public void setBookInfo(List<BookInfo> book) {
		this.book = book;
	}
	public List<BookIssueDetails> getIssue() {
		return issue;
	}
	public void setIssue(List<BookIssueDetails> issue) {
		this.issue = issue;
	}
	public List<BooksBorrowed> getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(List<BooksBorrowed> borrowed) {
		this.borrowed = borrowed;
	}
	public List<RequestInfo> getDetails() {
		return info;
	}
	public void setRequestDetails(List<RequestInfo> info) {
		this.info = info;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public BookInfo getBookDetails() {
		return bookInfo;
	}
	public void setBookDetails(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public BookIssueDetails getBookIssue() {
		return bookIssue;
	}
	public void setBookIssue(BookIssueDetails bookIssue) {
		this.bookIssue = bookIssue;
	}
	public BooksBorrowed getBooksBorrowed() {
		return booksBorrowed;
	}
	public void setBooksBorrowed(BooksBorrowed booksBorrowed) {
		this.booksBorrowed = booksBorrowed;
	}
	public RequestInfo getRequestInfo() {
		return requestInfo;
	}
	public void setRequestDetails(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
	}
	
	

	
	
}
