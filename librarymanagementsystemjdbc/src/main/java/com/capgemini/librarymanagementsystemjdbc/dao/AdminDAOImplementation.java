package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.capgemini.librarymanagementsystemjdbc.dto.BookInfo;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersInfo;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class AdminDAOImplementation implements AdminDAO {
	
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	@Override
	public boolean addBook(BookInfo book) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.addBookQuery);) {

			statement.setInt(1, book.getBookId());
			statement.setString(2, book.getBookName());
			statement.setString(3, book.getAuthor());
			statement.setString(4, book.getCategory());
			statement.setString(5, book.getPublisher());
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeBook(int bookId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.removeBookQuery);) {

			statement.setInt(1, bookId);
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateBook(BookInfo book) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.updateBookQuery);) {

			statement.setString(1, book.getBookName());
			statement.setInt(2, book.getBookId());
			int count = statement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean issueBook(int bookId, int userId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.issueBookQuery1);) {

			statement.setInt(1, userId);
			statement.setInt(2, bookId);
			statement.setInt(3, userId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapping.issueBookQuery2);) {
					pstmt1.setInt(1, bookId);
					pstmt1.setInt(2, userId);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String issueDate = sdf.format(cal.getTime());
					pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
					cal.add(Calendar.DAY_OF_MONTH, 7);
					String returnDate = sdf.format(cal.getTime());
					pstmt1.setDate(4, java.sql.Date.valueOf(returnDate));
					int count = pstmt1.executeUpdate();
					if (count != 0) {
						try (PreparedStatement pstmt2 = connection.prepareStatement(QueryMapping.issueBookQuery3);) {
							pstmt2.setInt(1, userId);
							pstmt2.setInt(2, bookId);
							pstmt2.setInt(3, userId);
							int isBorrowed = pstmt2.executeUpdate();
							if (isBorrowed != 0) {
								return true;
							} else {
								return false;
							}
						}
					} else {
						throw new LibraryException("Book Not issued");
					}
				}
			} else {
				throw new LibraryException("The respective user have not placed any request");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ArrayList<BookIssueDetails> bookHistoryDetails(int userId) {

		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapping.bookHistroyQuery);) {
			statement.setInt(1, userId);
			rs = statement.executeQuery();
			ArrayList<BookIssueDetails> beans = new ArrayList<BookIssueDetails>();
			while (rs.next()) {
				BookIssueDetails issueDetails = new BookIssueDetails();
				issueDetails.setUserId(rs.getInt("userId"));
				beans.add(issueDetails);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ArrayList<RequestDetails> showRequests() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapping.showRequestsQuery);) {
			ArrayList<RequestDetails> beans = new ArrayList<RequestDetails>();
			while (rs.next()) {
				RequestDetails bean = new RequestDetails();
				bean.setUserId(rs.getInt("userId"));
				bean.setFullName(rs.getString("fullName"));
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<BookIssueDetails> showIssuedBooks() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapping.showIssuedBooksQuery);) {
			ArrayList<BookIssueDetails> beans = new ArrayList<BookIssueDetails>();
			while (rs.next()) {
				BookIssueDetails bean = new BookIssueDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setUserId(rs.getInt("userId"));
				bean.setIssueDate(rs.getDate("issueDate"));
				bean.setReturnDate(rs.getDate("returnDate"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<UsersInfo> showUsers() {

		connection = JdbcUtility.getConnection();

		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapping.showUsersQuery);) {
			ArrayList<UsersInfo> beans = new ArrayList<UsersInfo>();
			while (rs.next()) {
				UsersInfo bean = new UsersInfo();
				bean.setUserId(rs.getInt("userId"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobile(rs.getLong("mobileNo"));
				bean.setRole(rs.getString("role"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}



}
