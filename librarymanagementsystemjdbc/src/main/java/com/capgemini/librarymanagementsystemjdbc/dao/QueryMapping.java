package com.capgemini.librarymanagementsystemjdbc.dao;

public interface QueryMapping {

	String registerQuery = "insert into users_info values(?,?,?,?,?,?,?)";
	
	String loginQuery = "select * from users_info where email=? and password=?";
	
	String addBookQuery = "insert into book_info values(?,?,?,?,?)";
	
	String removeBookQuery = "delete from book_info where bookid=?";
	
	String updateBookQuery = "update book_info set bookname=? where bookid=?";
	
	String issueBookQuery1 = "select * from request_details where userid=? and bookid=? and email=(select email from users where userid=?)";
	
	String issueBookQuery2 = "insert into book_issue_details values(?,?,?,?)";
	
	String issueBookQuery3 = "Insert into borrowed_books values(?,?,(select email from users where userid=?))";
	
	String requestQuery1 = "select count(*) as userid from borrowed_books where userid=? and bookid=? and email=(select email from users_info where userid=?)";
	
	String requestQuery3 = "insert into request_details values(?,(select concat(firstName,'_',lastName) from users_info where userid=?)"
			+ ",?,(select bookname from book_info where bookid=?),(select email from users_info where userid=?))";
	
	String requestQuery2 = "select count(*) as userid from book_issue_details where userid=?";
	
	String titleQuery = "select * from book_info where bookname=?";
	
	String authorQuery = "select * from book_info where author=?";
	
	String getAllBooksQuery = "select * from book_info";
	
	String bookHistroyQuery = "select count(*) as userid from book_issue_details where userid=?";
	
	String borrowQuery = "select * from borrowed_books where userid=?";
	
	String searchIdQuery = "select * from book_info where bookid=?";
	
	String returnBookQuery1 = "select * from book_issue_details where bookid=? and userid=?";
	
	String returnBookQuery2 = "delete from book_issue_details where bookid=? and userid=?";
	
	String returnBookQuery3 = "delete from borrowed_books where bookid=? and userid=?";
	
	String returnBookQuery4 = "delete from request_details where bookid=? and userid=?";
	
	String showRequestsQuery = "select * from request_details";
	
	String showIssuedBooksQuery = "select * from book_issue_details";
	
	String showUsersQuery = "select * from users_info";
	
	String updatePasswordQuery1 = "select * from users_info where email=? and role=?";
	
	String updatePasswordQuery2 = "update users_info set password=? where email=? and password=?";
		
}

